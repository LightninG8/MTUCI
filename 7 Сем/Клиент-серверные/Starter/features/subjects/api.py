from flask import request

from . import bp, service


@bp.get("/api/subjects")
def get_items():
    return {
        "items": service.list_subjects()
    }


@bp.post("/api/subjects")
@bp.post("/api/subjects/<int:record_id>")
def save_item(record_id=None):

    data = request.get_json(silent=True)

    if not isinstance(data, dict):
        raise ValueError("Ожидается JSON-объект.")

    item = service.save_subject(
        data.get("name"),
        record_id
    )

    status = 201 if record_id is None else 200

    return {"item": item}, status


@bp.delete("/api/subjects/<int:record_id>")
def delete_item(record_id):

    service.delete_subject(record_id)

    return "", 204