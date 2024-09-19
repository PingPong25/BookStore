package UserMod;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Profile {
    public void viewProfile() throws IOException {
        String username;
        System.out.println("Enter username to view profile: ");
        username = System.console().readLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username)) {
                    System.out.println("Username: " + userDetails[0]);
                    System.out.println("Gender: " + userDetails[2]);
                    System.out.println("Date of Birth: " + userDetails[3]);
                    return;
                }
            }
        }

        System.out.println("Profile not found.");
    }

    public void updateProfile() throws IOException {
        String username;
        System.out.println("Enter username to update profile: ");
        username = System.console().readLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username)) {
                    System.out.println("Enter new gender: ");
                    String gender = System.console().readLine();
                    System.out.println("Enter new date of birth (YYYY-MM-DD): ");
                    String dob = System.console().readLine();

                    fileContent.append(username).append(",").append(userDetails[1]).append(",").append(gender).append(",").append(dob).append("\n");
                } else {
                    fileContent.append(line).append("\n");
                }
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt"))) {
                writer.write(fileContent.toString());
            }

            System.out.println("Profile updated successfully.");
        }
    }
}
