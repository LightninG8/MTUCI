import {requestJSON} from "/static/js/api.js";
import {cell, message, busy, emptyRow, avatarCell, chipCell, meterCell} from "/static/js/ui.js";

const API = "/api/reports";
const kindSelect = document.querySelector("#report-kind");
const head = document.querySelector("#table-head");
const body = document.querySelector("#table-body");
let loaded = false, requestNumber = 0;

/** Описание столбцов: заголовок, поле, класс и форматирование значения. */
const COLUMNS = {
    groups: [
        {title: "Группа", field: "name", kind: "chip"},
        {title: "Студентов", field: "student_count", className: "number"},
        {title: "Оценок", field: "grade_count", className: "number"},
        {title: "Средний балл", field: "average_mark", className: "number", kind: "meter"},
    ],
    no_grades: [
        {title: "Студент", field: "name", kind: "person"},
        {title: "Группа", field: "group_name", kind: "chip"},
    ],
    failing: [
        {title: "Студент", field: "name", kind: "person"},
        {title: "Группа", field: "group_name", kind: "chip"},
    ],
};

const summary = document.querySelector("#summary");
const LABELS = {no_grades: "студентов без оценок", failing: "студентов с двойками"};

/** Плитка с крупным числом и подписью. */
function tile(value, label, accent = false) {
  const box = document.createElement("div"); box.className = "stat" + (accent ? " stat-accent" : "");
  const number = document.createElement("strong"); number.textContent = value;
  const text = document.createElement("span"); text.textContent = label;
  box.append(number, text); return box;
}

/** Показывает итоги над таблицей. */
function renderSummary(kind, items) {
  summary.replaceChildren();
  if (kind === "groups") {
    const students = items.reduce(function(sum, g) { return sum + g.student_count; }, 0);
    const grades = items.reduce(function(sum, g) { return sum + g.grade_count; }, 0);
    const weighted = items.reduce(function(sum, g) { return sum + (g.average_mark === null ? 0 : g.average_mark * g.grade_count); }, 0);
    summary.append(tile(String(students), "студентов в журнале"), tile(String(grades), "оценок выставлено"),
                   tile(grades ? (weighted / grades).toFixed(2) : "—", "средний балл по всем группам", true));
  } else {
    summary.append(tile(String(items.length), LABELS[kind] || "записей", true));
  }
  summary.hidden = false;
}

/** Строит таблицу выбранного отчёта. */
function render(kind, items) {
    const columns = COLUMNS[kind];
    head.replaceChildren(); body.replaceChildren();
    const row = document.createElement("tr");
    for (const column of columns) {
        const th = document.createElement("th"); th.textContent = column.title;
        if (column.className) th.className = column.className;
        row.append(th);
    }
    head.append(row);
    if (!items.length) { emptyRow(body, columns.length, "По отчёту записей нет"); return; }
    for (const item of items) {
        const tr = document.createElement("tr");
        for (const column of columns) {
            const value = item[column.field];
            if (column.kind === "person") tr.append(avatarCell(value));
            else if (column.kind === "chip") tr.append(chipCell(value));
            else if (column.kind === "meter") tr.append(meterCell(value, 5, {mark: true, digits: 2}));
            else tr.append(cell(column.format ? column.format(value) : value, column.className || ""));
        }
        body.append(tr);
    }
}

/** Загружает отчёт; устаревший ответ при быстром переключении отбрасывается. */
async function load() {
    const number = ++requestNumber, kind = kindSelect.value;
    busy(true); message("Загрузка...");
    try {
        const data = await requestJSON(API + "?kind=" + encodeURIComponent(kind));
        if (number !== requestNumber) return;
        loaded = true; render(data.kind, data.items); renderSummary(data.kind, data.items); message();
    } catch (error) {
        if (number !== requestNumber) return;
        message(error.message, true);
        if (!loaded) emptyRow(body, 1, "Данные не загружены");
    } finally { if (number === requestNumber) busy(false); }
}

kindSelect.addEventListener("change", load);
load();
