package user_module.src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Profile {
    private String username;
    private String password;
    private String dob; // Date of Birth

    public Profile(String username) {
        loadUserDetails(username);
    }

    private void loadUserDetails(String username) {
        try {
            List<String> fileContent = Files.readAllLines(Paths.get("userDetails.txt"));
            for (String line : fileContent) {
                String[] details = line.split(", ");
                if (details[0].split(": ")[1].equals(username)) {
                    this.username = username;
                    this.password = details[1].split(": ")[1];
                    this.dob = details[3].split(": ")[1];
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void showProfileMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Profile Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            if (scanner.hasNextInt()) {  // Check if there's valid input
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1 -> showProfile();
                    case 2 -> {
                        editProfile(scanner);
                        exit = true; // After editing, exit profile menu to go back to main menu
                    }
                    case 3 -> exit = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public void showProfile() {
        System.out.println("Username: " + username);
        System.out.println("Password: ******");
        System.out.println("Date of Birth: " + dob);
    }

    public void editProfile(Scanner scanner) {
        scanner.nextLine();  // Clear the buffer before accepting string input

        System.out.print("Enter new username (or press Enter to keep the current one): ");
        String newUsername = scanner.nextLine();
        if (!newUsername.trim().isEmpty()) {
            this.username = newUsername;
        }

        System.out.print("Enter new password (or press Enter to keep the current one): ");
        String newPassword = scanner.nextLine();
        if (!newPassword.trim().isEmpty()) {
            this.password = newPassword;
        }

        System.out.print("Enter new Date of Birth (DD/MM/YYYY) (or press Enter to keep the current one): ");
        String newDob = scanner.nextLine();
        if (!newDob.trim().isEmpty()) {
            this.dob = newDob;
        }

        updateUserDetails();
        System.out.println("Profile updated!");

        // Automatically return to the main menu after editing
        System.out.println("Returning to the main menu...");
    }

    private void updateUserDetails() {
        try {
            List<String> fileContent = Files.readAllLines(Paths.get("userDetails.txt"));
            StringBuilder updatedContent = new StringBuilder();

            for (String line : fileContent) {
                String[] details = line.split(", ");
                if (details[0].split(": ")[1].equals(username)) {
                    updatedContent.append("Username: ").append(username)
                                  .append(", Password: ").append(password)
                                  .append(", Gender: ").append(details[2].split(": ")[1])
                                  .append(", Date of Birth: ").append(dob).append(System.lineSeparator());
                } else {
                    updatedContent.append(line).append(System.lineSeparator());
                }
            }

            // Create temporary file with updated content
            Files.write(Paths.get("userDetails_temp.txt"), updatedContent.toString().getBytes());

            // Delete original file and rename temp file to original file name
            Files.delete(Paths.get("userDetails.txt"));
            Files.move(Paths.get("userDetails_temp.txt"), Paths.get("userDetails.txt"));

        } catch (IOException e) {
            System.out.println("Error updating user details: " + e.getMessage());
        }
    }
}
