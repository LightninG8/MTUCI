import {requestJSON} from "/static/js/api.js";
import {cell, message, busy, actions, emptyRow, fillGroups, avatarCell, chipCell} from "/static/js/ui.js";

const API = "/api/students";
const body = document.querySelector("#table-body");
const editor = document.querySelector("#editor");
const form = document.querySelector("#edit-form");
const idInput = document.querySelector("#record-id");
const nameInput = document.querySelector("#record-name");
const groupSelect = document.querySelector("#record-group");
const emailInput = document.querySelector("#record-email");
const filter = document.querySelector("#group-filter");
const search = document.querySelector("#search");
const confirmation = document.querySelector("#confirmation");

let items = [], groups = [], pendingId = null, loaded = false, requestNumber = 0;

/** Отправляет добавление или изменение студента. */
async function saveRecord(recordId, payload) {
    const url = recordId
        ? API + "/" + recordId
        : API;

    return requestJSON(url, "POST", payload);
}

/** Отправляет удаление студента вместе с оценками. */
async function deleteRecord(recordId) {
    return requestJSON(
        API + "/" + recordId,
        "DELETE"
    );
}

/** Строит таблицу по массиву из API. */
function render() {
    body.replaceChildren();
    if (!items.length) { emptyRow(body, 4, "Студенты не найдены"); return; }
    for (const item of items) {
        const row = document.createElement("tr");
        row.append(avatarCell(item.name), chipCell(item.group_name), cell(item.email || null, "muted"));
        const td = document.createElement("td");
        td.append(actions(item.id));
        row.append(td);
        body.append(row);
    }
}

/** Загружает студентов с учётом фильтра группы и строки поиска.
 *  quiet=true не блокирует форму, чтобы не терять фокус при вводе. */
async function load(successText = "", quiet = false) {
    const number = ++requestNumber;
    const params = new URLSearchParams();
    if (filter.value) params.set("group_id", filter.value);
    if (search.value.trim()) params.set("q", search.value.trim());
    const url = params.toString() ? API + "?" + params : API;
    if (!quiet) { busy(true); message("Загрузка..."); }
    try {
        const data = await requestJSON(url);
        if (number !== requestNumber) return;  // пришёл устаревший ответ
        items = data.items; loaded = true; render(); message(successText);
    } catch (error) {
        if (number !== requestNumber) return;
        message((successText ? successText + " Обновление списка не удалось. " : "") + error.message, true);
        if (!loaded) emptyRow(body, 4, "Данные не загружены");
    } finally { if (!quiet) busy(false); }
}

/** Загружает группы для фильтра и для формы. */
async function loadGroups() {
    groups = (await requestJSON("/api/groups")).items;
    fillGroups(filter, groups, true);
    fillGroups(groupSelect, groups);
}

/** Открывает одну форму для создания и изменения. */
function openEditor(item = null) {
    form.reset();
    idInput.value = item ? String(item.id) : "";
    nameInput.value = item ? item.name : "";
    emailInput.value = item && item.email ? item.email : "";
    if (item) groupSelect.value = String(item.group_id);
    else if (filter.value) groupSelect.value = filter.value;
    document.querySelector("#editor-title").textContent = item ? "Изменение студента" : "Добавление студента";
    editor.hidden = false; confirmation.hidden = true; message(); nameInput.focus();
}

document.querySelector("#add-record").addEventListener("click", function() { openEditor(); });
document.querySelector("#cancel-edit").addEventListener("click", function() { editor.hidden = true; message(); });

form.addEventListener("submit", async function(event) {
    event.preventDefault();
    const name = nameInput.value.trim();
    if (!name) { message("Введите ФИО студента.", true); nameInput.focus(); return; }
    if (!groupSelect.value) { message("Выберите группу.", true); return; }
    busy(true);
    try {
        await saveRecord(idInput.value ? Number(idInput.value) : null, {
            name: name,
            group_id: Number(groupSelect.value),  // из select приходит строка
            email: emailInput.value.trim() || null,
        });
        editor.hidden = true; form.reset();
        await load("Изменения сохранены.");
    } catch (error) { message(error.message, true); }
    finally { busy(false); }
});

body.addEventListener("click", function(event) {
    const button = event.target.closest("button[data-action]"); if (!button) return;
    const item = items.find(function(record) { return record.id === Number(button.dataset.id); });
    if (!item) return;
    if (button.dataset.action === "edit") { openEditor(item); return; }
    pendingId = item.id; editor.hidden = true; confirmation.hidden = false;
    document.querySelector("#confirmation-text").textContent =
        "Удалить студента «" + item.name + "» вместе со всеми его оценками?";
    message();
});

document.querySelector("#cancel-delete").addEventListener("click", function() { pendingId = null; confirmation.hidden = true; });
document.querySelector("#confirm-delete").addEventListener("click", async function() {
    if (pendingId === null) return;
    busy(true);
    try {
        await deleteRecord(pendingId);
        confirmation.hidden = true; pendingId = null;
        await load("Студент и его оценки удалены.");
    } catch (error) { message(error.message, true); }
    finally { busy(false); }
});

filter.addEventListener("change", function() { load(); });
let timer = null;
search.addEventListener("input", function() {
    clearTimeout(timer);
    timer = setTimeout(function() { load("", true); }, 250);
});

(async function start() {
    busy(true);
    try { await loadGroups(); }
    catch (error) { message("Не удалось загрузить группы. " + error.message, true); }
    finally { busy(false); }
    await load();
})();
