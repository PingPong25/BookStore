package user_module.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    public boolean login(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("userDetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line based on ", " and check if it contains both username and password
                String[] details = line.split(", ");
                if (details.length >= 2) {
                    String storedUsername = details[0].split(": ")[1];
                    String storedPassword = details[1].split(": ")[1];

                    // Case-sensitive login, modify if case-insensitive is required
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File format is incorrect.");
        }
        return false;
    }
}
