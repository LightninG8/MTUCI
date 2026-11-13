import sqlite3

from db import connect_db
from . import repo


def list_subjects():
    con = connect_db()

    try:
        return repo.list_subjects(con)
    finally:
        con.close()


def save_subject(name, record_id=None):

    if not isinstance(name, str) or not name.strip():
        raise ValueError("Введите название предмета.")

    name = name.strip()

    con = connect_db()

    try:
        with con:

            if record_id is not None:
                if repo.get_subject(con, record_id) is None:
                    raise LookupError("Предмет не найден.")

                repo.update_subject(con, name, record_id)

            else:
                record_id = repo.insert_subject(con, name)

            result = repo.get_subject(con, record_id)

        return result

    except sqlite3.IntegrityError as error:
        raise ValueError(
            "Предмет с таким названием уже существует."
        ) from error

    finally:
        con.close()


def delete_subject(record_id):

    con = connect_db()

    try:
        with con:

            if repo.get_subject(con, record_id) is None:
                raise LookupError("Предмет не найден.")

            repo.delete_subject(con, record_id)

    except sqlite3.IntegrityError as error:
        raise ValueError(
            "Нельзя удалить предмет, по которому уже выставлены оценки."
        ) from error

    finally:
        con.close()