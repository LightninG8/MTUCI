import java.util.*;

public class Task2 {
  public static void main(String[] args) {

    System.out.println("\n1. duplicateChars");
    System.out.println(duplicateChars("Donald", "Trump"));
    System.out.println(duplicateChars("Barack", "Obama"));

    System.out.println("\n2. dividedByThree");
    int[] numbers1 = { 3, 12, 7, 81, 52 };
    int[] numbers2 = { 5, 16, 9, 81, 99 };
    System.out.println(dividedByThree(numbers1));
    System.out.println(dividedByThree(numbers2));

    System.out.println("\n3. getInitials");
    System.out.println(getInitials("simonov sergei evgenievich"));
    System.out.println(getInitials("kozhevnikova tatiana vitalevna"));

    System.out.println("\n4. normalizator");
    double[] numbers4_1 = { 3.5, 7.0, 1.5, 9.0, 5.5 };
    System.out.println(Arrays.toString(normalizator(numbers4_1)));

    double[] numbers4_2 = { 10.0, 10.0, 10.0, 10.0 };
    System.out.println(Arrays.toString(normalizator(numbers4_2)));

    System.out.println("\n5. compressedNums");
    double[] numbers5_1 = { 1.6, 0, 212.3, 34.8, 0, 27.5 };
    System.out.println(Arrays.toString(compressedNums(numbers5_1)));

    double[] numbers5_2 = { 0, 0, 3.7, 3.2, 9.9, 10.1 };
    System.out.println(Arrays.toString(compressedNums(numbers5_2)));

    System.out.println("\n6. camelToSnake");
    System.out.println(camelToSnake("helloWorld"));
    System.out.println(camelToSnake("thisIsCamelCase"));

    System.out.println("\n7. secondBiggest");
    int[] numbers6_1 = { 3, 5, 8, 1, 2, 4 };
    System.out.println(secondBiggest(numbers6_1));

    int[] numbers6_2 = { 10, 10, 9, 8 };
    System.out.println(secondBiggest(numbers6_2));

    System.out.println("\n8. localReverse");
    System.out.println(localReverse("baobab", 'b')); // Вывод: "boabab"
    System.out.println(localReverse("Hello, I’m under the water, please help me", 'e'));

    System.out.println("\n9. equal");
    System.out.println(equal(8, 1, 8)); // Вывод: 2
    System.out.println(equal(5, 5, 5)); // Вывод: 3
    System.out.println(equal(4, 9, 6)); // Вывод: 0

    System.out.println("\n10. isAnagram");
    System.out.println(isAnagram("LISTEN", "silent")); // Вывод: true
    System.out.println(isAnagram("Eleven plus two?", "Twelve plus one!")); // Вывод: true
    System.out.println(isAnagram("hello", "world")); // Вывод: false
  }

