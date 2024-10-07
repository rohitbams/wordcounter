import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;
import java.util.stream.Stream;

public class wrdcntr {
    public static void main(String[] args){
        File textFile = new File("/Users/rohitbamane/repos/java/CS5001-p1-wordcounter/src/pride-and-prejudice.txt");
        Scanner textScanner = null;
        try {
            textScanner = new Scanner(textFile);
            textScanner.useDelimiter("[chapter]/gi");

            while(textScanner.hasNext()){
                String temp = textScanner.next();
                System.out.println(temp);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

//        String wordArg = "The quick brown fox jumps over the lazy dog";
//        String[] words = wordArg.split(" ");
//        String[] randomArray = {"The", "hello", "djdj,", "brown"};
//        System.out.println(Arrays.toString(words));
//        if (words[0].equals(randomArray[1])){
//            System.out.println(words[0]+randomArray[0]);
//        }
    }
}
