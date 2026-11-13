import sqlite3

from db import connect_db
from . import repo


def list_students(group_id=None, query=None):

    con = connect_db()

    try:
        return repo.list_students(
            con,
            group_id,
            query
        )
    finally:
        con.close()


def save_student(name, group_id, email, record_id=None):

    if not isinstance(name, str) or not name.strip():
        raise ValueError("Введите ФИО студента.")

    name = name.strip()

    if len(name) > 50:
        raise ValueError("ФИО не должно превышать 50 символов.")

    if not isinstance(group_id, int) or isinstance(group_id, bool):
        raise ValueError("Выберите группу.")

    if email is not None:
        if not isinstance(email, str):
            raise ValueError("Некорректный email.")

        email = email.strip() or None

    con = connect_db()

    try:
        with con:

            if record_id is not None:
                if repo.get_student(con, record_id) is None:
                    raise LookupError("Студент не найден.")

                repo.update_student(
                    con,
                    name,
                    group_id,
                    email,
                    record_id
                )

            else:
                record_id = repo.insert_student(
                    con,
                    name,
                    group_id,
                    email
                )

            result = repo.get_student(con, record_id)

        return result

    except sqlite3.IntegrityError as error:
        raise ValueError("Указанная группа не существует.") from error

    finally:
        con.close()


def delete_student(student_id, simulate_error=False):

    con = connect_db()

    try:
        with con:

            if repo.get_student(con, student_id) is None:
                raise LookupError("Студент не найден.")

            repo.delete_grades(con, student_id)

            if simulate_error:
                raise RuntimeError("Учебная ошибка")

            repo.delete_student(con, student_id)

    finally:
        con.close()