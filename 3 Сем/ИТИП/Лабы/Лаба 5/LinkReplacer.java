import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkReplacer {
    public static void main(String[] args) {
        String reg = "(?<!https://)[a-z0-9]+\\.[a-z]+";
        String string = "vk.com\n"
                + "vk . com\n"
                + "vk.com get.ru df.ret\n"
                + "google..com\n"
                + "vk.com google.com goerdgl..ru\n" +
                "https://vk.com";

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);

        ArrayList<String> xs = new ArrayList<>();
        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group());
            xs.add(matcher.group());
        }
        System.out.println(xs);

        String result = string;
        for (int i = 0; i < xs.size(); i++){
            Pattern pattern1 = Pattern.compile("\\b(?<!https://)"+xs.get(i));
            Matcher matcher1 = pattern1.matcher(result);

            result = matcher1.replaceAll("https://"+xs.get(i));
        }
        System.out.println(result);
    }
}
