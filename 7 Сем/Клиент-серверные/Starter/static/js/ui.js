/** Создаёт ячейку, выводя значения через textContent. */
export function cell(value, className = "") {
  const td = document.createElement("td");
  td.textContent = value === null || value === undefined ? "—" : String(value);
  td.className = className;
  return td;
}

/** Показывает сообщение о результате запроса. */
export function message(text = "", error = false) {
  const box = document.querySelector("#status");
  box.textContent = text; box.hidden = !text;
  box.classList.toggle("error", error);
  box.setAttribute("role", error ? "alert" : "status");
}

/** Блокирует повторные действия на время запроса. */
export function busy(value) {
  document.querySelector("main").setAttribute("aria-busy", String(value));
  document.querySelectorAll("main button, main input, main select").forEach(function(control) { control.disabled = value; });
}

/** Создаёт кнопки строки для делегирования событий. */
export function actions(id) {
  const box = document.createElement("div"); box.className = "row-actions";
  for (const action of ["edit", "delete"]) {
    const button = document.createElement("button"); button.type = "button";
    button.dataset.action = action; button.dataset.id = String(id);
    button.textContent = action === "edit" ? "Изменить" : "Удалить";
    if (action === "delete") button.className = "button-danger";
    box.append(button);
  }
  return box;
}

/** Выводит строку пустого состояния таблицы. */
export function emptyRow(body, columns, text = "Записей нет") {
  body.replaceChildren(); const row = document.createElement("tr");
  const td = cell(text, "empty-row"); td.colSpan = columns;
  row.append(td); body.append(row);
}

/** Заполняет выбор группы, сохраняя доступный прежний вариант. */
export function fillGroups(select, groups, includeAll = false) {
  const previous = select.value; select.replaceChildren();
  if (includeAll) { const option = document.createElement("option"); option.value = ""; option.textContent = "Все группы"; select.append(option); }
  for (const group of groups) {
    const option = document.createElement("option"); option.value = String(group.id); option.textContent = group.name; select.append(option);
  }
  if (Array.from(select.options).some(function(option) { return option.value === previous; })) select.value = previous;
  else if (includeAll) select.value = "";
}

/** Оттенок из строки: одинаковый текст всегда получает одинаковый цвет (синий–фиолетовый диапазон). */
function hueOf(text) {
  let hash = 2166136261;
  for (const ch of String(text)) hash = Math.imul(hash ^ ch.codePointAt(0), 16777619);
  hash = Math.imul(hash ^ (hash >>> 15), 2246822507);
  hash ^= hash >>> 13;
  return 165 + ((hash >>> 0) % 170);   // от бирюзового через синий и фиолетовый к розовому
}

/** Ячейка с круглым значком-инициалами и именем. */
export function avatarCell(name) {
  const td = document.createElement("td");
  const box = document.createElement("div"); box.className = "person";
  const badge = document.createElement("span"); badge.className = "avatar"; badge.setAttribute("aria-hidden", "true");
  badge.style.setProperty("--h", String(hueOf(name)));
  badge.textContent = String(name).split(/\s+/).slice(0, 2).map(function(w) { return w[0] || ""; }).join("").toUpperCase();
  const label = document.createElement("span"); label.textContent = name;
  box.append(badge, label); td.append(box);
  return td;
}

/** Ячейка с цветной «таблеткой» (например, название группы). */
export function chipCell(text) {
  const td = document.createElement("td");
  if (text === null || text === undefined || text === "") { td.textContent = "—"; return td; }
  const chip = document.createElement("span"); chip.className = "chip";
  chip.style.setProperty("--h", String(hueOf(text))); chip.textContent = String(text);
  td.append(chip); return td;
}

/** Ячейка с числом и полосой-индикатором. Для среднего балла цвет полосы зависит от значения. */
export function meterCell(value, max, options = {}) {
  const td = document.createElement("td"); td.className = "number";
  if (value === null || value === undefined) { td.textContent = "—"; return td; }
  const number = Number(value);
  const box = document.createElement("div"); box.className = "meter";
  const track = document.createElement("span"); track.className = "meter-track"; track.setAttribute("aria-hidden", "true");
  const bar = document.createElement("span"); bar.className = "meter-bar";
  if (options.mark) bar.classList.add(number >= 4.5 ? "is-great" : number >= 3.5 ? "is-good" : number >= 3 ? "is-mid" : "is-low");
  bar.style.width = Math.max(0, Math.min(100, number / max * 100)) + "%";
  track.append(bar);
  const label = document.createElement("span"); label.className = "meter-value";
  label.textContent = options.digits === undefined ? String(number) : number.toFixed(options.digits);
  box.append(track, label); td.append(box);
  return td;
}
