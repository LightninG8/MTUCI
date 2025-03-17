import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharBeginFind {
    public static void main (String[] args) {
        String text = "I once had a girl. Or should i say, she once had me." +
                "She show me her room. Isn`t it good, norwegian wood." +
                "She asked me to stay and she told me to sit anywhere." +
                "So I looked around and I noticed there wasn`t a chair." +
                "I sat on the rug, biding my time, drinking her wine." +
                "We talked until two and then she said it`s time for bed." +
                "She told that she worked in the morning and started to laugh." +
                "I told that I didn`t and crawl of to sleep in the bath." +
                "And when I awoke I was alone, this bird had flown." +
                "So, I lid the fire. Isn`t it good, norwegian wood.";
        String firstLetter = "a";
        Pattern pattern = Pattern.compile("(?<=[\\s\\.,`;:])"+firstLetter+"[\\w]*(?=[\\s\\.,`;:])?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
