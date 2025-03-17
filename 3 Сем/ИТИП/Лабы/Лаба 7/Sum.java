public class Sum {

    private static int sum = 0;
    public Sum(){}

    public synchronized void increment(int number) {
        sum += number;
    }

    public int getSum () {
        return sum;
    }

}
