from flask import render_template
from . import bp


@bp.get("/subjects")
def index():
    return render_template(
        "subjects/index.html",
        active_page="subjects"
    )