import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Challenge_2 {
    public static boolean isPangram(String s) {
        String lowerCase = s.toLowerCase();
        Set<Character> letters = new HashSet<>();
        for (char c : lowerCase.toCharArray()) {
            if (Character.isLetter(c)) {
                letters.add(c);
            }
        }
        return letters.size() == 26;
    }

    public static String longestWord(String s) {
        String[] words = s.split("\\s+");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public static String pangramLongestWord(String s) {
        if (isPangram(s)) {
            return longestWord(s);
        } else {
            return "Not a Pangram";
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter a string:");
            String input = scanner.nextLine();
            
            System.out.println(pangramLongestWord(input));
        }
    }
}
