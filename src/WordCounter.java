import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            //System.out.println(Arrays.toString(words));

            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File not found: " + fileName);
            }

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            int count = 0;

            for (int i = 1; i < args.length; i++) {
                words[i - 1] = args[i];
                // System.out.println("Assigned word: " + words[i - 1]);

                count = 0; // reset the counter

                /* this for-each loop
                 *
                 */
                for (String lineFromList : lines) {
                    Pattern patter = Pattern.compile("\\b" + words[i - 1] + "\\b");
                    Matcher matcher = patter.matcher(lineFromList);

                    /* this while loop matches the word
                     * and counts its appearances in each line of the text
                     */

                    while (matcher.find()) {
                        count++;
                    }

                }
                totalCount += count;
//                List<String> strings = Arrays.asList(words);
//                String longest = strings.stream().max(Comparator.comparingInt(String::length)).get();
//                int max = longest.length();

                //if (words.length < 3) {


                if (args.length < 3) {
                    if (count == 0) {
                        System.out.println("The word '" + words[i - 1] + "' appears " + count + " times.");
                    } else if (count == 1) {
                        System.out.println("The word '" + words[i - 1] + "' appears " + +count + " time.");
                    } else {
                        System.out.println("The word '" + words[i - 1] + "' appears " + +count + " times.");
                    }
                } else {
                    //System.out.printf("--------------------------------%n");
                    //System.out.printf("| %-15s | %-10s |%n", "Word", "Count");
                    //System.out.printf("--------------------------------%n");
                    System.out.printf("| %-15s | %-10d |%n", words[i - 1], count);
                    //System.out.printf("--------------------------------%n");
                    //System.out.printf("| %-15s | %-10d |%n", "Total", totalCount);


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
