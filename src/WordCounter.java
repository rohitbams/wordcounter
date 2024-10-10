import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math;

public class WordCounter {

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Usage: java WordCounter <fileName> <searchTerm>");
                return;
            }

            String fileName = args[0];
            String[] words = new String[args.length - 1];
            String line = "";
            ArrayList<String> lines = new ArrayList<>();
            int totalCount = 0;

            String RESET = "\u001B[0m";
            String RED_TEXT = "\u001B[31m";
            String BLUE_TEXT = "\u001B[32m";
            String YELLOW_TEXT ="\u001B[33m";

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
            int[] count = new int[words.length];
            int longestWordLength = 0;

            for (int i = 1; i < args.length; i++) {
                words[i - 1] = args[i];
            }

            /**
             *  this for loop fills the words[] array with the values from
             *  the args[] array from index args[1] onwards
             *  and finds the longest string in the words[] array
             *  and assigns it's to the int variable longestWordLength
             */
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                longestWordLength = Math.max(longestWordLength, word.length());

                /** this for-each loop
                 *
                 */
                for (String lineFromList : lines) {
                    Pattern pattern = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(lineFromList);
                    /** this while loop matches the word
                     * and counts its appearances in each line of the text
                     */
                    while (matcher.find()) {
                        count[i]++;
                    }
                }
                totalCount += count[i];
            }
            if (args.length == 2) {
                if (count[0] == 0) {
                    System.out.println("The word '" + words[0] + "' appears " + count[0] + " times.");
                } else if (count[0] == 1) {
                    System.out.println("The word '" + words[0] + "' appears " + count[0] + " time.");
                } else {
                    System.out.println("The word '" + words[0] + "' appears " + count[0] + " times.");
                }
            } else {
                String totalCountString = Integer.toString(totalCount);
                System.out.println(RED_TEXT + "|" + "-".repeat(longestWordLength < 6 ? 7 : longestWordLength + 2) + "|" + "-".repeat(7) + "|");
                System.out.printf("| %-" + (longestWordLength < 4 ? 5 : longestWordLength) + "s | %s %s %n", "WORD", "COUNT", "|");
                System.out.println("|" + "-".repeat(longestWordLength < 6 ? 7 : longestWordLength + 2) + "|" + "-".repeat(7) + "|" + RESET);
                for (int i = 0; i < words.length; i++) {
                    System.out.printf(BLUE_TEXT + "| %-" + (longestWordLength < 4 ? 5 : longestWordLength) + "s | %" + (5) + "s %s %n", words[i], count[i], "|" + RESET);
                }
                System.out.println(YELLOW_TEXT + "|" + "-".repeat(longestWordLength < 6 ? 7 : longestWordLength + 2) + "|" + "-".repeat(7) + "|");
                System.out.printf("| %-" + (longestWordLength < 5 ? 5 : longestWordLength) + "s | %" + (5) + "s %s %n", "Total", totalCountString, "|");
                System.out.println("|" + "-".repeat(longestWordLength < 6 ? 7 : longestWordLength + 2) + "|" + "-".repeat(totalCountString.length() > 5 ? totalCountString.length() + 2 : 7) + "|" + RESET);
            }
        } catch (IOException e) {
            System.err.println("File not found: " + args[0]);
        }
    }
}
