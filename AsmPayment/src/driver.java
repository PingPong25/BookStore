import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Credit/Debit Card");
            System.out.println("2. E-wallet");
            System.out.println("3. Exit");
            
            int choice = scanner.nextInt();

            if (choice == 1) {
                Payment payment = handleCardPayment(scanner);
                if (payment != null && ((CreditnDebit_Card) payment).validate()) {
                    processPayment(payment);
                } else {
                    System.out.println("Invalid card details.");
                }
            } else if (choice == 2) {
                Payment payment = handleEwalletPayment(scanner);
                if (payment != null && ((Ewallet) payment).validate()) {
                    processPayment(payment);
                } else {
                    System.out.println("Invalid e-wallet details.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting the program.");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    // Process the payment
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

    // For card payments
    private static Payment handleCardPayment(Scanner scanner) {
        System.out.println("Enter total amount:");
        double totalAmount = scanner.nextDouble();

        System.out.println("Enter card number (10 digits):");
        int cardNo = scanner.nextInt();
        
        scanner.nextLine();
        System.out.println("Enter Card Expiration Date (MM/YY):");
        String expDate = scanner.nextLine();

        System.out.println("Enter CVV (3 digits):");
        int cvv = scanner.nextInt();

        return new CreditnDebit_Card(totalAmount, cardNo, expDate, cvv);
    }

    // For e-wallet payments
    private static Payment handleEwalletPayment(Scanner scanner) {
        System.out.println("Enter total amount:");
        double totalAmount = scanner.nextDouble();
        
        scanner.nextLine();
        System.out.println("Enter phone number (10 digits):");
        String phoneNo = scanner.nextLine();

        return new Ewallet(totalAmount, phoneNo);
    }
}
