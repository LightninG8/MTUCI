public class Task1 {
  // Task 1: Создайте функцию, которая принимает целое число галлонов и преобразует его в литры.
  private static final double GALLON_TO_LITER = 3.78541;

  public static double convert(int gallons) {
      return gallons * GALLON_TO_LITER;
  }

  // Task 2: Вы пишете программу для квази-фитнес-приложения и хотите создать функцию для расчета калорий, сожженных пользователем во время тренировки. Функция должна принимать время тренировки в минутах и интенсивность, где 1 – низкая интенсивность, 2 – средняя, 3 – высокая, а затем вычислять количество сожженных калорий на основе этой информации.

  public static int fitCalc(int minutes, int intensity) {
      int caloriesPerMinute;
      switch (intensity) {
          case 1:
              caloriesPerMinute = 1;
              break;
          case 2:
              caloriesPerMinute = 2;
              break;
          case 3:
              caloriesPerMinute = 3;
              break;
          default:
              throw new IllegalArgumentException("Интенсивность должна быть равна 1, 2 или 3.");
      }
      return minutes * caloriesPerMinute;
  }

  // Task 3: В этой задаче вы управляете складом, где хранятся товары трех типов:

  // - Коробки содержат по 20 товаров в каждой.
  // - Мешки содержат по 50 товаров в каждом.
  // - Бочки содержат по 100 товаров в каждой.

  // Вам предоставили информацию о количестве каждого типа емкостей на складе, и вам нужно создать функцию, которая вернет общее количество товаров на складе, учитывая объекты хранения разных типов.

  public static int containers(int boxes, int bags, int barrels) {
      int itemsPerBox = 20;
      int itemsPerBag = 50;
      int itemsPerBarrel = 100;
      return (boxes * itemsPerBox) + (bags * itemsPerBag) + (barrels * itemsPerBarrel);
  }

  // Task 4: Создайте функцию, которая принимает 3 числа: X, Y и Z. Эти числа представляют длины сторон треугольника. Функция должна вернуть тип треугольника на основе данных сторон: "равносторонний" (если все стороны равны), "равнобедренный" (если две стороны равны), "разносторонний" (если все стороны разные) или "не является треугольником" (если невозможно построить треугольник с заданными сторонами).
  public static String triangleType(int x, int y, int z) {
      if (x + y <= z || x + z <= y || y + z <= x) {
          return "not a triangle";
      }
      if (x == y && y == z) {
          return "equilateral";
      }
      if (x == y || x == z || y == z) {
          return "isosceles";
      }
      return "different-sided";
  }

  // Task 5: В Java есть вариация условного оператора – тернарный оператор "? :", принимающий три операнда и возвращающий один из них на основе значения условия. Он имеет следующую структуру:

  // условие ? выражение1 : выражение2

  // Ваша задача создать функцию, которая принимает два числа a и b, а затем с помощью тернарного оператора определяет, какое из чисел больше, и возвращает большее число.
  public static int ternaryEvaluation(int a, int b) {
      return (a > b) ? a : b;
  }

  // Task 6: У меня есть ограниченное количество ткани определенной длины, и я хочу сшить как можно больше пододеяльников. Создайте функцию, которая будет принимать длину ткани (в метрах) и размер одной детали (ширина и длина в метрах), а затем возвращать количество пододеяльников, которые я смогу сшить, прежде чем кончится рулон.

  // n * 2 – это количество квадратных метров имеющейся ткани,
  // w и h – это длина и ширина одной детали в метрах

  public static int howManyItems(double fabricLength, double pieceWidth, double pieceLength) {
      double fabricArea = fabricLength;
      double coverArea = pieceWidth * pieceLength * 2;
      return (int)(fabricArea / coverArea);
  }

  // Task 7: Напишите функцию, вычисляющую факториал выбранного числа. 
  public static long factorial(int n) {
      if (n < 0) {
          throw new IllegalArgumentException("Факториал не определен для отрицательных чисел.");
      }
      long result = 1;
      for (int i = 2; i <= n; i++) {
          result *= i;
      }
      return result;
  }

  // Task 8: Создайте функцию, которая находит наибольший общий делитель двух чисел.
  public static int gcd(int a, int b) {
      while (b != 0) {
          int temp = b;
          b = a % b;
          a = temp;
      }
      return a;
  }

  // Task 9: Создайте функцию, которая принимает количество билетов на концерт, проданных через веб-сервис, и стоимость одного билета с учетом фиксированной комиссии. Функция должна вернуть общую выручку от продажи билетов.

  public static int ticketSaler(int ticketsSold, int ticketPrice) {
      double fixedDeduction = ticketPrice <= 1000 ? 266 : ticketPrice <= 1250 ? 350 : 420;
      double totalRevenue = ticketsSold * (ticketPrice - fixedDeduction);
      return (int) totalRevenue;
  }

  // Task 10: Создайте функцию, которая принимает целое число студентов и количество парт в аудитории. Функция должна определить, сколько столов не хватает для размещения всех студентов, если за одним столом помещается два студента.
  public static int tables(int students, int tables) {
      int requiredTables = (students + 1) / 2;
      return Math.max(0, requiredTables - tables);
  }

  public static void main(String[] args) {
      // Task 1 outputs
      System.out.println("\nTask 1 outputs");
      System.out.println(convert(5));
      System.out.println(convert(3));
      System.out.println(convert(8));

      // Task 2 outputs
      System.out.println("\nTask 2 outputs");
      System.out.println(fitCalc(15, 1));
      System.out.println(fitCalc(24, 2));
      System.out.println(fitCalc(41, 3));

      // Task 3 outputs
      System.out.println("\nTask 3 outputs");
      System.out.println(containers(3, 4, 2));
      System.out.println(containers(5, 0, 2));
      System.out.println(containers(4, 1, 4));

      // Task 4 outputs
      System.out.println("\nTask 4 outputs");
      System.out.println(triangleType(5, 5, 5));
      System.out.println(triangleType(5, 4, 5));
      System.out.println(triangleType(3, 4, 5));
      System.out.println(triangleType(5, 1, 1));

      // Task 5 outputs
      System.out.println("\nTask 5 outputs");
      System.out.println(ternaryEvaluation(8, 4));
      System.out.println(ternaryEvaluation(1, 11));
      System.out.println(ternaryEvaluation(5, 9));

      // Task 6 outputs
      System.out.println("\nTask 6 outputs");
      System.out.println(howManyItems(22, 1.4, 2));
      System.out.println(howManyItems(45, 1.8, 1.9));
      System.out.println(howManyItems(100, 2, 2));

      // Task 7 outputs
      System.out.println("\nTask 7 outputs");
      System.out.println(factorial(3));
      System.out.println(factorial(5));
      System.out.println(factorial(7));

      // Task 8 outputs
      System.out.println("\nTask 8 outputs");
      System.out.println(gcd(48, 18));
      System.out.println(gcd(52, 8));
      System.out.println(gcd(259, 28));

      // Task 9 outputs
      System.out.println("\nTask 9 outputs");
      System.out.println(ticketSaler(70, 1500));
      System.out.println(ticketSaler(24, 950));
      System.out.println(ticketSaler(53, 1250));

      // Task 10 outputs
      System.out.println("\nTask 10 outputs");
      System.out.println(tables(5, 2));
      System.out.println(tables(31, 20));
      System.out.println(tables(123, 58));
  }
}
