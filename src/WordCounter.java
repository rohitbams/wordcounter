import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    /// Users/rohitbamane/repos/java/CS5001-p1-wordcounter/src/pride-and-prejudice.txt
    /// Users/rohitbamane/repos/java/CS5001-p1-wordcounter/src/a-tale-of-two-citiest.txt
    public static void main(String[] args) {

        try {
            if (args.length < 2) {
                System.out.println("Usage: java WordCounter <filename> <searchTerms>");
                return;
            }
            String fileName = args[0];
            if (args[0] == null){
                System.out.println("File not found: " + fileName);
                return;
            }

            List<String> line = new ArrayList<>();
            String[] words = new String[args.length - 1];

            BufferedReader br =  new BufferedReader(new FileReader(fileName));
            try {
                int count = 0;
                Pattern pattern;
                Matcher matcher;

                for (int i = 1; i < args.length; i++) {
                    words[i - 1] = args[i];
                    count = 0;

                    while ((line = br.readLine()) != null) {
                        pattern = Pattern.compile("\\b"+ words[i - 1] +"\\b");
                        matcher = pattern.matcher(line);
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
                    br.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}