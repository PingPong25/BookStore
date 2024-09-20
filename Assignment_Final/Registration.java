import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class Registration {
    private String username;
    private String password;
    private char gender;
    private LocalDate dob;

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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void registerUser() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter username: ");
        setUsername(scanner.nextLine());
        System.out.println("\nEnter password: ");
        setPassword(scanner.nextLine());

        while (true) {
            System.out.println("\nEnter gender (m/f): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equalsIgnoreCase("m") || input.equalsIgnoreCase("f")) {
                setGender(input.charAt(0));
                break;
            } else {
                System.out.println("\nInvalid input. Please enter 'm' or 'f'.");
            }
        }

        while (dob == null) {
            System.out.println("\nEnter date of birth (YYYY-MM-DD): ");
            String dobInput = scanner.nextLine();
            if (dobInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
                String[] parts = dobInput.split("-");
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
                if (isValidDate(year, month, day)) {
                    setDob(LocalDate.of(year, month, day));
                } else {
                    System.out.println("\nInvalid date. Please ensure the date exists.");
                }
            } else {
                System.out.println("\nInvalid format. Please use YYYY-MM-DD.");
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt", true))) {
            writer.println(getUsername() + "," + getPassword() + "," + getGender() + "," + getDob());
        }

        System.out.println("\n!!!!User registered successfully.!!!!");
    }

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
