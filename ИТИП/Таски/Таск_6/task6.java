import java.util.*;


public class task6 {

  public static void main(String[] args) {

    // Задание 1
    System.out.println("\nЗадание 1");
    System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.",
        "sworn love lived"));
    System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
    System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
    System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
    System.out.println(hiddenAnagram("D e b90it->?$ (c)a r...d,,#~", "bad credit"));
    System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));

    // Задание 2
    System.out.println("\nЗадание 2");
    System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] {}));
    System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] { "b" }));
    System.out.println(stripUrlParams("https://edabit.com", new String[] { "b" }));

    // Задание 3
    System.out.println("\nЗадание 3");
    System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
    System.out.println(nicoCipher("mubashirhassan", "crazy"));

    // Задание 4
    System.out.println("\nЗадание 4");
    int[] arr1 = { 1, 2, 3, 9, 4, 5, 15 };
    int[] arr2 = { 1, 2, 3, 9, 4, 15, 3, 5 };
    int[] arr3 = { 1, 2, -1, 4, 5, 6, 10, 7 };

    System.out.println(Arrays.toString(twoProduct(arr1, 45)));
    System.out.println(Arrays.toString(twoProduct(arr2, 45)));
    System.out.println(Arrays.toString(twoProduct(arr3, 20)));

    // Задание 5
    System.out.println("\nЗадание 5");
    System.out.println(Arrays.toString(isExact(6)));
    System.out.println(Arrays.toString(isExact(24)));
    System.out.println(Arrays.toString(isExact(125)));
    System.out.println(Arrays.toString(isExact(720)));
    System.out.println(Arrays.toString(isExact(1024)));

    // Задание 6
    System.out.println("\nЗадание 6");
    System.out.println(fractions("0.(6)"));
    System.out.println(fractions("1.(1)"));
    System.out.println(fractions("3.(142857)"));
    System.out.println(fractions("0.19(2367)"));
    System.out.println(fractions("0.1097(3)"));

    // Задание 7
    System.out.println("\nЗадание 7");
    System.out.println(pilish_string("33314444"));
    System.out.println(pilish_string("TOP"));
    System.out.println(pilish_string("X"));
    System.out.println(pilish_string(""));

    // Задание 8
    System.out.println("\nЗадание 8");
    System.out.println(formula("6 * 4 = 24"));
    System.out.println(formula("18 / 17 = 2"));
    System.out.println(formula("16 * 10 = 160 = 14 + 120"));

    // Задание 9
    System.out.println("\nЗадание 9");
    System.out.println(isValid("aabbcd"));
    System.out.println(isValid("aabbccddeefghi"));
    System.out.println(isValid("abcdefghhgfedecba"));
    System.out.println(isValid("aabbcc"));

    // Задание 10
    System.out.println("\nЗадание 10");
    System.out.println(palindromeDescendant(11211230));
    System.out.println(palindromeDescendant(13001120));
    System.out.println(palindromeDescendant(23336014));
    System.out.println(palindromeDescendant(11));

  }

  // Задание 1
  public static String hiddenAnagram(String text, String temp) {

    StringBuilder ntemp = new StringBuilder();
    temp = temp.toLowerCase();
    for (int i = 0; i < temp.length(); i++) {
      char c = temp.charAt(i);
      if ('a' <= c && c <= 'z') {
        ntemp.append(c);
      }
    }
    temp = ntemp.toString();

    int len = temp.length();
    int n = text.length();

    StringBuilder ntext = new StringBuilder();
    text = text.toLowerCase();
    for (int i = 0; i < n; i++) {
      char c = text.charAt(i);
      if ('a' <= c && c <= 'z') {
        ntext.append(c);
      }
    }
    text = ntext.toString();
    n = text.length();

    int[] tempCount = new int[26];
    for (int i = 0; i < len; i++) {
      int ind = temp.charAt(i) - 'a';
      if (0 <= ind && ind < 26) {
        tempCount[ind] += 1;
      }
    }
    String ans = "notfound";
    for (int l = 0; l + len <= n; l++) {
      int r = l + len;
      int[] rayCount = new int[26];
      for (int j = l; j < r; j++) {
        int ind = text.charAt(j) - 'a';
        rayCount[ind] += 1;
      }

      boolean isAns = true;
      for (int i = 0; i < 26; i++) {
        if (tempCount[i] != rayCount[i]) {
          isAns = false;
        }
      }
      if (isAns) {
        ans = text.substring(l, r);
        break;
      }
    }

    return ans;
  }

  // Задание 2
  public static String stripUrlParams(String url, String[] paramsToStrip) {
    if (!url.contains("?"))
      return url;

    String[] parts = url.split("\\?");
    String base = parts[0];
    String[] queryParams = parts[1].split("&");
    Map<String, String> paramMap = new LinkedHashMap<>();

    for (String param : queryParams) {
      String[] pair = param.split("=");
      paramMap.put(pair[0], pair[1]);
    }

    if (paramsToStrip != null) {
      for (String param : paramsToStrip) {
        paramMap.remove(param);
      }
    }

    StringBuilder result = new StringBuilder(base);
    if (!paramMap.isEmpty()) {
      result.append("?");
      paramMap.forEach((key, value) -> result.append(key).append("=").append(value).append("&"));
      result.setLength(result.length() - 1); // Remove last '&'
    }

    return result.toString();
  }

  // Задание 3
  public static String nicoCipher(String message, String key) {

    char[] copyKey = key.toCharArray();
    Arrays.sort(copyKey);

    int cnt = 0;
    // Проверка на корректность ключа
    // В нем должны быть все символы различными
    HashMap<Character, Integer> mp = new HashMap<>();
    for (char c : copyKey) {
      if (!mp.containsKey(c)) {
        mp.put(c, cnt);
        cnt++;
      } else {
        return "Key is not valid";
      }
    }

    int[] positions = new int[key.length()];
    System.out.println(positions.length);
    for (int i = 0; i < key.length(); i++) {
      positions[i] = mp.get(key.charAt(i));
    }

    for (int i = 0; i < positions.length; i++) {
      System.out.print(positions[i]);
      System.out.print(" ");
    }
    System.out.println(" ");

    ArrayList<ArrayList<Character>> cipher = new ArrayList<ArrayList<Character>>();
    for (int i = 0; i < positions.length; i++) {
      cipher.add(new ArrayList<>());
    }

    int new_length = (message.length() / key.length() + 1) * (key.length());
    int new_size = new_length / (key.length());
    // System.out.println(new_size);

    for (int i = 0; i < new_length; i++) {
      if (i < message.length()) {
        cipher.get(positions[i % positions.length]).add(message.charAt(i));
      } else {
        cipher.get(positions[i % positions.length]).add(' ');
      }
    }

    // for (ArrayList<Character> l : cipher) {
    // for (Character c : l) {
    // System.out.print(c);
    // System.out.print(" ");
    // }
    // System.out.println();
    // }

    StringBuilder ans = new StringBuilder();
    for (int i = 0; i < new_size; i++) {
      for (int j = 0; j < positions.length; j++) {
        ans.append(cipher.get(j).get(i));
      }
    }

    return ans.toString();
  }

  // Задание 4
  public static int[] twoProduct(int[] arr, int n) {
    int[] ans = new int[0];
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] * arr[j] == n) {
          ans = new int[] { i, j };
          return ans;
        }
      }
    }
    return ans;
  }

  // Задание 5
  public static int[] isExact(int n) {
    return isExactly(n, 1, 1);
  }

  public static int[] isExactly(int n, int p, int z) {
    if (p >= n) {
      int[] ans = new int[0];
      if (p == n) {
        ans = new int[] { p, z - 1 };
      }
      return ans;
    } else {
      return isExactly(n, p * z, z + 1);
    }
  }

  public static long gcd(long a, long b) {
    if (b == 0) {
      return a;
    } else {
      return gcd(b, a % b);
    }
  }

  // Задание 6
  public static String fractions(String s) {
    String[] x = s.split("\\(");
    String[] xx = x[0].split("\\.");
    String x0 = xx[0];
    String x1;
    if (xx.length == 1) {
      x1 = "0";
    } else {
      x1 = xx[1];
    }
    String x2 = x[1].substring(0, x[1].length() - 1);
    StringBuilder val1 = new StringBuilder();
    for (int i = 0; i < x2.length(); i++) {
      val1.append("9");
    }
    for (int i = 0; i < x[0].length() - 2; i++) {
      val1.append("0");
    }

    long val1Int = Long.parseLong(val1.toString());
    long val2Int = (long) Math.pow(10, x1.length());
    long A = Long.parseLong(x0) * val1Int * val2Int + Long.parseLong(x1) * val1Int + Long.parseLong(x2) * val2Int;
    long B = val1Int * val2Int;

    long g = gcd(A, B);
    A /= g;
    B /= g;

    return Long.toString(A) + "/" + Long.toString(B);
  }

  // Задание 7
  public static String pilish_string(String s) {
    StringBuilder cur, ans = new StringBuilder();
    String pi = "314159265358979";
    char[] pis = pi.toCharArray();
    int[] pisInts = new int[pis.length];
    for (int i = 0; i < pis.length; i++) {
      pisInts[i] = pis[i] - '0';
    }

    int cnt = 0;
    for (int p : pisInts) {
      for (int i = 0; i < p; i++) {
        if (cnt >= s.length() && !s.isEmpty()) {
          ans.append(s.charAt(s.length() - 1));
        } else if (!s.isEmpty()) {
          ans.append(s.charAt(cnt));
        }
        cnt++;
      }

      if (cnt >= s.length()) {
        break;
      }
      ans.append(" ");
    }
    return ans.toString();
  }

  // Задание 8
  public static boolean formula(String input) {
    String[] parts = input.split("=");
    int[] results = new int[parts.length];

    for (int i = 0; i < parts.length; i++) {
      results[i] = evaluateExpression(parts[i].trim());
    }

    for (int i = 1; i < results.length; i++) {
      if (results[i] != results[i - 1])
        return false;
    }

    return true;
  }

  private static int evaluateExpression(String expression) {
    Stack<Integer> stack = new Stack<>();
    char sign = '+';
    int num = 0;
    expression = expression.replaceAll("\\s", ""); // Убираем пробелы

    for (int i = 0; i < expression.length(); i++) {
      char ch = expression.charAt(i);

      if (Character.isDigit(ch)) {
        num = num * 10 + (ch - '0');
      }

      if (!Character.isDigit(ch) || i == expression.length() - 1) {
        if (ch == '(') {
          int j = i, brackets = 0;
          for (; i < expression.length(); i++) {
            if (expression.charAt(i) == '(')
              brackets++;
            if (expression.charAt(i) == ')')
              brackets--;
            if (brackets == 0)
              break;
          }
          num = evaluateExpression(expression.substring(j + 1, i));
        }

        if (sign == '+')
          stack.push(num);
        if (sign == '-')
          stack.push(-num);
        if (sign == '*')
          stack.push(stack.pop() * num);
        if (sign == '/')
          stack.push(stack.pop() / num);

        sign = ch;
        num = 0;
      }
    }

    int result = 0;
    for (int value : stack)
      result += value;
    return result;
  }

  // Задание 9
  public static String isValid(String s) {
    char[] cs = s.toCharArray();
    Arrays.sort(cs);
    boolean used = false;
    int a1 = 0, a2 = 0, n = 0;
    int cnt1 = 0, cnt2 = 0, curCnt = 1;
    for (int i = 1; i < cs.length; i++) {
      if (cs[i] != cs[i - 1]) {
        if (a1 == 0) {
          a1 = curCnt;
          cnt1++;
        } else if (curCnt != a1 && a2 == 0) {
          a2 = curCnt;
          cnt2++;
        } else {
          if (curCnt == a1)
            cnt1++;
          else if (curCnt == a2)
            cnt2++;
          else
            return "NO";
        }
        n++;
        curCnt = 1;
      } else {
        curCnt++;
      }
    }
    n++;
    if (curCnt == a1)
      cnt1++;
    else if (curCnt == a2)
      cnt2++;
    else
      return "NO";
    if ((cnt1 == n - 1 || cnt2 == n - 1) && Math.abs(a1 - a2) == 1) {
      return "YES";
    } else if (a2 == 0) {
      return "YES";
    } else {
      return "NO";
    }
  }

  // Задание 10
  public static boolean isPalindrome(String num) {
    int left = 0, right = num.length() - 1;
    while (left < right) {
      if (num.charAt(left++) != num.charAt(right--))
        return false;
    }
    return true;
  }

  public static boolean palindromeDescendant(int num) {
    String current = String.valueOf(num);

    while (current.length() >= 2) {
      if (isPalindrome(current))
        return true;

      StringBuilder next = new StringBuilder();
      for (int i = 0; i < current.length() - 1; i += 2) {
        int sum = Character.getNumericValue(current.charAt(i)) + Character.getNumericValue(current.charAt(i + 1));
        next.append(sum);
      }

      current = next.toString();
    }

    return false;
  }
}
