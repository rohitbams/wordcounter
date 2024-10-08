import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
                totalCount = count;
                count = 0;// reset the counter
                for (String lineFromList : lines) {
                    Pattern patter = Pattern.compile("\\b" + words[i - 1] + "\\b");
                    Matcher matcher = patter.matcher(lineFromList);

                    while (matcher.find()) {
                        count++;
                    }
                }
                //List<String> strings = Arrays.asList(words);
                //String longest = strings.stream().max(Comparator.comparingInt(String::length)).get();
                //int max = longest.length();

                if (words.length < 3) {
                    if (count == 0) {
                        System.out.println("The word " + words[i - 1] + " does not appear in your file.");
                    } else if (count == 1) {
                        System.out.println("The word " + words[i - 1] + " appears " + +count + " time.");
                    } else {
                        System.out.println("The word " + words[i - 1] + " appears " + +count + " times." + totalCount);
                        //Arrays.stream(words).forEach(System.out::println);
                    }
                } else {
                    String nline = "\n";

                    //System.out.println(countArray);
                    String.join(nline, words);
                    System.out.printf("|---------------------------------|%n");
                    System.out.printf("|     Word      |      Count      |%n");
                    System.out.printf("|---------------------------------|%n");
                    Arrays.stream(words).forEach(System.out::println);
                    System.out.println(words);
                    System.out.println("total " + totalCount);



                    }
                }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
