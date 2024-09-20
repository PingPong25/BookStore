import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    private String username;
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean loginUser() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username: ");
        setUsername(scanner.nextLine());
        System.out.println("Enter password: ");
        setPassword(scanner.nextLine());

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(getUsername()) && userDetails[1].equals(getPassword())) {
                    System.out.println("\nLogin successful!");
                    return true;
                }
            }
        }

        System.out.println("\nInvalid username or password.");
        return false;
    }
}
