"""SQL раздела students. Соединение передаёт сервис, commit здесь отсутствует."""


def list_students(con, group_id=None, query=None):
    """Возвращает студентов с названием группы, с фильтром и поиском.

    Поиск выполняется в Python, а не через SQL LIKE: LIKE в SQLite
    нечувствителен к регистру только для латиницы, для кириллицы
    «иван» не нашло бы «Иванов».

    Args:
        con (sqlite3.Connection): Соединение текущей операции.
        group_id (int | None): Показывать только эту группу.
        query (str | None): Часть ФИО без учёта регистра.

    Returns:
        list[dict]: Строки с id, name, group_id, group_name, email.
    """
    sql = (
        "SELECT s.id, s.name, s.group_id, g.name AS group_name, s.email "
        "FROM students AS s JOIN groups AS g ON g.id = s.group_id"
    )
    params = []
    if group_id is not None:
        sql += " WHERE s.group_id = ?"
        params.append(group_id)
    sql += " ORDER BY s.name, s.id"
    rows = [dict(row) for row in con.execute(sql, params).fetchall()]
    needle = (query or "").strip().casefold()
    if needle:
        rows = [row for row in rows if needle in row["name"].casefold()]
    return rows


def get_student(con, record_id):
    """Находит студента по идентификатору.

    Args:
        con (sqlite3.Connection): Соединение текущей операции.
        record_id (int): Идентификатор студента.

    Returns:
        dict | None: Запись с названием группы либо None.
    """
    row = con.execute(
        "SELECT s.id, s.name, s.group_id, g.name AS group_name, s.email "
        "FROM students AS s JOIN groups AS g ON g.id = s.group_id "
        "WHERE s.id = ?",
        (record_id,),
    ).fetchone()
    return dict(row) if row is not None else None


def insert_student(con, name, group_id, email):
    cursor = con.execute(
        """
        INSERT INTO students (name, group_id, email)
        VALUES (?, ?, ?)
        """,
        (name, group_id, email),
    )
    return cursor.lastrowid


def update_student(con, name, group_id, email, record_id):
    con.execute(
        """
        UPDATE students
        SET name = ?, group_id = ?, email = ?
        WHERE id = ?
        """,
        (name, group_id, email, record_id),
    )


def delete_grades(con, student_id):
    con.execute(
        "DELETE FROM grades WHERE student_id = ?",
        (student_id,),
    )


def delete_student(con, record_id):
    con.execute(
        "DELETE FROM students WHERE id = ?",
        (record_id,),
    )