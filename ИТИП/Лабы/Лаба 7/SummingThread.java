public class SummingThread  extends Thread{

    private Sum sum;
    private int start, finish;
    private int[] array;

    public SummingThread(int[] array, int start, int finish, Sum sum) {
        this.array = array;
        this.start = start;
        this.finish = finish;
        this.sum = sum;
    }

    public void run() {
        for (int i = start; i < finish; i++) {
            sum.increment(array[i]);
        }
    }
}
