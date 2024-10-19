public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.println((isPalindrome(s))? "palindrome" : "not a palindrome");
        }
    }
    public static String reverseString (String s) {
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result = result + s.charAt(i);
        }
        return result;
    }

    public static boolean isPalindrome (String s) {
        return s.equals(reverseString(s));
    }
}