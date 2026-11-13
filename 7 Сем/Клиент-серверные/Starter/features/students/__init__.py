from flask import Blueprint

bp = Blueprint(
    "students",
    __name__,
    template_folder="templates",
    static_folder="static",
    static_url_path="/students/static",
)

from . import pages, api