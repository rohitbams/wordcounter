import java.util.Scanner;

public class Class {     // create a class
    String name = "John"; // give it properties (attributes)
    int age = 5;
    int food = 100;
    int poop;

    public void grow(int add){   // create methods (behaviours) that affect/change the attributes
         add = age + 1;
         System.out.println("you've grown "+add);
    }

    public void lastName(String last){   // create methods (behaviours) that affect/change the attributes
        last = name + last;
        System.out.println("full name is "+last);
    }

    public void eat(int portion){   // create methods (behaviours) that affect/change the attributes
        food = food - portion;
        System.out.println("total hunger level is "+ food);
    }

    public void poop(int portion){   // create methods (behaviours) that affect/change the attributes
        poop =  food - portion;
        System.out.println("poop level is "+ poop);
    }

    public static void main(String[] args) { // create main method
        Class person = new Class();// create a constructor and an object of the class
        person.grow(8); // use that object.method to change attributes
        person.lastName(" Barwell"); // use that object.method to change attributes
        person.eat(10);
        person.poop(2);

        //Scanner sc = new Scanner(System.load(WordCounter));

    }
}
