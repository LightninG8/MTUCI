"""HTTP и JSON раздела journal. SQL и бизнес-правила находятся ниже."""

from flask import request
from . import bp, service


@bp.get("/api/journal")
def get_items():
    """Возвращает данные журнала выбранной группы.

    Returns:
        dict: group, students, subjects, grades.
    """
    value = request.args.get("group_id")
    try:
        group_id = int(value)
    except (TypeError, ValueError):
        raise ValueError("Укажите корректный ID группы.")
    if group_id <= 0:
        raise ValueError("Укажите корректный ID группы.")
    return service.get_journal(group_id)


@bp.post("/api/journal/grades")
def save_item():
    """Читает JSON и сохраняет оценку.

    Returns:
        tuple: Объект item и статус 200.
    """
    data = request.get_json(silent=True)
    if not isinstance(data, dict):
        raise ValueError("Ожидается JSON-объект.")
    item = service.save_grade(
        data.get("student_id"), data.get("subject_id"), data.get("mark")
    )
    return {"item": item}, 200


@bp.delete("/api/journal/grades/<int:student_id>/<int:subject_id>")
def delete_item(student_id, subject_id):
    """Удаляет оценку и возвращает пустой ответ.

    Returns:
        tuple: Пустое тело и статус 204.
    """
    service.delete_grade(student_id, subject_id)
    return "", 204