  public static String duplicateChars(String str1, String str2) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < str1.toLowerCase().length(); i++) {
      char ch = str1.toLowerCase().charAt(i);
      // Проверяем, входит ли символ ch из str1 в str2
      if (str2.toLowerCase().indexOf(ch) == -1) {
        result.append(ch);
      }
    }
    return result.toString();
  }

  public static int dividedByThree(int[] arr) {
    int count = 0;
    for (int num : arr) {
      // Проверяем, является ли число нечетным и кратным 3
      if (num % 2 != 0 && num % 3 == 0) {
        count++;
      }
    }
    return count;
  }

  public static String getInitials(String fullName) {
    String[] parts = fullName.split(" ");

    String lastName = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1).toLowerCase();
    char firstInitial = Character.toUpperCase(parts[1].charAt(0));
    char middleInitial = Character.toUpperCase(parts[2].charAt(0));

    return firstInitial + "." + middleInitial + "." + " " + lastName;
  }

  public static double[] normalizator(double[] arr) {
    double min = Arrays.stream(arr).min().orElse(0);
    double max = Arrays.stream(arr).max().orElse(0);

    if (min == max) {
      // Если все элементы равны, возвращаем массив нулей
      return new double[arr.length];
    }

    double[] normalizedArr = new double[arr.length];
    for (int i = 0; i < arr.length; i++) {
      normalizedArr[i] = (arr[i] - min) / (max - min);
    }

    return normalizedArr;
  }

  public static int[] compressedNums(double[] arr) {
    return Arrays.stream(arr)
        .filter(num -> num != 0) // Убираем нули
        .mapToInt(num -> (int) Math.floor(num)) // Округляем в меньшую сторону и преобразуем в целые
        .distinct() // Убираем возможные дубликаты
        .sorted() // Сортируем по возрастанию
        .toArray(); // Преобразуем в массив
  }

  public static String camelToSnake(String camelCase) {
    StringBuilder result = new StringBuilder();

    // Проходим по каждому символу строки
    for (int i = 0; i < camelCase.length(); i++) {
      char currentChar = camelCase.charAt(i);

      // Если символ заглавный, добавляем подчеркивание и преобразуем его в нижний
      // регистр
      if (Character.isUpperCase(currentChar)) {
        if (i != 0) { // Не добавляем подчеркивание перед первым символом
          result.append('_');
        }
        result.append(Character.toLowerCase(currentChar));
      } else {
        // Иначе просто добавляем символ
        result.append(currentChar);
      }
    }

    return result.toString();
  }

  public static int secondBiggest(int[] arr) {

    int max = Integer.MIN_VALUE;
    int secondMax = Integer.MIN_VALUE;

    // Проходим по всем элементам массива
    for (int num : arr) {
      if (num > max) {
        secondMax = max; // Предыдущий максимум становится вторым по величине
        max = num; // Новый максимум
      } else if (num > secondMax && num != max) {
        secondMax = num; // Обновляем второй максимум, если число меньше максимума, но больше предыдущего
                         // второго
      }
    }

    return secondMax;
  }

  public static String localReverse(String input, char marker) {
    StringBuilder result = new StringBuilder();
    StringBuilder betweenMarkers = new StringBuilder();
    boolean insideMarkers = false;

    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);

      // Если встречаем маркерный символ
      if (currentChar == marker) {
        if (insideMarkers) {
          // Если уже внутри пары маркеров, разворачиваем накопленную строку
          result.append(betweenMarkers.reverse());
          betweenMarkers.setLength(0); // Очищаем накопитель
        }
        // Добавляем маркер в результат
        result.append(marker);
        insideMarkers = !insideMarkers; // Переключаем флаг
      } else if (insideMarkers) {
        // Если находимся между маркерными символами, добавляем символ в накопитель
        betweenMarkers.append(currentChar);
      } else {
        // Если не внутри маркеров, просто добавляем символ в результат
        result.append(currentChar);
      }
    }

    return result.toString();
  }

  public static int equal(int a, int b, int c) {
    if (a == b && b == c) {
      return 3; // Все три числа равны
    } else if (a == b || b == c || a == c) {
      return 2; // Два числа равны
    } else {
      return 0; // Нет одинаковых чисел
    }
  }


  public static String retainLetters(String input) {
    StringBuilder result = new StringBuilder();

    // Проходим по каждому символу строки
    for (int i = 0; i < input.length(); i++) {
        char currentChar = input.charAt(i);
        // Проверяем, является ли символ буквой
        if (Character.isLetter(currentChar)) {
            result.append(currentChar);  // Добавляем букву в результат
        }
    }

    return result.toString();  // Возвращаем результирующую строку
}

  public static boolean isAnagram(String a, String b) {

    char[] chars1 = retainLetters(a).toLowerCase().toCharArray();
    char[] chars2 = retainLetters(b).toLowerCase().toCharArray();

    Arrays.sort(chars1);
    Arrays.sort(chars2);

    return Arrays.equals(chars1, chars2);
  }
}
