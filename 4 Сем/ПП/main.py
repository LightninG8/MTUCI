import cv2
import numpy as np
import requests
from ultralytics import YOLO

# === Настройки Telegram ===
TOKEN = "7907985921:AAFAYj2Xv-qHxxH38z6loKltgPI0tY1ZM2o"
chat_id = "777795628"

def send_photo_file(chat_id, img_path):
    with open(img_path, 'rb') as f:
        files = {'photo': f}
        requests.post(f'https://api.telegram.org/bot{TOKEN}/sendPhoto?chat_id={chat_id}', files=files)

def send_telegram_message(message):
    requests.get(f'https://api.telegram.org/bot{TOKEN}/sendMessage?chat_id={chat_id}&text={message}').json()

# === Путь до видео ===
video_path = './test_5.mp4'

# === Функция для отрисовки Bounding Box ===
def draw_bbox(image, x, y, w, h, text, color=(0, 255, 0)):
    cv2.rectangle(image, (x, y), (x + w, y + h), color, 2)
    cv2.putText(image, text, (x, y - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.4, color, 1)
    return image

# === Загружаем YOLOv8 ===
model = YOLO("yolov8n.pt")  # используем локальный файл

# === Переменные для парковки ===
first_frame_parking_spaces = None
free_parking_timer = 0
free_parking_timer_bag1 = 0
free_parking_space = False
free_parking_space_box = None
check_det_frame = None
telegram_message = False

# === Работа с видео ===
video_capture = cv2.VideoCapture(video_path)

while video_capture.isOpened():
    ret, frame = video_capture.read()
    if not ret:
        video_capture.set(cv2.CAP_PROP_POS_FRAMES, 0)
        continue

    height, width, _ = frame.shape

    # Прогон кадра через YOLOv8
    results = model(frame)[0]  # первый результат
    boxes = []
    scores = []
    # NMS для фильтрации дублирующих машин
    cars_boxes = []  # <--- добавляем эту строку

    for r in results.boxes:
        cls_id = int(r.cls[0])
        score = float(r.conf[0])
        # 2 = car в COCO (проверяем)
        if cls_id == 2 and score > 0.25:
            x1, y1, x2, y2 = map(int, r.xyxy[0])
            boxes.append([x1, y1, x2 - x1, y2 - y1])
            scores.append(score)

    # === Первый кадр: определяем парковочные места ===
    if first_frame_parking_spaces is None:
        first_frame_parking_spaces = boxes.copy()
        first_frame_parking_scores = scores.copy()
        parking_spaces = boxes.copy()
    else:
        # NMS для фильтрации дублирующих машин
        indices = cv2.dnn.NMSBoxes(boxes, scores, 0.0, 0.4)
        indices = indices.flatten() if len(indices) > 0 else []
        cars_boxes = [boxes[i] for i in indices]

        # === Проверка свободных мест через IoU ===
        if len(cars_boxes) > 0 and len(first_frame_parking_spaces) > 0:
            def calculate_iou(box, other_boxes):
                box_area = box[2] * box[3]
                other_areas = np.array([b[2]*b[3] for b in other_boxes])
                ious = []
                for b in other_boxes:
                    xA = max(box[0], b[0])
                    yA = max(box[1], b[1])
                    xB = min(box[0]+box[2], b[0]+b[2])
                    yB = min(box[1]+box[3], b[1]+b[3])
                    inter = max(0, xB-xA) * max(0, yB-yA)
                    iou = inter / (box_area + (b[2]*b[3]) - inter)
                    ious.append(iou)
                return ious

            for idx, space in enumerate(first_frame_parking_spaces):
                overlaps = calculate_iou(space, cars_boxes)
                max_iou = max(overlaps) if overlaps else 0

                if not free_parking_space and 0.0 < max_iou < 0.4:
                    free_parking_timer += 1
                    if free_parking_timer >= 10:
                        free_parking_space = True
                        free_parking_space_box = space
                elif free_parking_space:
                    overlaps = calculate_iou(free_parking_space_box, cars_boxes)
                    if overlaps and max(overlaps) > 0.6:
                        free_parking_space = False
                        telegram_message = False

    # === Отрисовка парковочных мест ===
    for space in first_frame_parking_spaces:
        if free_parking_space and space == free_parking_space_box:
            frame = draw_bbox(frame, *space, "FREE SPACE!!!", (0, 0, 255))
            if not telegram_message:
                cv2.imwrite('./image_test_free.png', frame)
                send_telegram_message("Свободное место! Давай, жми скорее!!!")
                send_photo_file(chat_id, './image_test_free.png')
                telegram_message = True
        else:
            frame = draw_bbox(frame, *space, "No parking")

    # === Отрисовка машин ===
    for car in cars_boxes:
        frame = draw_bbox(frame, *car, "Car", (255, 255, 0))

    cv2.imshow("Parking Space", frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

video_capture.release()
cv2.destroyAllWindows()
