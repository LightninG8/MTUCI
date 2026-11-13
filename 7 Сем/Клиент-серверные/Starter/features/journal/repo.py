"""SQL раздела journal. Соединение передаёт сервис, commit здесь отсутствует."""


def get_group(con, group_id):
    """Находит группу по идентификатору.

    Returns:
        dict | None: Запись группы либо None.
    """
    row = con.execute(
        "SELECT id, name FROM groups WHERE id = ?", (group_id,)
    ).fetchone()
    return dict(row) if row is not None else None


def get_student(con, student_id):
    """Находит студента по идентификатору.

    Returns:
        dict | None: Запись студента либо None.
    """
    row = con.execute(
        "SELECT id, name, group_id FROM students WHERE id = ?", (student_id,)
    ).fetchone()
    return dict(row) if row is not None else None


def get_subject(con, subject_id):
    """Находит предмет по идентификатору.

    Returns:
        dict | None: Запись предмета либо None.
    """
    row = con.execute(
        "SELECT id, name FROM subjects WHERE id = ?", (subject_id,)
    ).fetchone()
    return dict(row) if row is not None else None


def list_students(con, group_id):
    """Возвращает студентов одной группы по алфавиту."""
    cursor = con.execute(
        "SELECT id, name FROM students WHERE group_id = ? ORDER BY name, id",
        (group_id,),
    )
    return [dict(row) for row in cursor.fetchall()]


def list_subjects(con):
    """Возвращает все предметы по алфавиту."""
    cursor = con.execute("SELECT id, name FROM subjects ORDER BY name, id")
    return [dict(row) for row in cursor.fetchall()]


def list_grades(con, group_id):
    """Возвращает оценки студентов выбранной группы.

    Пустая ячейка журнала — это отсутствие строки, поэтому здесь только
    реально выставленные оценки.
    """
    cursor = con.execute(
        '''
        SELECT gr.student_id, gr.subject_id, gr.mark
        FROM grades AS gr
        JOIN students AS s ON s.id = gr.student_id
        WHERE s.group_id = ?
        ORDER BY gr.student_id, gr.subject_id
        ''',
        (group_id,),
    )
    return [dict(row) for row in cursor.fetchall()]


def get_grade(con, student_id, subject_id):
    """Находит оценку по паре студент и предмет.

    Returns:
        dict | None: Запись оценки либо None, если ячейка пуста.
    """
    row = con.execute(
        "SELECT id, student_id, subject_id, mark FROM grades "
        "WHERE student_id = ? AND subject_id = ?",
        (student_id, subject_id),
    ).fetchone()
    return dict(row) if row is not None else None


def insert_grade(con, student_id, subject_id, mark):
    """Добавляет оценку без фиксации транзакции.

    Returns:
        int: Идентификатор новой строки.
    """
    cursor = con.execute(
        "INSERT INTO grades (student_id, subject_id, mark) VALUES (?, ?, ?)",
        (student_id, subject_id, mark),
    )
    return cursor.lastrowid


def update_grade(con, student_id, subject_id, mark):
    """Меняет значение существующей оценки.

    Returns:
        None: Изменения остаются в текущей транзакции.
    """
    con.execute(
        "UPDATE grades SET mark = ? WHERE student_id = ? AND subject_id = ?",
        (mark, student_id, subject_id),
    )


def delete_grade(con, student_id, subject_id):
    """Удаляет оценку; если её нет, ничего не происходит.

    Returns:
        None: Изменения остаются в текущей транзакции.
    """
    con.execute(
        "DELETE FROM grades WHERE student_id = ? AND subject_id = ?",
        (student_id, subject_id),
    )
