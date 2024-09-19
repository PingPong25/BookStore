package UserMod;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        try {
            Main.main(args); // Call the main method from the Main class
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
