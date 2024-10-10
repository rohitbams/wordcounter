import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WordCounter.
 * WordCounter program is used to count search instances of any number
 * of words and prints out the total instances of all words
 * @author rohit  <rb341@st-andrews.ac.uk>
 * @version 1
 */
public class WordCounter {
    /**
     * main method takes a minimum of two parameters.
     * @param args
     *     The absolute or relative path of any .txt file
     *     The word that is going to be searched
     */
    public static void main(String[] args) {

        try {
            // Print usage message if the user eneters fewer than 2 arguments in the command line
            if (args.length < 2) {
                System.out.println("Usage: java WordCounter <filename> <searchTerm>");
                return;
            }

            String fileName = args[0];
            String[] words = new String[args.length - 1];
            String line = "";
            ArrayList<String> lines = new ArrayList<>();
            int totalCount = 0;

            // New BufferedReader object 'br' to read the text file line by line
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // Fill lines<> ArrayList with lines read by br from the text file
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close(); // Close br to avoid resource leak

            int[] count = new int[words.length];
            int longestWordLength = 0;

            // Fill words[] array with arguments from args[1] onwards
            for (int i = 1; i < args.length; i++) {
                words[i - 1] = args[i];
            }

            // Find the longest string form the words[] array and assign its value to the int variable longestWordLength
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                longestWordLength = Math.max(longestWordLength, word.length());

                // Iterate each line from the lines<> ArrayList
                for (String lineFromList : lines) {
                    // Create Pattern and Matcher objects to match the pattern from each iteration of lineFromList within the lines<> ArrayList
                    Pattern pattern = Pattern.compile("\\b" + word + "\\b");
                    Matcher matcher = pattern.matcher(lineFromList);

                    // Incrementally add int count[i] for item [i] that matches the pattern
                    while (matcher.find()) {
                        count[i]++;
                    }
                }
                // Find the total sum of all items in the count[] array
                totalCount += count[i];
            }
            // Print output message is words[] array has only one item (words[0])
            if (args.length == 2) {
                if (count[0] == 0) {
                    System.out.println("The word '" + words[0] + "' appears " + count[0] + " times.");
                } else if (count[0] == 1) {
                    System.out.println("The word '" + words[0] + "' appears " + count[0] + " time.");
                } else {
                    System.out.println("The word '" + words[0] + "' appears " + count[0] + " times.");
                }
            } else {
                int spacesAroundWord = 2;
                int wordColumnWidthMin = "WORD".length();
                int headerColumnWidthMin = "WORD".length() + spacesAroundWord;
                int headerColumnWidthMax = "TOTAL".length() + spacesAroundWord;
                int spaceAdjuster = 1;
                int totalColumnLength = "TOTAL".length();

                // Print a table if there are multiple items in the words[] array
                String totalCountString = Integer.toString(totalCount);
                System.out.println("|" + "-".repeat(longestWordLength < headerColumnWidthMin ? headerColumnWidthMax : longestWordLength + spacesAroundWord) + "|" + "-".repeat(headerColumnWidthMax) + "|");
                System.out.printf("| %-" + (longestWordLength < wordColumnWidthMin ? wordColumnWidthMin + spaceAdjuster : longestWordLength) + "s | %s %s %n", "WORD", "COUNT", "|");
                System.out.println("|" + "-".repeat(longestWordLength < headerColumnWidthMin ? headerColumnWidthMax : longestWordLength + spacesAroundWord) + "|" + "-".repeat(headerColumnWidthMax) + "|");
                for (int i = 0; i < words.length; i++) {
                    System.out.printf("| %-" + (longestWordLength < wordColumnWidthMin ? wordColumnWidthMin + spaceAdjuster : longestWordLength) + "s | %" + (totalColumnLength) + "s %s %n", words[i], count[i], "|");
                }
                System.out.println("|" + "-".repeat(longestWordLength < headerColumnWidthMin ? headerColumnWidthMax : longestWordLength + spacesAroundWord) + "|" + "-".repeat(headerColumnWidthMax) + "|");
                System.out.printf("| %-" + (longestWordLength < totalColumnLength ? totalColumnLength : longestWordLength) + "s | %" + (totalColumnLength) + "s %s %n", "TOTAL", totalCountString, "|");
                System.out.println("|" + "-".repeat(longestWordLength < totalColumnLength + spaceAdjuster ? headerColumnWidthMax : longestWordLength + spacesAroundWord) + "|" + "-".repeat(totalCountString.length() > totalColumnLength ? totalCountString.length() + spacesAroundWord : headerColumnWidthMax) + "|");
            }
        } catch (IOException e) {
            // Catch exception and print error message if the user doesn't enter or incorrectly enters the file path in the command line argument
            System.err.println("File not found: " + args[0]);
        }
    }
}
