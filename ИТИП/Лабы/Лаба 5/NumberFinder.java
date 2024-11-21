import java.util.regex.*;
public class NumberFinder {
    public static void main(String[] args) {
        String text = "The price of the product is $19. 6. 8. 1.3 -5";
        Pattern pattern = Pattern.compile("-?(\\d)+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }
}