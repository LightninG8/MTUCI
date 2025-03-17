import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class CustomStack <T> {

    private int size;
    Stack<T> ourStack;

    public CustomStack () {
        ourStack = new Stack<T>();
        size = 0;
    }

    public void pop () throws CustomEmptyStackException {
        if (size == 0) {
            try (FileWriter f1 = new FileWriter("./log.txt", false);) {
                f1.write("You can`t remove item from stack\n");
            }
            catch (IOException e) {
                //
            }
            throw new CustomEmptyStackException("You can`t remove item from stack");
        }
        else {
            ourStack.pop();
            size -= 1;
        }
    }

    public void push (T a) {
        ourStack.push(a);
        size += 1;
    }

    public T get () throws CustomEmptyStackException {
        if (size == 0) {
            try (FileWriter f1 = new FileWriter("./log.txt", false);) {
                f1.write("You have nothing in your own stack\n");
            }
            catch (IOException e) {
                //
            }
            throw new CustomEmptyStackException("You have nothing in your own stack");
        }
        else {
            return ourStack.peek();
        }
    }

    public int size() {
        return this.size;
    }
}
