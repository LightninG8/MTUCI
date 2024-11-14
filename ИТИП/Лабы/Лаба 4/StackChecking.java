public class StackChecking {

    public static void main (String[] args) {

        CustomStack<String> st1 = new CustomStack<>();
        try {
            st1.pop();
        }
        catch(CustomEmptyStackException e) {
            System.out.println(e.getMessage());
        }

        try {
            String f = st1.get();
        }
        catch (CustomEmptyStackException e) {
            System.out.println(e.getMessage());
        }
    }
}
