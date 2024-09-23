package user_module.src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registration {
    private String username;
    private String password;
    private String gender;
    private String dob; // Date of Birth

    public void registerUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();

            System.out.print("Enter password: ");
            password = scanner.nextLine();

            System.out.print("Enter gender: ");
            gender = scanner.nextLine();

            System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
            dob = scanner.nextLine();
        }

        try (FileWriter writer = new FileWriter("userDetails.txt", true)) {
            writer.write("Username: " + username + ", Password: " + password +
                    ", Gender: " + gender + ", Date of Birth: " + dob);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("User registered successfully!");
    }
}
