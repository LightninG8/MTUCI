import {requestJSON} from "/static/js/api.js";
import {cell, message, busy, emptyRow, fillGroups, avatarCell, meterCell} from "/static/js/ui.js";

const API = "/api/journal";
const head = document.querySelector("#table-head");
const body = document.querySelector("#table-body");
const groupSelect = document.querySelector("#group-select");
const editor = document.querySelector("#editor");
const form = document.querySelector("#grade-form");
const markSelect = document.querySelector("#grade-mark");
const deleteButton = document.querySelector("#delete-grade");

let journal = null;      // последний ответ GET /api/journal
let marks = new Map();   // "studentId:subjectId" -> оценка
let current = null;      // выбранная ячейка {student, subject}

/** Ставит или меняет оценку. */
async function saveGrade(payload) {
    return requestJSON(API + "/grades", "POST", payload);
}

/** Очищает ячейку журнала. */
async function deleteGrade(studentId, subjectId) {
    return requestJSON(API + "/grades/" + studentId + "/" + subjectId, "DELETE");
}

/** Строит таблицу: студенты в строках, предметы в столбцах. */
function render() {
    head.replaceChildren(); body.replaceChildren();
    const {students, subjects} = journal;
    const headRow = document.createElement("tr");
    const first = document.createElement("th"); first.textContent = "Студент"; headRow.append(first);
    for (const subject of subjects) {
        const th = document.createElement("th"); th.textContent = subject.name; th.className = "grade-cell"; headRow.append(th);
    }
    const avgHead = document.createElement("th"); avgHead.textContent = "Средний балл"; avgHead.className = "number"; headRow.append(avgHead);
    head.append(headRow);
    if (!students.length) { emptyRow(body, subjects.length + 2, "В группе нет студентов"); return; }
    for (const student of students) {
        const row = document.createElement("tr");
        row.append(avatarCell(student.name));
        const own = [];
        for (const subject of subjects) {
            const mark = marks.get(student.id + ":" + subject.id);
            if (mark !== undefined) own.push(mark);
            const td = document.createElement("td"); td.className = "grade-cell";
            const button = document.createElement("button"); button.type = "button";
            button.className = "grade-button" + (mark === undefined ? " empty" : mark === 2 ? " failing" : " mark-" + mark);
            button.textContent = mark === undefined ? "+" : String(mark);
            button.dataset.student = String(student.id); button.dataset.subject = String(subject.id);
            button.setAttribute("aria-label", student.name + ", " + subject.name + ": " + (mark === undefined ? "нет оценки" : mark));
            td.append(button); row.append(td);
        }
        row.append(meterCell(own.length ? own.reduce(function(a, b) { return a + b; }, 0) / own.length : null, 5, {mark: true, digits: 2}));
        body.append(row);
    }
}

/** Загружает журнал выбранной группы и перестраивает таблицу. */
async function load(successText = "") {
    if (!groupSelect.value) { head.replaceChildren(); emptyRow(body, 1, "Групп нет"); return; }
    busy(true); message("Загрузка...");
    try {
        journal = await requestJSON(API + "?group_id=" + encodeURIComponent(groupSelect.value));
        marks = new Map(journal.grades.map(function(g) { return [g.student_id + ":" + g.subject_id, g.mark]; }));
        render(); message(successText);
    } catch (error) {
        message((successText ? successText + " Обновление таблицы не удалось. " : "") + error.message, true);
        if (!journal) emptyRow(body, 1, "Данные не загружены");
    } finally { busy(false); }
}

/** Открывает форму для выбранной ячейки. */
function openEditor(studentId, subjectId) {
    const student = journal.students.find(function(s) { return s.id === studentId; });
    const subject = journal.subjects.find(function(s) { return s.id === subjectId; });
    if (!student || !subject) return;
    current = {student: student, subject: subject};
    const mark = marks.get(studentId + ":" + subjectId);
    markSelect.value = mark === undefined ? "5" : String(mark);
    deleteButton.hidden = mark === undefined;   // удалять нечего
    document.querySelector("#editor-title").textContent = mark === undefined ? "Выставление оценки" : "Изменение оценки";
    document.querySelector("#editor-context").textContent = student.name + " · " + subject.name;
    editor.hidden = false; message(); markSelect.focus();
}

function closeEditor() { editor.hidden = true; current = null; }

body.addEventListener("click", function(event) {
    const button = event.target.closest("button.grade-button"); if (!button) return;
    openEditor(Number(button.dataset.student), Number(button.dataset.subject));
});

form.addEventListener("submit", async function(event) {
    event.preventDefault(); if (!current) return;
    busy(true);
    try {
        await saveGrade({student_id: current.student.id, subject_id: current.subject.id, mark: Number(markSelect.value)});
        closeEditor(); await load("Оценка сохранена.");
    } catch (error) { message(error.message, true); }
    finally { busy(false); }
});

deleteButton.addEventListener("click", async function() {
    if (!current) return; busy(true);
    try {
        await deleteGrade(current.student.id, current.subject.id);
        closeEditor(); await load("Оценка удалена.");
    } catch (error) { message(error.message, true); }
    finally { busy(false); }
});

document.querySelector("#cancel-edit").addEventListener("click", function() { closeEditor(); message(); });
groupSelect.addEventListener("change", function() { closeEditor(); journal = null; load(); });

(async function start() {
    busy(true);
    try { fillGroups(groupSelect, (await requestJSON("/api/groups")).items); }
    catch (error) { message("Не удалось загрузить группы. " + error.message, true); }
    finally { busy(false); }
    await load();
})();
