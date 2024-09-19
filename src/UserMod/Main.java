package UserMod;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Registration registration = new Registration();
        Login login = new Login();  // Ensure Login.java exists and is imported
        User user = new User();

        System.out.println("Choose an option: ");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        int option = Integer.parseInt(System.console().readLine());

        switch (option) {
            case 1:
                registration.registerUser();  // Ensure this method exists in Registration.java
                break;
            case 2:
                login.loginUser();  // Ensure loginUser() exists in Login.java
                user.menu();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Invalid option.");
        }
    }
}
