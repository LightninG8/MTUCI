def list_subjects(con):
    cursor = con.execute(
        """
        SELECT id, name
        FROM subjects
        ORDER BY name, id
        """
    )
    return [dict(row) for row in cursor.fetchall()]


def get_subject(con, record_id):
    cursor = con.execute(
        """
        SELECT id, name
        FROM subjects
        WHERE id = ?
        """,
        (record_id,)
    )
    row = cursor.fetchone()
    return dict(row) if row else None


def insert_subject(con, name):
    cursor = con.execute(
        """
        INSERT INTO subjects (name)
        VALUES (?)
        """,
        (name,)
    )
    return cursor.lastrowid


def update_subject(con, name, record_id):
    con.execute(
        """
        UPDATE subjects
        SET name = ?
        WHERE id = ?
        """,
        (name, record_id)
    )


def delete_subject(con, record_id):
    con.execute(
        """
        DELETE FROM subjects
        WHERE id = ?
        """,
        (record_id,)
    )