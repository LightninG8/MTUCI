import java.util.*;

public class Task3 {
  public static void main(String[] args) {
    System.out.println(("\n1. isStrangePair"));
    System.out.println(isStrangePair("ratio", "orator"));
    System.out.println(isStrangePair("sparkling", "groups"));
    System.out.println(isStrangePair("bush", "hubris"));
    System.out.println(isStrangePair("", ""));

    System.out.println(("\n2. sale"));
    Object[][] items = {
        { "Laptop", 124200 },
        { "Phone", 51450 },
        { "Headphones", 13800 }
    };

    Object[][] result = sale(items, 25);

    for (Object[] item : result) {
      System.out.printf("%s - %d%n", item[0], item[1]);
    }

    System.out.println(("\n3. successShoot"));
    System.out.println(successShoot(0, 0, 5, 2, 2));
    System.out.println(successShoot(-2, -3, 4, 5, -6));
    System.out.println(successShoot(1, 1, 3, 1, 2));
    System.out.println(successShoot(0, 0, 1, 1, 1));

    System.out.println(("\n4. parityAnalysis"));
    System.out.println(parityAnalysis(243));
    System.out.println(parityAnalysis(12));
    System.out.println(parityAnalysis(3));

    System.out.println(("\n5. parityAnalysis"));
    System.out.println(rps("rock", "paper"));
    System.out.println(rps("paper", "rock"));
    System.out.println(rps("paper", "scissors"));
    System.out.println(rps("scissors", "scissors"));

    System.out.println(("\n6. bugger"));
    System.out.println(bugger(39));
    System.out.println(bugger(999));
    System.out.println(bugger(4));

    System.out.println(("\n7. mostExpensive"));
    Object[][] inventory = {
        { "Скакалка", 550, 8 },
        { "Шлем", 3750, 4 },
        { "Мяч", 2900, 10 }
    };

    System.out.println(mostExpensive(inventory));

    System.out.println(("\n8. longestUnique"));
    System.out.println(longestUnique("abcba"));
    System.out.println(longestUnique("bbb"));
    System.out.println(longestUnique("pwwkew"));
    System.out.println(longestUnique(""));
    System.out.println(longestUnique("abcdefg"));

    System.out.println(("\n9. isPrefix, isSuffix"));
    System.out.println(isPrefix("automation", "auto-"));
    System.out.println(isSuffix("arachnophobia", "-phobia"));
    System.out.println(isPrefix("retrospect", "sub-"));
    System.out.println(isSuffix("vocation", "-logy"));

    System.out.println(("\n10. doesBrickFit"));
    System.out.println(doesBrickFit(1, 1, 1, 1, 1));
    System.out.println(doesBrickFit(1, 2, 1, 1, 1));
    System.out.println(doesBrickFit(1, 2, 2, 1, 1));
    System.out.println(doesBrickFit(2, 1, 1, 1, 1));
    System.out.println(doesBrickFit(2, 2, 2, 2, 2));
    System.out.println(doesBrickFit(2, 3, 1, 1, 2));

  }

  public static boolean isStrangePair(String str1, String str2) {
    // Если переданы 2 пустые строки
    if (str1 == "" && str2 == "") {
      return true;
    }

    if ((str1.charAt(0) == str2.charAt(str2.length() - 1) && (str2.charAt(0) == str1.charAt(str1.length() - 1)))) {
      return true;
    }

    return false;
  }

  public static Object[][] sale(Object[][] items, double discount) {
    // Создаем массив для обновленных товаров с той же длиной, что и входной массив
    Object[][] updatedItems = new Object[items.length][2];

    for (int i = 0; i < items.length; i++) {
      String name = (String) items[i][0]; // Название товара
      int price = (int) items[i][1]; // Цена товара

      // Применяем скидку
      double discountedPrice = price * (1 - discount / 100);

      // Округляем до ближайшего целого
      int finalPrice = (int) Math.round(discountedPrice);

      // Если цена меньше 1, устанавливаем ее в 1
      if (finalPrice < 1) {
        finalPrice = 1;
      }

      // Сохраняем обновленный товар в массив
      updatedItems[i][0] = name;
      updatedItems[i][1] = finalPrice;
    }

    return updatedItems; // Возвращаем массив с обновленными ценами
  }

  public static boolean successShoot(int centerX, int centerY, int radius, int pointX, int pointY) {
    // Вычисляем квадрат расстояния от центра круга до точки
    int distanceSquared = (pointX - centerX) * (pointX - centerX) + (pointY - centerY) * (pointY - centerY);

    // Вычисляем квадрат радиуса
    int radiusSquared = radius * radius;

    // Проверяем, находится ли точка внутри круга
    // Если квадрат расстояния меньше или равен квадрату радиуса, то точка внутри
    // круга
    return distanceSquared <= radiusSquared;
  }

