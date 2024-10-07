import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    /// Users/rohitbamane/repos/java/CS5001-p1-wordcounter/src/pride-and-prejudice.txt
    /// Users/rohitbamane/repos/java/CS5001-p1-wordcounter/src/a-tale-of-two-citiest.txt
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: java WordCounter <filename> <searchTerms>");
            return;
        }
        String fileName = args[0];
        String line = "";

        String[] words = new String[args.length - 1];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));

            for (int i = 1; i < args.length; i++) {
                words[i - 1] = args[i];
                int count = 0;

                while ((line = br.readLine()) != null) {
                    Pattern pattern = Pattern.compile("\\W" + words[i - 1] + "\\W");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        count++;
                    }
                }
                if (count == 1) {
                    System.out.println("The word '" + words[i - 1] + "' appears " + count + " time.");
                } else if (count == 0){
                    System.out.println("The word '" + words[i-1] + "' does not appear in the text file");
                } else System.out.println("The word '" + words[i - 1] + "' appears " + count + " times.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}