import java.io.IOException;
import java.util.Scanner;

public class User {
    public void menu(Report report) throws IOException {
        Profile profile = new Profile();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
        	System.out.println("|==================================================|");
        	System.out.println("|__________________________________________________|");
            System.out.println("|               Choose an option:                  |");
            System.out.println("|1. View Profile                                   |");
            System.out.println("|2. Update Profile                                 |");
            System.out.println("|3. Inventory (To be implemented)                  |");
            System.out.println("|4. Order (To be implemented)                      |");
            System.out.println("|5. Sales Report                                   |");
            System.out.println("|6. Exit                                           |");
            System.out.println("|==================================================|");
        	System.out.println("|__________________________________________________|");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number.");
                continue; // Go back to the start of the loop
            }

            switch (option) {
                case 1:
                    profile.viewProfile(scanner);
                    break;
                case 2:
                    profile.updateProfile(scanner);
                    break;
                case 3:
                    System.out.println("Inventory management not implemented yet.");
                    break;
                case 4:
                    System.out.println("Order management not implemented yet.");
                    break;
                case 5:
                    displayReportMenu(scanner, report); // Call the report menu
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Handle the report logic
    private void displayReportMenu(Scanner scanner, Report report) {
    	System.out.println("\n\n|==================================================|");
        System.out.println("|__________________________________________________|");
        System.out.println("|            Select a Report filter:               |");
        System.out.println("|1. All Payments                                   |");
        System.out.println("|2. Credit/Debit Card Payments                     |");
        System.out.println("|3. E-wallet Payments                              |");
        System.out.println("|==================================================|");
        System.out.println("|__________________________________________________|");

        int filterChoice;
        try {
            filterChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input. Please enter a number.");
            return;
        }

        switch (filterChoice) {
            case 1:
                report.displayReport();
                break;
            case 2:
                report.displayReport("Credit/Debit Card");
                break;
            case 3:
                report.displayReport("E-wallet");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
