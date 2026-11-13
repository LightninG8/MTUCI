"""HTTP и JSON раздела reports."""

from flask import request
from . import bp, service


@bp.get("/api/reports")
def get_items():
    """Возвращает отчёт по параметру kind.

    Returns:
        dict: kind и items.
    """
    return service.build_report(request.args.get("kind"))
