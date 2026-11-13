from flask import request

from . import bp, service


@bp.get("/api/students")
def get_items():

    group_value = request.args.get("group_id")
    query = request.args.get("q", "").strip()

    group_id = None

    if group_value:
        try:
            group_id = int(group_value)
        except ValueError:
            raise ValueError("Некорректный ID группы.")

        if group_id <= 0:
            raise ValueError("Некорректный ID группы.")

    return {
        "items": service.list_students(group_id, query)
    }


@bp.post("/api/students")
@bp.post("/api/students/<int:record_id>")
def save_item(record_id=None):

    data = request.get_json(silent=True)

    if not isinstance(data, dict):
        raise ValueError("Ожидается JSON-объект.")

    item = service.save_student(
        data.get("name"),
        data.get("group_id"),
        data.get("email"),
        record_id
    )

    return {
        "item": item
    }, 201 if record_id is None else 200


@bp.delete("/api/students/<int:record_id>")
def delete_item(record_id):

    service.delete_student(record_id)

    return "", 204