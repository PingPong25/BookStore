package UserMod;
import java.io.IOException;

public class User {
    public void menu() throws IOException {
        Profile profile = new Profile();  // Ensure Profile has a no-arg constructor

        System.out.println("Choose an option: ");
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. Inventory (To be implemented)");
        System.out.println("4. Order (To be implemented)");
        System.out.println("5. Sales Report (To be implemented)");
        System.out.println("6. Exit");

        int option = Integer.parseInt(System.console().readLine());

        switch (option) {
            case 1:
                profile.viewProfile();  // Ensure method exists in Profile.java
                break;
            case 2:
                profile.updateProfile();  // Ensure method exists in Profile.java
                break;
            case 3:
                System.out.println("Inventory management not implemented yet.");
                break;
            case 4:
                System.out.println("Order management not implemented yet.");
                break;
            case 5:
                System.out.println("Sales report not implemented yet.");
                break;
            case 6:
                System.exit(0);
            default:
                System.out.println("Invalid option.");
        }
    }
}
