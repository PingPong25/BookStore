package user_module.src;

import java.util.Scanner;

public class User {
    private final String username;
    private final Profile profile;

    public User(String username, Profile profile) {
        this.username = username;
        this.profile = profile;
    }

    public void showMainMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Main Menu:");
            System.out.println("1. Profile");
            System.out.println("2. Orders (Not Implemented)");
            System.out.println("3. Inventory (Not Implemented)");
            System.out.println("4. Sales Report (Not Implemented)");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            
            if (scanner.hasNextInt()) {  // Check if there is valid input
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1 -> profile.showProfileMenu(scanner); // Pass Scanner to Profile
                    case 2 -> System.out.println("Orders functionality not implemented yet.");
                    case 3 -> System.out.println("Inventory functionality not implemented yet.");
                    case 4 -> System.out.println("Sales Report functionality not implemented yet.");
                    case 5 -> {
                        exit = true;
                        System.out.println("Logging out...");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
