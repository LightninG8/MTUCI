"""HTML-страница раздела Отчёты без запросов к БД."""

from flask import render_template
from . import bp


@bp.get("/reports")
def index():
    """Возвращает структуру страницы и подключение JavaScript.

    Returns:
        str: HTML с общими шапкой и подвалом.
    """
    return render_template("reports/index.html", active_page="reports")