  public static boolean parityAnalysis(int number) {
    // Вычисляем сумму цифр числа
    int sumOfDigits = 0;
    int temp = Math.abs(number); // Берем абсолютное значение для обработки отрицательных чисел

    while (temp > 0) {
      sumOfDigits += temp % 10; // Добавляем последнюю цифру к сумме
      temp /= 10; // Убираем последнюю цифру
    }

    // Определяем четности
    boolean isNumberEven = number % 2 == 0; // Четность числа
    boolean isSumEven = sumOfDigits % 2 == 0; // Четность суммы цифр

    // Сравниваем четности
    return isNumberEven == isSumEven;
  }

  public static String rps(String player1, String player2) {
    // Приводим выбор игроков к нижнему регистру для избежания ошибок
    player1 = player1.toLowerCase();
    player2 = player2.toLowerCase();

    // Проверяем на ничью
    if (player1.equals(player2)) {
      return "Tie";
    }

    // Определяем победителя
    switch (player1) {
      case "rock":
        return player2.equals("scissors") ? "Player 1 wins" : "Player 2 wins";
      case "paper":
        return player2.equals("rock") ? "Player 1 wins" : "Player 2 wins";
      case "scissors":
        return player2.equals("paper") ? "Player 1 wins" : "Player 2 wins";
      default:
        return "Invalid input"; // Обработка неверного ввода
    }
  }

  public static int bugger(int number) {
    // Если число уже одноцифровое, возвращаем 0
    if (number < 10) {
      return 0;
    }

    int count = 0;

    // Продолжаем перемножать цифры, пока не останется одноцифровое число
    while (number >= 10) {
      int product = 1; // Начинаем произведение с 1

      // Перемножаем цифры числа
      int temp = number; // Сохраняем текущее число для работы с цифрами
      while (temp > 0) {
        product *= temp % 10; // Умножаем последнюю цифру
        temp /= 10; // Убираем последнюю цифру
      }

      number = product; // Обновляем число на произведение его цифр
      count++; // Увеличиваем счетчик
    }

    return count; // Возвращаем количество умножений
  }

  public static String mostExpensive(Object[][] items) {
    String mostExpensiveItem = "";
    int maxTotalCost = 0;

    // Проходим по всем предметам инвентаря
    for (Object[] item : items) {
      String name = (String) item[0]; // Получаем название предмета
      int price = (int) item[1]; // Получаем цену предмета
      int quantity = (int) item[2]; // Получаем количество предметов

      // Вычисляем общую стоимость
      int totalCost = price * quantity;

      // Если текущая общая стоимость больше максимальной, обновляем значения
      if (totalCost > maxTotalCost) {
        maxTotalCost = totalCost;
        mostExpensiveItem = name;
      }
    }

    return String.format("Наиб. общ. стоимость у предмета %s - %d", mostExpensiveItem, maxTotalCost);
  }

  public static String longestUnique(String str) {
    HashMap<Character, Integer> charIndexMap = new HashMap<>();
    int maxLength = 0; // Длина максимальной подстроки
    int start = 0; // Начальный индекс текущей подстроки
    String longestSubstring = ""; // Результирующая подстрока

    for (int end = 0; end < str.length(); end++) {
      char currentChar = str.charAt(end);

      // Если текущий символ уже встречался, обновляем начальный индекс
      if (charIndexMap.containsKey(currentChar)) {
        start = Math.max(start, charIndexMap.get(currentChar) + 1);
      }

      // Сохраняем текущий символ и его индекс
      charIndexMap.put(currentChar, end);

      // Проверяем, если длина текущей подстроки больше максимальной
      if (end - start + 1 > maxLength) {
        maxLength = end - start + 1;
        longestSubstring = str.substring(start, end + 1);
      }
    }

    return longestSubstring; // Возвращаем подстроку с уникальными символами
  }

  public static boolean isPrefix(String word, String prefix) {
    // Убираем тире в конце префикса
    if (prefix.endsWith("-")) {
      prefix = prefix.substring(0, prefix.length() - 1);
    }
    return word.startsWith(prefix); // Проверяем, начинается ли слово с префикса
  }

  public static boolean isSuffix(String word, String suffix) {
    // Убираем тире в начале суффикса
    if (suffix.startsWith("-")) {
      suffix = suffix.substring(1);
    }
    return word.endsWith(suffix); // Проверяем, заканчивается ли слово на суффикс
  }

  public static boolean doesBrickFit(int a, int b, int c, int w, int h) {
    // Проверяем все возможные комбинации сторон кирпича
    return (a <= w && b <= h) || (a <= h && b <= w) || // a, b
        (a <= w && c <= h) || (a <= h && c <= w) || // a, c
        (b <= w && c <= h) || (b <= h && c <= w) || // b, c
        (b <= w && a <= h) || (b <= h && a <= w) || // b, a
        (c <= w && a <= h) || (c <= h && a <= w) || // c, a
        (c <= w && b <= h) || (c <= h && b <= w); // c, b
  }
}
