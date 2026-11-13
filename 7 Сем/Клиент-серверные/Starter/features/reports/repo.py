"""SQL трёх отчётов. Соединение передаёт сервис, commit здесь отсутствует."""


def group_report(con):
    """Показатели каждой группы, включая пустые.

    LEFT JOIN сохраняет группы без студентов и студентов без оценок.
    Соединение групп со студентами и оценками размножает строки, поэтому
    студентов считаем через COUNT(DISTINCT s.id). Оценки считает
    COUNT(gr.id): для NULL после LEFT JOIN он даёт 0. AVG игнорирует
    NULL и для группы без оценок возвращает NULL, то есть None в Python.

    Returns:
        list[dict]: id, name, student_count, grade_count, average_mark.
    """
    cursor = con.execute(
        '''
        SELECT g.id,
               g.name,
               COUNT(DISTINCT s.id) AS student_count,
               COUNT(gr.id) AS grade_count,
               ROUND(AVG(gr.mark), 2) AS average_mark
        FROM groups AS g
        LEFT JOIN students AS s ON s.group_id = g.id
        LEFT JOIN grades AS gr ON gr.student_id = s.id
        GROUP BY g.id, g.name
        ORDER BY g.name, g.id
        '''
    )
    return [dict(row) for row in cursor.fetchall()]


def no_grades_report(con):
    """Студенты, у которых нет ни одной оценки.

    После LEFT JOIN у студента без оценок все поля grades равны NULL,
    поэтому условие gr.id IS NULL оставляет именно таких студентов.

    Returns:
        list[dict]: id, name, group_name.
    """
    cursor = con.execute(
        '''
        SELECT s.id, s.name, g.name AS group_name
        FROM students AS s
        JOIN groups AS g ON g.id = s.group_id
        LEFT JOIN grades AS gr ON gr.student_id = s.id
        WHERE gr.id IS NULL
        ORDER BY g.name, s.name, s.id
        '''
    )
    return [dict(row) for row in cursor.fetchall()]


def failing_report(con):
    """Студенты, у которых есть хотя бы одна двойка.

    Один студент может иметь несколько двоек, поэтому группируем по
    студенту. HAVING работает с результатом группировки: минимум оценок
    равен 2 только если среди них есть двойка.

    Returns:
        list[dict]: id, name, group_name.
    """
    cursor = con.execute(
        '''
        SELECT s.id, s.name, g.name AS group_name
        FROM students AS s
        JOIN groups AS g ON g.id = s.group_id
        JOIN grades AS gr ON gr.student_id = s.id
        GROUP BY s.id, s.name, g.name
        HAVING MIN(gr.mark) = 2
        ORDER BY g.name, s.name, s.id
        '''
    )
    return [dict(row) for row in cursor.fetchall()]
