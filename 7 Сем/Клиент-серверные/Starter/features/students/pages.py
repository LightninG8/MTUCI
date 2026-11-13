from flask import render_template
from . import bp


@bp.get("/students")
def index():
    return render_template(
        "students/index.html",
        active_page="students"
    )