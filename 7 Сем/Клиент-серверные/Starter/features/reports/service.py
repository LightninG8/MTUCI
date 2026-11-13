"""Выбор отчёта и работа с соединением раздела reports."""

from db import connect_db
from . import repo

REPORTS = {
    "groups": repo.group_report,
    "no_grades": repo.no_grades_report,
    "failing": repo.failing_report,
}


def build_report(kind):
    """Выполняет отчёт указанного вида.

    Args:
        kind (str): groups, no_grades или failing.

    Returns:
        dict: kind и items.

    Raises:
        ValueError: Неизвестный вид отчёта.
    """
    if kind not in REPORTS:
        raise ValueError("Неизвестный вид отчёта.")
    con = connect_db()
    try:
        return {"kind": kind, "items": REPORTS[kind](con)}
    finally:
        con.close()
