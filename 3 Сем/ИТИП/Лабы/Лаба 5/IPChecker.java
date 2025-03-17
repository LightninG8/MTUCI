import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPChecker {
    public static void main (String[] args) {
        String pass1 = "192.049.254.123";
        String pass2 = "194.29.495.294";
        String pass3 = "300.405.304.204";
        String pass4 = "129.395.394.";
        String pass5 = "193.20.30.10";
        String[] pass = {pass1, pass2, pass3, pass4, pass5};
        Pattern pattern = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}" +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

        for (int i = 0; i < 5; i++) {
            Matcher matcher = pattern.matcher(pass[i]);
            if (matcher.matches()) {
                System.out.println(pass[i]);
            }
        }
    }
}
