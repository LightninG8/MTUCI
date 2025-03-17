// Реализация многопоточной программы для поиска наибольшего
// элемента в матрице.
// Создать пул потоков с помощью класса ExecutorService и
// разделить матрицу на равные части, каждую из которых будет
// обрабатывать отдельный поток. После завершения работы всех потоков
// результаты будут сравниваться в главном потоке для нахождения
// наибольшего элемента.

import java.util.*;
import java.util.concurrent.*;

public class MatrixMaxFinder {
    public static void main(String[] args) throws Exception {
        // Создание матрицы
        int[][] matrix = {
                {5, 3, 8},
                {12, 15, 7},
                {9, 2, 14}
        };

        // Создание пула потоков
        ExecutorService executor = Executors.newFixedThreadPool(matrix.length);

        // Разделение задачи на подзадачи
        List<Future<Integer>> results = new ArrayList<>();
        for (int[] row : matrix) {
            results.add(executor.submit(() -> Arrays.stream(row).max().orElse(Integer.MIN_VALUE)));
        }

        // Сбор и сравнение результатов
        int maxElement = Integer.MIN_VALUE;
        for (Future<Integer> result : results) {
            maxElement = Math.max(maxElement, result.get());
        }

        // Завершение работы пула потоков
        executor.shutdown();
        System.out.println("Наибольший элемент в матрице: " + maxElement);
    }
}
