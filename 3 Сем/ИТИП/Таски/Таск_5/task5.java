import java.util.*;

public class task5 {

  public static void main(String[] args) {

    // Задание 1
    System.out.println("\nЗадание 1");
    System.out.println(sameLetterPattern("ABAB", "CDCD"));
    System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
    System.out.println(sameLetterPattern("FFGG", "CDCD"));
    System.out.println(sameLetterPattern("FFFF", "ABCD"));

    // Задание 2
    System.out.println("\nЗадание 2");
    System.out.println(memeSum(26, 39));
    System.out.println(memeSum(122, 81));
    System.out.println(memeSum(1222, 30277));

    // Задание 3
    System.out.println("\nЗадание 3");
    System.out.println(digitsCount(4666));
    System.out.println(digitsCount(544));
    System.out.println(digitsCount(121317));
    System.out.println(digitsCount(0));
    System.out.println(digitsCount(12345));

    // Задание 4
    String[] s1 = { "cat", "create", "sat" };
    String[] s2 = { "trance", "recant" };
    String[] s3 = { "dote", "dotes", "toes", "set", "dot", "dots", "sted" };
    System.out.println("\nЗадание 4");
    System.out.println(totalPoints(s1, "caster"));
    System.out.println(totalPoints(s2, "recant"));
    System.out.println(totalPoints(s3, "tossed"));

    // Задание 5
    System.out.println("\nЗадание 5");
    System.out.println(longestRun(new int[] { 1, 2, 3, 5, 6, 7, 8, 9 }));
    System.out.println(longestRun(new int[] { 1, 2, 3, 10, 11, 15 }));
    System.out.println(longestRun(new int[] { 5, 4, 2, 1 }));
    System.out.println(longestRun(new int[] { 3, 5, 7, 10, 15 }));

    // Задание 6
    String[] w1 = { "95%", "83%", "90%", "87%", "88%", "93%" };
    String[] w2 = { "10%" };
    String[] w3 = { "53%", "79%" };

    System.out.println("\nЗадание 6");
    System.out.println(takeDownAverage(w1));
    System.out.println(takeDownAverage(w2));
    System.out.println(takeDownAverage(w3));

    // Задание 7
    System.out.println("\nЗадание 7");
    System.out.println(canMove("Rook", "A8", "H8"));
    System.out.println(canMove("Bishop", "A7", "G1"));
    System.out.println(canMove("Queen", "C4", "D6"));

    // Задание 8
    System.out.println("\nЗадание 8");
    System.out.println(maxPossible(9328, 456));
    System.out.println(maxPossible(523, 76));
    System.out.println(maxPossible(9132, 5564));
    System.out.println(maxPossible(8732, 91255));

    // Задание 9
    System.out.println("\nЗадание 9");
    System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
    System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
    System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));

    // Задание 10
    System.out.println("\nЗадание 10");
    System.out.println(isNew(3));
    System.out.println(isNew(30));
    System.out.println(isNew(321));
    System.out.println(isNew(123));
  }

  // Задание 1
  public static boolean sameLetterPattern(String a, String b) {
    int x = b.charAt(0) - a.charAt(0);
    for (int i = 1; i < a.length(); i++) {
      if ((b.charAt(i) - a.charAt(i)) != x) {
        return false;
      }
    }
    return true;
  }

  // Задание 2
  public static int memeSum(int a, int b) {
    String strA = String.valueOf(a);
    String strB = String.valueOf(b);
    StringBuilder result = new StringBuilder();

    int maxLength = Math.max(strA.length(), strB.length());
    strA = String.format("%" + maxLength + "s", strA).replace(' ', '0');
    strB = String.format("%" + maxLength + "s", strB).replace(' ', '0');

    for (int i = 0; i < maxLength; i++) {
      int sum = Character.getNumericValue(strA.charAt(i)) + Character.getNumericValue(strB.charAt(i));
      result.append(sum);
    }

    return Integer.parseInt(result.toString());
  }

  // Задание 3
  public static int digitsCount(long n) {
    if (n < 10) {
      return 1;
    } else {
      return 1 + digitsCount(n / 10);
    }
  }

  // Задание 4
  public static int totalPoints(String[] words, String word) {
    int[] wordCount = new int[26];
    for (int i = 0; i < 26; i++) {
      wordCount[i] = 0;
    }
    for (int i = 0; i < word.length(); i++) {
      int ind = word.charAt(i) - 'a';
      wordCount[ind] += 1;
    }
    int answer = 0;
    for (String s : words) {
      int[] nCount = new int[26];
      for (int i = 0; i < 26; i++) {
        nCount[i] = 0;
      }
      for (int i = 0; i < s.length(); i++) {
        int ind = s.charAt(i) - 'a';
        nCount[ind] += 1;
      }
      int count = 0;
      boolean is_include_in_answer = true;
      for (int i = 0; i < 26; i++) {
        // System.out.print(i);
        // System.out.print(wordCount[i]);
        // System.out.println(nCount[i]);
        if (nCount[i] > wordCount[i]) {
          is_include_in_answer = false;
        }
        count += nCount[i];
      }
      if (is_include_in_answer) {
        answer += (count - 2);
        if (count >= 6) {
          answer += 50;
        }
      }
    }
    return answer;
  }

  // Задание 5
  public static int longestRun(int[] nums) {
    int maxRun = 1, currentRun = 1;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1] + 1 || nums[i] == nums[i - 1] - 1) {
        currentRun++;
        maxRun = Math.max(maxRun, currentRun);
      } else {
        currentRun = 1;
      }
    }

    return maxRun;
  }

  // Задание 6
  public static String takeDownAverage(String[] arr) {
    int n = arr.length;
    double sum = 0;
    for (int i = 0; i < n; i++) {
      arr[i] = arr[i].replaceAll("%", "");
      sum += Integer.parseInt(arr[i]);
    }
    int ans = (int) Math.round(sum / n - 5 * (n + 1));

    return Integer.toString(ans) + "%";
  }

  // Задание 7
  public static boolean canMove(String piece, String start, String end) {
    int startX = start.charAt(0) - 'A';
    int startY = start.charAt(1) - '1';
    int endX = end.charAt(0) - 'A';
    int endY = end.charAt(1) - '1';

    switch (piece.toLowerCase()) {
      case "rook":
        return startX == endX || startY == endY;
      case "bishop":
        return Math.abs(startX - endX) == Math.abs(startY - endY);
      case "queen":
        return startX == endX || startY == endY || Math.abs(startX - endX) == Math.abs(startY - endY);
      case "king":
        return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1;
      case "knight":
        return Math.abs(startX - endX) * Math.abs(startY - endY) == 2;
      case "pawn":
        return (startY == 1 && endY - startY == 2 && startX == endX) || (endY - startY == 1 && startX == endX);
      default:
        return false;
    }
  }

  // Задание 8
  public static int maxPossible(int num1, int num2) {
    char[] digits1 = String.valueOf(num1).toCharArray();
    char[] digits2 = String.valueOf(num2).toCharArray();
    Arrays.sort(digits2);

    int index = digits2.length - 1;
    for (int i = 0; i < digits1.length; i++) {
      if (index >= 0 && digits2[index] > digits1[i]) {
        digits1[i] = digits2[index--];
      }
    }
    return Integer.parseInt(new String(digits1));
  }

  // Задание 9
  public static String timeDifference(String cityA, String timeA, String cityB) {

    HashMap<String, int[]> cityDifference = new HashMap<>();
    HashMap<String, Integer> months = new HashMap<>();
    HashMap<String, Integer> daysInMonths = new HashMap<>();

    String[] monthsArray = { "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December" };

    cityDifference.put("Los Angeles", new int[] { -8, 0 });
    cityDifference.put("New York", new int[] { -5, 0 });
    cityDifference.put("Caracas", new int[] { -4, 30 });
    cityDifference.put("Buenos Aires", new int[] { -3, 0 });
    cityDifference.put("London", new int[] { 0, 0 });
    cityDifference.put("Rome", new int[] { 1, 0 });
    cityDifference.put("Moscow", new int[] { 3, 0 });
    cityDifference.put("Tehran", new int[] { 3, 30 });
    cityDifference.put("New Delhi", new int[] { 5, 30 });
    cityDifference.put("Beijing", new int[] { 8, 0 });
    cityDifference.put("Canberra", new int[] { 10, 0 });

    daysInMonths.put("January", 31);
    daysInMonths.put("February", 28);
    daysInMonths.put("March", 31);
    daysInMonths.put("April", 30);
    daysInMonths.put("May", 31);
    daysInMonths.put("June", 30);
    daysInMonths.put("July", 31);
    daysInMonths.put("August", 31);
    daysInMonths.put("September", 30);
    daysInMonths.put("October", 31);
    daysInMonths.put("November", 30);
    daysInMonths.put("December", 31);

    months.put("January", 1);
    months.put("February", 2);
    months.put("March", 3);
    months.put("April", 4);
    months.put("May", 5);
    months.put("June", 6);
    months.put("July", 7);
    months.put("August", 8);
    months.put("September", 9);
    months.put("October", 20);
    months.put("November", 11);
    months.put("December", 12);

    // Парсим время
    // time[0] - Месяц, time[1] - Число месяца
    // time[2] - Год, time[3] - Время
    String[] time = timeA.split(" ");
    time[1] = time[1].replaceAll(",", "");

    // Получаем разницу часовых поясов
    int[] timeInCityA = cityDifference.get(cityA);
    int[] timeInCityB = cityDifference.get(cityB);
    // Получаем прибавку ко времени
    int[] timeDifference = new int[] { timeInCityB[0] - timeInCityA[0],
        timeInCityB[1] - timeInCityA[1] };

    // Парсим из времени time[3] часы и минуты
    String[] currentTimeInString = time[3].split(":");
    int[] currentTime = { Integer.parseInt(currentTimeInString[0]),
        Integer.parseInt(currentTimeInString[1]) };

    // newTime - Время с учетом смены часовых поясов
    // newTime[0] - Часы
    // newTime[1] - Минуты
    int[] newTime = new int[] { currentTime[0] + timeDifference[0],
        currentTime[1] + timeDifference[1] };

    int month = months.get(time[0]);
    int date = Integer.parseInt(time[1]);
    int year = Integer.parseInt(time[2]);

    if (newTime[1] < 0) {
      newTime[1] = 60 + newTime[1];
      newTime[0] -= 1;
      if (newTime[0] < 0) {
        newTime[0] = 24 - newTime[0];
        date -= 1;
        if (date == 0) {
          month -= 1;
          if (month >= 1) {
            date = daysInMonths.get(monthsArray[month - 1]);
          } else {
            year -= 1;
            month = 12;
            date = 31;
          }
        }
      }
    }

    if (newTime[1] >= 60) {
      newTime[1] = newTime[1] % 60;
      newTime[0] += 1;
    }

    if (newTime[0] >= 24) {
      newTime[0] -= 24;
      date += 1;
      if (date > daysInMonths.get(monthsArray[month - 1])) {
        month += 1;
        date = 1;
        if (month > 11) {
          month = 1;
          year += 1;
        }
      }
    }

    StringBuilder ans = new StringBuilder();
    ans.append(Integer.toString(year));
    ans.append("-");
    ans.append(Integer.toString(month));
    ans.append("-");
    ans.append(Integer.toString(date));
    ans.append(" ");
    if (newTime[0] <= 9) {
      ans.append("0");
    }
    ans.append(newTime[0]);
    ans.append(":");
    if (newTime[1] <= 9) {
      ans.append("0");
    }
    ans.append(newTime[1]);

    return ans.toString();
  }

  // Задание 10
  public static boolean isNew(int a) {
    char[] digs = ("" + a).toCharArray();
    for (int i = 1; i < digs.length; i++) {
      if (digs[i] < digs[i - 1] && digs[i] != '0') {
        return false;
      }
    }
    return true;
  }
}
