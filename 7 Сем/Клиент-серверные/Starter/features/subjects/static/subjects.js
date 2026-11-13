import {requestJSON} from "/static/js/api.js";

import {cell, message, busy, actions, emptyRow, avatarCell} from "/static/js/ui.js";

const API = "/api/subjects";

const body = document.querySelector("#table-body");
const editor = document.querySelector("#editor");
const form = document.querySelector("#edit-form");

const idInput = document.querySelector("#record-id");
const nameInput = document.querySelector("#record-name");

const confirmation = document.querySelector("#confirmation");

let items = [];
let pendingId = null;
let loaded = false;


async function saveRecord(recordId, payload) {

    const url = recordId
        ? API + "/" + recordId
        : API;

    return requestJSON(url, "POST", payload);
}


async function deleteRecord(recordId) {

    return requestJSON(
        API + "/" + recordId,
        "DELETE"
    );
}


function render() {

    body.replaceChildren();

    if (!items.length) {
        emptyRow(body, 2);
        return;
    }

    for (const item of items) {

        const row = document.createElement("tr");

        row.append(avatarCell(item.name));

        const td = document.createElement("td");

        td.append(actions(item.id));

        row.append(td);

        body.append(row);
    }
}


async function load(successText = "") {

    busy(true);

    try {

        items = (await requestJSON(API)).items;

        loaded = true;

        render();

        message(successText);

    } catch (error) {

        message(error.message, true);

        if (!loaded) {
            emptyRow(body, 2, "Данные не загружены");
        }

    } finally {

        busy(false);
    }
}


function openEditor(item = null) {

    form.reset();

    idInput.value = item ? String(item.id) : "";

    nameInput.value = item ? item.name : "";

    document.querySelector("#editor-title").textContent =
        item ? "Изменение предмета" : "Добавление предмета";

    editor.hidden = false;

    confirmation.hidden = true;

    message();

    nameInput.focus();
}


document.querySelector("#add-record").addEventListener(
    "click",
    () => openEditor()
);


document.querySelector("#cancel-edit").addEventListener(
    "click",
    () => {
        editor.hidden = true;
        message();
    }
);


form.addEventListener("submit", async function(event) {

    event.preventDefault();

    const name = nameInput.value.trim();

    if (!name) {
        message("Введите название предмета.", true);
        return;
    }

    busy(true);

    try {

        await saveRecord(
            idInput.value ? Number(idInput.value) : null,
            {name: name}
        );

        editor.hidden = true;

        await load("Изменения сохранены.");

    } catch (error) {

        message(error.message, true);

    } finally {

        busy(false);
    }
});


body.addEventListener("click", function(event) {

    const button = event.target.closest("button[data-action]");

    if (!button) return;

    const item = items.find(
        record => record.id === Number(button.dataset.id)
    );

    if (!item) return;

    if (button.dataset.action === "edit") {

        openEditor(item);

        return;
    }

    pendingId = item.id;

    editor.hidden = true;

    confirmation.hidden = false;

    document.querySelector("#confirmation-text").textContent =
        "Удалить предмет «" + item.name + "»?";

});


document.querySelector("#cancel-delete").addEventListener(
    "click",
    () => {
        pendingId = null;
        confirmation.hidden = true;
    }
);


document.querySelector("#confirm-delete").addEventListener(
    "click",
    async function() {

        if (pendingId === null) return;

        busy(true);

        try {

            await deleteRecord(pendingId);

            pendingId = null;

            confirmation.hidden = true;

            await load("Предмет удалён.");

        } catch (error) {

            message(error.message, true);

        } finally {

            busy(false);
        }
    }
);


load();