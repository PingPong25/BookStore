package user_module.src;

import com.sun.tools.javac.Main;

public class Driver {
    public static void main(String[] args) {
        try {
            Main.main(args); // Call the main method from the Main class
        } catch (Exception e) {  // General Exception to handle any kind of error
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
