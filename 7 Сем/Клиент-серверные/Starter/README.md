# Электронный журнал

Стартовый проект студента. Три недели, шесть лабораторных пар по 90 минут, всего 12 академических часов.
Проект использует Flask, Jinja2, обычный JavaScript и модуль Python sqlite3.
Python 3.11 или новее. В браузере должен быть включён JavaScript.

## Запуск в Debian Linux

Один раз подготовьте системные пакеты:

~~~bash
sudo apt update
sudo apt install python3 python3-venv python3-pip sqlite3 unzip
~~~

После распаковки архива:

~~~bash
cd journal-starter
python3 -m venv .venv
source .venv/bin/activate
python -m pip install -r requirements.txt
python prepare_db.py
python check_db.py --baseline
python app.py
~~~

Откройте http://127.0.0.1:5000/. Остановка сервера: Ctrl+C.
Повторный запуск после закрытия терминала:

~~~bash
cd journal-starter
source .venv/bin/activate
python app.py
~~~

## Исходная база и восстановление

База instance/journal.db создаётся явно. Исходные данные: 3 группы БЭИ2301,
БЭИ2302, БЭИ2303, по 20 студентов, 3 предмета и 162 оценки. Без оценок остаются
студенты с ID 10, 20, 30, 40, 50, 60. Группы и предметы можно добавлять.
Оценка одна на пару студент и предмет. Пустая ячейка означает отсутствие строки,
а не оценку 0. Email необязателен, одинаковые имена и email допустимы.

Для восстановления остановите сервер и выполните:

~~~bash
python prepare_db.py
python check_db.py --baseline
python app.py
~~~

Восстановление заменяет текущие учебные данные исходными. Схема и заполнение
выполняются одной транзакцией. При SQL-ошибке скрипт откатывает изменения.
Обработчик with con фиксирует или откатывает транзакцию, а соединение
дополнительно закрывается в finally. Репозитории commit не вызывают.

## Консоль SQLite

Во втором терминале из корня проекта:

~~~bash
sqlite3 instance/journal.db
~~~

Внутри консоли:

~~~sql
PRAGMA foreign_keys = ON;
PRAGMA foreign_keys;
.headers on
.mode column
SELECT g.name, COUNT(s.id) AS students
FROM groups AS g LEFT JOIN students AS s ON s.group_id = g.id
GROUP BY g.id, g.name;
PRAGMA foreign_key_check;
.quit
~~~

foreign_keys должен вернуть 1; foreign_key_check не должен вернуть строк.
Настройка включается для каждого соединения отдельно до BEGIN.

## Вертикальные срезы

Каждый каталог features/groups, subjects, students, journal, reports
объединяет один функциональный раздел. Blueprint создаётся в __init__.py
и регистрируется в app.py. pages.py возвращает только HTML. api.py читает
HTTP и JSON. service.py проверяет бизнес-правила, открывает соединение и
управляет транзакцией. repo.py выполняет SQL через переданное соединение.
HTML и JS находятся внутри соответствующего среза.

Общие части: db.py, templates/base.html, templates/partials/header.html,
templates/partials/footer.html, static/css/styles.css, static/js/api.js,
static/js/ui.js. Переходы между разделами обычными ссылками. Действия
пользователя обрабатывает JS, который вызывает JSON API.

## План шести пар

1. Вертикальный срез и Blueprint, запуск, БД и готовый пример групп.
2. Предметы: POST, DELETE, сервис, SQL и два вызова из JS.
3. Студенты: сохранение, перевод и атомарное удаление с оценками.
4. Журнал: SELECT, INSERT либо UPDATE оценки и удаление оценки.
5. Три отчёта: GROUP BY, HAVING, LEFT JOIN и COUNT DISTINCT.
6. Полный откат, восстановление, README и итоговая демонстрация.

## Проверка отката

После реализации удаления студента:

~~~bash
python check_rollback.py
~~~

Скрипт создаёт временную копию БД через sqlite3.backup, вызывает тот же
service.delete_student, что и API, и вызывает учебный сбой после удаления
оценок. Параметр simulate_error доступен только в Python и не принимается
HTTP API. Скрипт сравнивает все строки четырёх таблиц до и после отката,
затем проверяет обычное удаление на копии. Рабочая база остаётся прежней.

## Контракты API

GET /api/groups и GET /api/subjects возвращают {"items": [...]}.
POST /api/groups или /api/subjects создаёт запись с JSON {"name": "..."}.
POST по адресу /api/groups/ID или /api/subjects/ID переименовывает запись.
Ответ: {"item": {"id": ..., "name": "..."}}, статус 201 при создании и 200 при изменении.
DELETE /api/groups/ID и /api/subjects/ID возвращает пустой ответ 204.

GET /api/students возвращает items; можно передать group_id и q.
POST /api/students и POST /api/students/ID принимают name, group_id, email.
DELETE /api/students/ID удаляет студента и оценки одной транзакцией.

GET /api/journal?group_id=1 возвращает group, students, subjects, grades.
POST /api/journal/grades принимает student_id, subject_id, mark и возвращает item, статус 200.
DELETE /api/journal/grades/STUDENT_ID/SUBJECT_ID возвращает 204.
Повторное удаление пустой оценки также возвращает 204, если родители существуют.

GET /api/reports?kind=groups, no_grades или failing возвращает kind и items.
В отчёте групп поля id, name, student_count, grade_count, average_mark.
В остальных отчётах id, name, group_name. Средний балл пустой группы равен null.
Ошибки полей и зависимостей: 400 и {"error": "..."}; отсутствующая запись: 404.
Техническая ошибка БД: 500. Тело ответа 204 разбирать как JSON не нужно.

## Сдача

