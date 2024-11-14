public class ArrayAverage {

    public static void main (String[] args) {


        Object[] a1 = {};
        int n = a1.length;
        try{
            System.out.println(avarageIntSum(a1, 0) / n);
        }
        catch(ClassCastException e) {
            System.out.println("There is no int array");
        }
        catch(ArrayIndexOutOfBoundsException f) {
            System.out.println("You out of your bounds");
        }
        catch(ArithmeticException g) {
            System.out.println("devided by zero");
        }
        //double d = 0 / 0;
    }

    public static int avarageIntSum (Object[] arr, int size) {
        int av = 0;
        for (int i = 0; i < size; i++) {
            av += (int) arr[i];
        }
        return  av;
    }
}
