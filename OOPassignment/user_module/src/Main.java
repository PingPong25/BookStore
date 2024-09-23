package user_module.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Registration registration = new Registration();
            Login login = new Login();
            boolean exit = false;

            while (!exit) {
                System.out.println("1. Register\n2. Login\n3. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        registration.registerUser();
                        System.out.println("Registration complete. Please log in.");
                    }
                    case 2 -> {
                        System.out.print("Enter username for login: ");
                        String username = scanner.next();
                        System.out.print("Enter password for login: ");
                        String password = scanner.next();

                        if (login.login(username, password)) {
                            Profile profile = new Profile(username);
                            User user = new User(username, profile); // Pass Profile object to User
                            user.showMainMenu(scanner); // Show main menu after successful login
                        } else {
                            System.out.println("Login failed.");
                        }
                    }
                    case 3 -> {
                        exit = true;
                        System.out.println("Exiting...");
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
}