Сохраните проект в Git и фиксируйте результат после каждого этапа.
К сдаче подготовьте исходники, схему и данные, рабочий скрипт восстановления,
команды запуска и результаты проверок. Дополните этот README описанием
самостоятельно реализованных функций. Покажите перевод без потери оценок,
запрет удаления связанных справочников, отчёты и полный откат.

## Справочные материалы

Flask Blueprint: https://flask.palletsprojects.com/en/stable/blueprints/
Python sqlite3: https://docs.python.org/3.11/library/sqlite3.html
Внешние ключи SQLite: https://www.sqlite.org/foreignkeys.html
Fetch: https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch

## Готовая и самостоятельная части

Группы реализованы полностью. Все HTML/CSS, чтение предметов и студентов, поиск, фильтры и построение журнала готовы. В функциях с TODO LAB2–LAB5 стоят явные заглушки. Это ожидаемое состояние стартового проекта. После подключения JS к API незавершённый сервер отвечает 501. Отчёты начнут возвращать данные после выполнения LAB5.

Изменяйте только отмеченные функции; сохраняйте их имена и параметры. Документация полного задания находится в методичке студента.

### LAB2

- features/subjects/api.py — save_item
- features/subjects/api.py — delete_item
- features/subjects/service.py — save_subject
- features/subjects/service.py — delete_subject
- features/subjects/repo.py — insert_subject
- features/subjects/repo.py — update_subject
- features/subjects/repo.py — delete_subject
- features/subjects/static/subjects.js — saveRecord
- features/subjects/static/subjects.js — deleteRecord

### LAB3

- features/students/api.py — save_item
- features/students/api.py — delete_item
- features/students/service.py — save_student
- features/students/service.py — delete_student
- features/students/repo.py — insert_student
- features/students/repo.py — update_student
- features/students/repo.py — delete_grades
- features/students/repo.py — delete_student
- features/students/static/students.js — saveRecord
- features/students/static/students.js — deleteRecord

### LAB4

- features/journal/api.py — save_item
- features/journal/api.py — delete_item
- features/journal/service.py — save_grade
- features/journal/service.py — delete_grade
- features/journal/repo.py — insert_grade
- features/journal/repo.py — update_grade
- features/journal/repo.py — delete_grade
- features/journal/static/journal.js — saveGrade
- features/journal/static/journal.js — deleteGrade

### LAB5

- features/reports/repo.py — group_report
- features/reports/repo.py — no_grades_report
- features/reports/repo.py — failing_report


## Самостоятельно реализованные функции

Ниже перечислено, что дописано сверх стартового проекта. Каждый пункт можно
показать на защите в браузере и в терминале.

### LAB2. Предметы
- `service.save_subject` — проверка названия, `INSERT` либо `UPDATE` в одной транзакции,
  `IntegrityError` превращается в понятное «такое название уже существует».
- `service.delete_subject` — удаление; предмет с оценками удалить нельзя (внешний ключ),
  пользователь видит сообщение, а не ошибку 500.
- `repo.insert_subject`, `repo.update_subject`, `repo.delete_subject` — параметризованный SQL.
- `api.save_item`, `api.delete_item` — чтение JSON, статусы 201/200/204.
- `subjects.js`: `saveRecord`, `deleteRecord` — два вызова `requestJSON`.

### LAB3. Студенты
- `repo.list_students` — список с названием группы, фильтр по группе; поиск по ФИО
  выполняется в Python через `casefold()`, потому что `LIKE` в SQLite не различает
  регистр только для латиницы.
- `repo.get_student`, `repo.insert_student`, `repo.update_student`.
- `service.save_student` — проверка ФИО (до 50 символов), группы и email; пустой email
  сохраняется как `NULL`. Перевод в другую группу меняет только `group_id`, поэтому
  оценки сохраняются.
- `service.delete_student` + `repo.delete_grades` + `repo.delete_student` — оценки и студент
  удаляются одной транзакцией; при сбое между двумя `DELETE` откатывается всё.
- `students.js` — загрузка групп, фильтр, поиск с задержкой 250 мс, форма, подтверждение удаления.

### LAB4. Журнал
- `GET /api/journal?group_id=` — студенты группы, предметы и существующие оценки.
- `service.save_grade` — проверка оценки (только 2–5), существования студента и предмета;
  если оценки нет — `INSERT`, если есть — `UPDATE`. Дубль пары невозможен благодаря `UNIQUE`.
- `service.delete_grade` — очищает ячейку; повторное удаление возвращает 204.
- `journal.js` — таблица «студент × предмет», форма оценки, кнопка «Удалить оценку»
  только для заполненных ячеек, подсветка двоек.

### LAB5. Отчёты
- `repo.group_report` — `LEFT JOIN` + `GROUP BY` + `COUNT(DISTINCT s.id)`; средний балл
  пустой группы равен `null`.
- `repo.no_grades_report` — `LEFT JOIN ... WHERE gr.id IS NULL`.
- `repo.failing_report` — `GROUP BY` студента + `HAVING MIN(gr.mark) = 2`.

### LAB6. Проверки и восстановление
- `python check_api.py` — сквозная проверка всех контрактов API на копии БД.
- `python check_rollback.py` — полный откат удаления студента при сбое.
- `python prepare_db.py` + `python check_db.py --baseline` — восстановление исходных данных.

## Результаты проверок

~~~text
$ python check_db.py --baseline
foreign_keys: 1
foreign_key_check: 0
groups: 3 / students: 60 / subjects: 3 / grades: 162
students_without_grades: 6
PASS: база прошла проверку

$ python check_api.py       -> 8 строк PASS (страницы, предметы, группы, студенты, журнал, отчёты, удаление)
$ python check_rollback.py  -> 3 строки PASS (откат, обычное удаление, рабочая база не изменена)
~~~
