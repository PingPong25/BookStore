package UserMod;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Registration {
    public void registerUser() throws IOException {
        String username, password, gender;
        LocalDate dob;

        System.out.println("Enter username: ");
        username = System.console().readLine();
        System.out.println("Enter password: ");
        password = System.console().readLine();
        System.out.println("Enter gender: ");
        gender = System.console().readLine();
        System.out.println("Enter date of birth (YYYY-MM-DD): ");
        dob = LocalDate.parse(System.console().readLine());

        try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt", true))) {
            writer.println(username + "," + password + "," + gender + "," + dob);
        }

        System.out.println("User registered successfully.");
    }
}
