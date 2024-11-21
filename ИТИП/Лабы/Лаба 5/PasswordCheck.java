import java.util.regex.*;

public class PasswordCheck {
    public static void main (String[] args) {
        String pass1 = "abdkfnvnAf12";
        String pass2 = "sdldlD1";
        String pass3 = "sdkdfksdfnksdnf1";
        String pass4 = "sdpkpaskdASDASDSD";
        String pass5 = "SDLFMDSMFL1";
        String[] pass = {pass1, pass2, pass3, pass4, pass5};
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,16}");

        for (int i = 0; i < 5; i++) {
            Matcher matcher = pattern.matcher(pass[i]);
            if (matcher.matches()) {
                System.out.println(pass[i]);
            }
        }
    }
}
