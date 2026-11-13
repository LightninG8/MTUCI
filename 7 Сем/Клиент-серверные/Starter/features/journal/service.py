"""Бизнес-правила и транзакции раздела journal."""

import sqlite3
from db import connect_db
from . import repo

MARKS = (2, 3, 4, 5)


def _require_id(value, text):
    """Проверяет, что значение — целое положительное число (не bool).

    Raises:
        ValueError: Значение не подходит под идентификатор.
    """
    if not isinstance(value, int) or isinstance(value, bool) or value <= 0:
        raise ValueError(text)
    return value


def get_journal(group_id):
    """Собирает данные таблицы журнала для одной группы.

    Args:
        group_id (int): Идентификатор группы.

    Returns:
        dict: group, students, subjects, grades.

    Raises:
        LookupError: Группа отсутствует.
    """
    con = connect_db()
    try:
        group = repo.get_group(con, group_id)
        if group is None:
            raise LookupError("Группа не найдена.")
        return {
            "group": group,
            "students": repo.list_students(con, group_id),
            "subjects": repo.list_subjects(con),
            "grades": repo.list_grades(con, group_id),
        }
    finally:
        con.close()


def save_grade(student_id, subject_id, mark):
    """Ставит или меняет оценку: INSERT для пустой ячейки, иначе UPDATE.

    Args:
        student_id (int): Студент.
        subject_id (int): Предмет.
        mark (int): Оценка 2, 3, 4 или 5.

    Returns:
        dict: student_id, subject_id и сохранённая mark.

    Raises:
        ValueError: Поля некорректны.
        LookupError: Студент или предмет не найден.
    """
    _require_id(student_id, "Укажите корректного студента.")
    _require_id(subject_id, "Укажите корректный предмет.")
    if not isinstance(mark, int) or isinstance(mark, bool) or mark not in MARKS:
        raise ValueError("Оценка должна быть 2, 3, 4 или 5.")
    con = connect_db()
    try:
        with con:
            if repo.get_student(con, student_id) is None:
                raise LookupError("Студент не найден.")
            if repo.get_subject(con, subject_id) is None:
                raise LookupError("Предмет не найден.")
            if repo.get_grade(con, student_id, subject_id) is None:
                repo.insert_grade(con, student_id, subject_id, mark)
            else:
                repo.update_grade(con, student_id, subject_id, mark)
            saved = repo.get_grade(con, student_id, subject_id)
        return {
            "student_id": saved["student_id"],
            "subject_id": saved["subject_id"],
            "mark": saved["mark"],
        }
    except sqlite3.IntegrityError as error:
        raise ValueError("Не удалось сохранить оценку.") from error
    finally:
        con.close()


def delete_grade(student_id, subject_id):
    """Очищает ячейку журнала. Повторное удаление не считается ошибкой.

    Raises:
        LookupError: Студент или предмет не найден.
    """
    con = connect_db()
    try:
        with con:
            if repo.get_student(con, student_id) is None:
                raise LookupError("Студент не найден.")
            if repo.get_subject(con, subject_id) is None:
                raise LookupError("Предмет не найден.")
            repo.delete_grade(con, student_id, subject_id)
    finally:
        con.close()
