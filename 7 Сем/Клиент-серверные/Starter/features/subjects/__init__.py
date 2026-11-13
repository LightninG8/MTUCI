from flask import Blueprint

bp = Blueprint(
    "subjects",
    __name__,
    template_folder="templates",
    static_folder="static",
    static_url_path="/subjects/static",
)

from . import pages, api