package UserMod;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    public boolean loginUser() throws IOException {
        String username, password;
        System.out.println("Enter username: ");
        username = System.console().readLine();
        System.out.println("Enter password: ");
        password = System.console().readLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username) && userDetails[1].equals(password)) {
                    System.out.println("Login successful!");
                    return true;
                }
            }
        }

        System.out.println("Invalid username or password.");
        return false;
    }
}
