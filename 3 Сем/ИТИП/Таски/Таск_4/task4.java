import java.util.*;

public class task4 {

    // 1. Удаление символов, которые повторяются больше 3 раз
    public static String nonRepeat(String str) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char ch : str.toLowerCase().toCharArray()) {
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
        }
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (countMap.get(Character.toLowerCase(ch)) <= 3) {
                result.append(ch);
            }
        }
        return result.toString();
    }

    // 2. Генерация всех комбинаций длины n из алфавита k
    public static List<String> bruteForce(int n, int k) {
        List<String> results = new ArrayList<>();
        generateCombinations("", n, k, results);
        return results;
    }

    private static void generateCombinations(String prefix, int n, int k, List<String> results) {
        if (n == 0) {
            results.add(prefix);
            return;
        }
        for (int i = 0; i < k; i++) {
            generateCombinations(prefix + i, n - 1, k, results);
        }
    }

    // 3. Шифрование и декодирование Вернама
    public static String encode(int[] numbers, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            result.append((char) (numbers[i] + key.charAt(i)));
        }
        return result.toString();
    }

    public static List<Integer> decode(String encoded, String key) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < encoded.length(); i++) {
            numbers.add((int) encoded.charAt(i) - (int) key.charAt(i));
        }
        return numbers;
    }

    // 4. Разделение строки на сбалансированные кластеры скобок
    public static List<String> split(String s) {
        List<String> clusters = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int lastIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (stack.isEmpty()) lastIndex = i;
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    clusters.add(s.substring(lastIndex, i + 1));
                }
            }
        }
        return clusters;
    }

    // 5. Преобразование строки в звездную стенографию
    public static String shortHand(String str) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= str.length(); i++) {
            if (i < str.length() && str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                if (count > 1) {
                    result.append(str.charAt(i - 1)).append("*").append(count);
                } else {
                    result.append(str.charAt(i - 1));
                }
                count = 1;
            }
        }
        return result.toString();
    }

    // 6. Преобразование числа в римские числа
    public static String convertToRome(int num) {
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < romanNumerals.length; i++) {
            while (num >= values[i]) {
                result.append(romanNumerals[i]);
                num -= values[i];
            }
        }
        return result.toString();
    }

    // 7. Подсчет повторяющихся элементов
    public static String uniqueSubstring(String str) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        int maxCount = 0;
        char resultChar = ' ';
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                resultChar = entry.getKey();
            }
        }

        return (str.indexOf(resultChar) % 2 == 0) ? "чет" : "нечет";
    }

    // 8. Прохождение матричного лабиринта
    public static List<String> labirint(int[][] matrix) {
        int n = matrix.length;
        if (matrix[n-1][n-1] < 0 || matrix[0][0] < 0) return Collections.singletonList("Прохода нет");
        
        List<Integer> path = new ArrayList<>();
        int totalCost = 0;
        boolean foundPath = findPath(matrix, n-1, n-1, path);
        
        if (foundPath) {
            Collections.reverse(path);
            StringBuilder pathStr = new StringBuilder();
            for (int cost : path) {
                pathStr.append(cost).append("-");
                totalCost += cost;
            }
            pathStr.setLength(pathStr.length() - 1); // убрать последний '-'
            return Arrays.asList(pathStr.toString(), String.valueOf(totalCost));
        }
        
        return Collections.singletonList("Прохода нет");
    }

    private static boolean findPath(int[][] matrix, int x, int y, List<Integer> path) {
        if (x < 0 || y < 0 || matrix[x][y] < 0) return false; // не можем пройти через стены
        if (x == 0 && y == 0) { // достигли начала
            path.add(matrix[x][y]);
            return true;
        }
        
        path.add(matrix[x][y]);
        // Двигаемся влево
        if (findPath(matrix, x, y - 1, path)) return true;
        // Двигаемся вверх
        path.remove(path.size() - 1); // откат
        return findPath(matrix, x - 1, y, path);
    }

    // 9. Упорядочивание строк на основе чисел
    public static String numericOrder(String str) {
        String[] words = str.split(" ");
        Arrays.sort(words, (a, b) -> Integer.compare(getNumber(a), getNumber(b)));
        
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word.replaceAll("[^a-zA-Z]", "")).append(" "); // убираем числа
        }
        return result.toString().trim();
    }

    private static int getNumber(String word) {
        return Character.getNumericValue(word.charAt(word.length() - 1));
    }

    // 10. Проверка принадлежности к золотому сечению
    public static boolean fibString(String str) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        List<Integer> counts = new ArrayList<>(countMap.values());
        for (int count : counts) {
            if (!isFibonacci(count)) return false;
        }
        return true;
    }

    private static boolean isFibonacci(int n) {
        int a = 0, b = 1;
        while (b < n) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b == n || n == 0;
    }
    public static void main(String[] args) {
        System.out.println(nonRepeat("abracadabra")); 
        System.out.println(nonRepeat("abababcac"));
        
        System.out.println(bruteForce(1, 5));
        System.out.println(bruteForce(2, 2)); 
        
        System.out.println(encode(new int[]{0, 31, 28, 10, 29}, "MKIIT"));
        System.out.println(decode("MTUCI", "MKIIT"));
        
        System.out.println(split("()()()"));
        System.out.println(split("((()))"));
        
        System.out.println(shortHand("abbccc"));
        System.out.println(shortHand("vvvvaajaaaaa"));
        
        System.out.println(convertToRome(8)); 
        System.out.println(convertToRome(1234)); 
        
        System.out.println(uniqueSubstring("31312131"));
        System.out.println(uniqueSubstring("1111111")); 
        
        System.out.println(labirint(new int[][]{ {1, 3, 1}, {1, -1, 1}, {4, 2, 1}})); 
        System.out.println(labirint(new int[][]{ {2, -7, 3}, {-4, -1, 8}, {4, 5, 9}})); 

        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng")); 
        
        System.out.println(fibString("CCCABDD")); 
        System.out.println(fibString("ABC"));
    }
}

