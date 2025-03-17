// Реализация многопоточной программы для вычисления суммы
// элементов массива.
// Создать два потока, которые будут вычислять сумму элементов
// массива по половинкам, после чего результаты будут складываться в
// главном потоке.


public class ArraySum {
    public static void main(String[] args) {

        int[] arr = new int[] {1, 9, 10, 7, -3, 6, 5};
        int m = arr.length / 2;

        Sum mainSum = new Sum();
        SummingThread thread1 = new SummingThread(arr, 0, m, mainSum);
        SummingThread thread2 = new SummingThread(arr, m, arr.length, mainSum);

        thread1.start();
        thread2.start();
    
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mainSum.getSum());

    }
}
