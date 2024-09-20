import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Profile {
    public void viewProfile(Scanner scanner) throws IOException {
        System.out.println("\nEnter username to view profile: ");
        String username = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username)) {
                    System.out.println("Username: " + userDetails[0]);
                    System.out.println("Gender: " + userDetails[2]);
                    System.out.println("Date of Birth: " + userDetails[3]);
                    return; // Go back to the User menu
                }
            }
        }

        System.out.println("\nProfile not found.");
    }

    public void updateProfile(Scanner scanner) throws IOException {
        System.out.println("\nEnter username to update profile: ");
        String username = scanner.nextLine();

        StringBuilder fileContent = new StringBuilder();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username)) {
                    found = true;

                    //validate gender
                    String gender;
                    while (true) {
                        System.out.println("\nEnter new gender (m/f): ");
                        gender = scanner.nextLine().trim().toLowerCase();
                        if (gender.equals("m") || gender.equals("f")) {
                            break;
                        } else {
                            System.out.println("\nInvalid input. Please enter 'm' or 'f'.");
                        }
                    }

                    //validate date of birth
                    String dob;
                    while (true) {
                        System.out.println("\nEnter new date of birth (YYYY-MM-DD): ");
                        dob = scanner.nextLine();
                        if (isValidDate(dob)) {
                            break;
                        } else {
                            System.out.println("\nInvalid date format or date does not exist. Please use YYYY-MM-DD.");
                        }
                    }

                    fileContent.append(username).append(",").append(userDetails[1]).append(",").append(gender).append(",").append(dob).append("\n");
                } else {
                    fileContent.append(line).append("\n");
                }
            }
        }

        if (found) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt"))) {
                writer.write(fileContent.toString());
            }
            System.out.println("\nProfile updated successfully.");
        } else {
            System.out.println("\nProfile not found for username: " + username);
        }
    }

    // Method to validate the date
    private boolean isValidDate(String dob) {
        if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false; 
        }

        String[] parts = dob.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        return isValidDate(year, month, day);
    }

    // Check for date
    private boolean isValidDate(int year, int month, int day) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }

        if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                return day <= 29;
            }
            return day <= 28;
        }

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }
        return true;
    }
}
