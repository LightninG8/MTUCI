import java.util.EmptyStackException;

public class CustomEmptyStackException extends Exception {

    private String message;

    public CustomEmptyStackException() {
        this.message = "Stack is empty";
    }

    public CustomEmptyStackException(String message){

        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
