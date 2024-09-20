import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Registration registration = new Registration();
        Login login = new Login();
        Profile profile = new Profile();
        Inventory inventory = new Inventory();
        Report report = new Report();
        Scanner scanner = new Scanner(System.in);
        boolean looping = true;

        while (looping) {
            System.out.println("|==================================================|");
            System.out.println("|__________________________________________________|");
            System.out.println("|      Welcome to BookStore Management System       |");
            System.out.println("|__________________________________________________|");
            System.out.println("|==================================================|");
            System.out.println("|              Choose an option                    |");
            System.out.println("|               1. Register                        |");
            System.out.println("|                2. Login                          |");
            System.out.println("|                3. Exit                           |");
            System.out.println("|==================================================|");

            int option = 0;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("\nEnter your choice: ");
                String input = scanner.nextLine();
                if (input.matches("\\d+")) {
                    option = Integer.parseInt(input);
                    validInput = true;
                } else {
                    System.out.println("\nInvalid input. Please enter a number.");
                }
            }

            switch (option) {
                case 1:
                    registration.registerUser();
                    break;
                case 2:
                    if (login.loginUser()) {
                        userMenu(scanner, profile, report, inventory);
                    } else {
                        System.out.println("\nLogin failed.");
                    }
                    break;
                case 3:
                    looping = false;
                    break;
                default:
                    System.out.println("\nInvalid option.");
            }
        }

        scanner.close();
        System.out.println("\nThank you and see you again!");
    }

    private static void userMenu(Scanner scanner, Profile profile, Report report, Inventory inventory) {
        List<Order> orderList = new ArrayList<>();

        while (true) {
            System.out.println("|==================================================|");
            System.out.println("|__________________________________________________|");
            System.out.println("|               Choose an option:                  |");
            System.out.println("|1. View Profile                                   |");
            System.out.println("|2. Update Profile                                 |");
            System.out.println("|3. Inventory                                      |");
            System.out.println("|4. Sales Report                                   |");
            System.out.println("|5. Exit                                           |");
            System.out.println("|==================================================|");
            System.out.println("|__________________________________________________|");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number.");
                continue;
            }

            switch (option) {
                case 1:
                    try {
                        profile.viewProfile(scanner);
                    } catch (IOException e) {
                        System.out.println("Error viewing profile: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        profile.updateProfile(scanner);
                    } catch (IOException e) {
                        System.out.println("Error updating profile: " + e.getMessage());
                    }
                    break;
                case 3:
                    inventoryMenu(scanner, inventory, orderList, report);
                    break;
                case 4:
                     displayReportMenu(scanner, report);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void inventoryMenu(Scanner scanner, Inventory inventory, List<Order> orderList, Report report) {
        inventory.loadBooksFromFile();

        while (true) {
            System.out.println("Inventory Menu:");
            System.out.println("1. View Book");
            System.out.println("2. Add Book");
            System.out.println("3. Edit Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Order");
            System.out.println("6. Order List");
            System.out.println("7. Exit Inventory Menu");
            System.out.print("Please choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("==============Inventory==============");
                    System.out.printf("%-10s %-20s %-15s %-10s %-20s%n", "Book ID", "Name", "Genre", "Price", "Publisher");
                    inventory.readBookFromFile();
                    System.out.println("=====================================");
                    break;

                case 2:
                    System.out.println("Add New Book");
                    System.out.print("Book Name :");
                    String bookName = inventory.checkWord(scanner.nextLine(), "Book Name");

                    System.out.print("Genre :");
                    String genre = inventory.checkWord(scanner.nextLine(), "Genre");

                    double price = inventory.checkWord(scanner, "Price");
                    scanner.nextLine();

                    System.out.print("Publisher :");
                    String publisher = inventory.checkWord(scanner.nextLine(), "Publisher");

                    String bookID = "B" + (inventory.getInventorySize() + 1);
                    Book newBook = new Book(bookID, bookName, genre, price, publisher);
                    inventory.addBook(bookID, bookName, genre, price, publisher);
                    inventory.saveBook(newBook);
                    break;

                case 3:
                    System.out.println("Edit Book");
                    System.out.print("Please enter the BookID to edit: ");
                    String editBookID = scanner.nextLine();
                    inventory.editBook(editBookID, scanner);
                    break;

                case 4:
                    System.out.println("Delete Book");
                    System.out.print("Please enter the BookID to delete: ");
                    String deleteBookID = scanner.nextLine();
                    inventory.deleteBook(deleteBookID, scanner);
                    break;

                case 5:
                    Order newOrder = Order.processOrder(scanner, inventory);
                    if (newOrder != null) {
                        orderList.add(newOrder);
                        System.out.println("Order saved successfully.");
                        handlePayment(scanner, newOrder, report); // Pass the report instance here
                    }
                    break;

                case 6:
                    System.out.println("\n--- All Orders ---");
                    if (orderList.isEmpty()) {
                        System.out.println("No orders placed yet.");
                    } else {
                        for (Order order : orderList) {
                            System.out.printf("Order ID: %s | Total Quantity: %d | Total Price: %.2f%n",
                                    order.getOrderID(),
                                    order.getTotalQuantity(),
                                    order.getTotalPrice());
                        }
                    }
                    System.out.println("------------------\n");
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void handlePayment(Scanner scanner, Order order, Report report) {
        System.out.println("Select a payment option:");

        while (true) {
            System.out.println("1. Credit/Debit Card");
            System.out.println("2. E-wallet");
            System.out.println("3. Exit");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: // Credit/Debit Card
                    while (true) {
                        Payment cardPayment = handleCardPayment(scanner, order.getTotalPrice());
                        if (cardPayment != null && ((CreditnDebit_Card) cardPayment).validate()) {
                            processPayment(cardPayment);
                            cardPayment.setOrderDetails(order); 
                            report.addPayment(cardPayment, order);
                            displayOrderSummary(order);
                            return;
                        } else {
                            System.out.println("Invalid card details. Please try again.");
                        }
                    }

                case 2: // E-wallet
                    while (true) {
                        Payment ewalletPayment = handleEwalletPayment(scanner, order.getTotalPrice());
                        if (ewalletPayment != null && ((Ewallet) ewalletPayment).validate()) {
                            processPayment(ewalletPayment);
                            ewalletPayment.setOrderDetails(order); 
                            report.addPayment(ewalletPayment, order); 
                            displayOrderSummary(order);
                            return;
                        } else {
                            System.out.println("Invalid e-wallet details. Please try again.");
                        }
                    }

                case 3:
                    System.out.println("Exiting payment options.");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayOrderSummary(Order order) {
        System.out.println("\n--- Order Summary ---");
        System.out.printf("Order ID: %s%n", order.getOrderID());
        System.out.printf("Total Quantity: %d%n", order.getTotalQuantity());
        System.out.println("Books Ordered:");
        
        for (Book book : order.getItems()) {
            System.out.printf("- %s | Price: %.2f%n", book.getBookName(), book.getPrice());
        }
        
        System.out.printf("Total Price: %.2f%n", order.getTotalPrice());
        System.out.println("---------------------\n");
    }

    private static void processPayment(Payment payment) {
        System.out.println("Payment details:");
        System.out.println(payment.toString());
        payment.makePayment();
        
        if (payment instanceof CreditnDebit_Card) {
            ((CreditnDebit_Card) payment).saveFileCard();
        } else if (payment instanceof Ewallet) {
            ((Ewallet) payment).saveFileEwallet();
        }
    }

    private static Payment handleCardPayment(Scanner scanner, double totalAmount) {
        System.out.printf("Total amount: %.2f%n", totalAmount);

        System.out.println("Enter card number (10 digits):");
        int cardNo = scanner.nextInt();

        scanner.nextLine();
        System.out.println("Enter Card Expiration Date (MM/YY):");
        String expDate = scanner.nextLine();

        System.out.println("Enter CVV (3 digits):");
        int cvv = scanner.nextInt();

        return new CreditnDebit_Card(totalAmount, cardNo, expDate, cvv);
    }

    private static Payment handleEwalletPayment(Scanner scanner, double totalAmount) {
        System.out.printf("Total amount: %.2f%n", totalAmount);

        System.out.println("Enter phone number (10 digits):");
        String phoneNo = scanner.nextLine();

        return new Ewallet(totalAmount, phoneNo);
    }

    private static void displayReportMenu(Scanner scanner, Report report) {
        System.out.println("\n\n|==================================================|");
        System.out.println("|__________________________________________________|");
        System.out.println("|            Select a Report filter:               |");
        System.out.println("|1. All Payments                                   |");
        System.out.println("|2. Credit/Debit Card Payments                     |");
        System.out.println("|3. E-wallet Payments                              |");
        System.out.println("|==================================================|");
        System.out.println("|__________________________________________________|");

        int filterChoice;
        try {
            filterChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input. Please enter a number.");
            return;
        }

        switch (filterChoice) {
            case 1:
                report.displayReport();
                break;
            case 2:
                report.displayReport("Credit/Debit Card");
                break;
            case 3:
                report.displayReport("E-wallet");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
