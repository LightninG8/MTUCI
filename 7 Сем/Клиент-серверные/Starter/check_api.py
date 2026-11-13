"""Сквозная проверка JSON API на временной копии БД (рабочая база не меняется)."""

from pathlib import Path
from tempfile import TemporaryDirectory
import sqlite3
import db


def make_copy(directory):
    """Копирует рабочую БД через sqlite3.backup и подменяет db.DATABASE."""
    target = Path(directory) / "journal.db"
    source = db.connect_db()
    copied = sqlite3.connect(target)
    try:
        source.backup(copied)
    finally:
        copied.close()
        source.close()
    db.DATABASE = target


def counts():
    """Возвращает количество строк в четырёх таблицах."""
    con = db.connect_db()
    try:
        return {t: con.execute("SELECT COUNT(*) FROM " + t).fetchone()[0]
                for t in ("groups", "students", "subjects", "grades")}
    finally:
        con.close()


def check_api():
    """Проходит по всем разделам через Flask test client.

    Raises:
        AssertionError: Любой контракт README нарушен.
    """
    original = db.DATABASE
    with TemporaryDirectory(prefix="journal-api-") as directory:
        make_copy(directory)
        try:
            from app import app
            c = app.test_client()

            # --- страницы
            for url in ("/groups", "/subjects", "/students", "/journal", "/reports"):
                assert c.get(url).status_code == 200, url
            print("PASS: 5 страниц открываются")

            # --- предметы (LAB2)
            r = c.post("/api/subjects", json={"name": "Алгоритмы"})
            assert r.status_code == 201, r.get_json()
            sid = r.get_json()["item"]["id"]
            assert c.post("/api/subjects/%d" % sid, json={"name": "Алгоритмы 2"}).status_code == 200
            assert c.post("/api/subjects", json={"name": "Python"}).status_code == 400   # дубль
            assert c.post("/api/subjects", json={"name": "  "}).status_code == 400
            assert c.delete("/api/subjects/1").status_code == 400                        # есть оценки
            assert c.delete("/api/subjects/%d" % sid).status_code == 204
            assert c.delete("/api/subjects/%d" % sid).status_code == 404
            print("PASS: предметы (создание, переименование, защита от удаления)")

            # --- группы: защита непустой группы
            assert c.delete("/api/groups/1").status_code == 400
            new = c.post("/api/groups", json={"name": "Пустая"}).get_json()["item"]
            assert c.delete("/api/groups/%d" % new["id"]).status_code == 204
            print("PASS: непустую группу удалить нельзя, пустую можно")

            # --- студенты (LAB3)
            all_ = c.get("/api/students").get_json()["items"]
            assert len(all_) == 60
            assert len(c.get("/api/students?group_id=1").get_json()["items"]) == 20
            assert c.get("/api/students?group_id=abc").status_code == 400
            found = c.get("/api/students?q=иванов").get_json()["items"]   # кириллица, регистр
            assert found and all("иванов" in s["name"].lower() for s in found), found
            r = c.post("/api/students", json={"name": "Тестов Тест", "group_id": 1, "email": ""})
            assert r.status_code == 201 and r.get_json()["item"]["email"] is None
            stud = r.get_json()["item"]
            assert c.post("/api/students", json={"name": "X", "group_id": 999}).status_code == 400
            assert c.post("/api/students", json={"name": "X" * 51, "group_id": 1}).status_code == 400
            # перевод без потери оценок
            c.post("/api/journal/grades", json={"student_id": stud["id"], "subject_id": 1, "mark": 4})
            r = c.post("/api/students/%d" % stud["id"], json={"name": "Тестов Тест", "group_id": 2, "email": None})
            assert r.status_code == 200 and r.get_json()["item"]["group_id"] == 2
            j = c.get("/api/journal?group_id=2").get_json()
            assert {"student_id": stud["id"], "subject_id": 1, "mark": 4} in j["grades"]
            print("PASS: студенты (поиск, фильтр, перевод сохраняет оценки)")

            # --- журнал (LAB4)
            j = c.get("/api/journal?group_id=1").get_json()
            assert set(j) == {"group", "students", "subjects", "grades"}
            assert len(j["students"]) == 20 and len(j["subjects"]) == 3
            assert c.get("/api/journal").status_code == 400
            assert c.get("/api/journal?group_id=999").status_code == 404
            before = counts()["grades"]
            empty = 10   # студент без оценок
            r = c.post("/api/journal/grades", json={"student_id": empty, "subject_id": 1, "mark": 5})
            assert r.status_code == 200 and r.get_json()["item"]["mark"] == 5
            assert counts()["grades"] == before + 1                     # INSERT
            r = c.post("/api/journal/grades", json={"student_id": empty, "subject_id": 1, "mark": 3})
            assert r.get_json()["item"]["mark"] == 3 and counts()["grades"] == before + 1   # UPDATE
            for bad in (1, 6, 0, "5", None, True, 4.5):
                r = c.post("/api/journal/grades", json={"student_id": empty, "subject_id": 1, "mark": bad})
                assert r.status_code == 400, bad
            assert c.post("/api/journal/grades", json={"student_id": 9999, "subject_id": 1, "mark": 5}).status_code == 404
            assert c.post("/api/journal/grades", json={"student_id": empty, "subject_id": 9999, "mark": 5}).status_code == 404
            assert c.delete("/api/journal/grades/%d/1" % empty).status_code == 204
            assert c.delete("/api/journal/grades/%d/1" % empty).status_code == 204   # повтор
            assert counts()["grades"] == before
            assert c.delete("/api/journal/grades/9999/1").status_code == 404
            print("PASS: журнал (INSERT/UPDATE/DELETE, проверки оценки, 404)")

            # --- отчёты (LAB5)
            rep = c.get("/api/reports?kind=groups").get_json()
            assert rep["kind"] == "groups" and len(rep["items"]) == 3
            assert sum(i["student_count"] for i in rep["items"]) == counts()["students"]
            assert sum(i["grade_count"] for i in rep["items"]) == counts()["grades"]
            lone = c.post("/api/groups", json={"name": "Нулевая"}).get_json()["item"]
            rep = c.get("/api/reports?kind=groups").get_json()["items"]
            zero = [i for i in rep if i["id"] == lone["id"]][0]
            assert zero["student_count"] == 0 and zero["grade_count"] == 0 and zero["average_mark"] is None
            ng = c.get("/api/reports?kind=no_grades").get_json()["items"]
            assert {"id", "name", "group_name"} == set(ng[0])
            fl = c.get("/api/reports?kind=failing").get_json()["items"]
            con = db.connect_db()
            expected_fail = con.execute("SELECT COUNT(DISTINCT student_id) FROM grades WHERE mark = 2").fetchone()[0]
            expected_ng = con.execute("SELECT COUNT(*) FROM students WHERE id NOT IN (SELECT student_id FROM grades)").fetchone()[0]
            con.close()
            assert len(fl) == expected_fail and len(ng) == expected_ng
            assert len({i["id"] for i in fl}) == len(fl), "студент не должен дублироваться"
            assert c.get("/api/reports?kind=zzz").status_code == 400
            assert c.get("/api/reports").status_code == 400
            print("PASS: отчёты (групп %d, без оценок %d, с двойками %d)" % (len(rep), len(ng), len(fl)))

            # --- удаление студента через API и целостность
            victim = 1
            assert c.delete("/api/students/%d" % victim).status_code == 204
            assert c.delete("/api/students/%d" % victim).status_code == 404
            con = db.connect_db()
            assert con.execute("PRAGMA foreign_key_check").fetchall() == []
            assert con.execute("SELECT COUNT(*) FROM grades WHERE student_id = ?", (victim,)).fetchone()[0] == 0
            con.close()
            print("PASS: удаление студента убрало и его оценки, ссылки целы")
        finally:
            db.DATABASE = original
    print("PASS: рабочая база не изменена проверкой")


if __name__ == "__main__":
    check_api()
