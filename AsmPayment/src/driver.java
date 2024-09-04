import java.util.Scanner;

public class driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a payment method:");
        System.out.println("1. Credit/Debit Card");
        System.out.println("2. Ewallet");
        int choice = scanner.nextInt();

        Payment payment = null;

        switch (choice) {
            case 1:
                payment = handleCreditOrDebitCardPayment(scanner);
                break;

            case 2:
                payment = handleEwalletPayment(scanner);
                break;

            default:
                System.out.println("Invalid choices. No payment method selected.");
                break;
        }

        if (payment != null) {
            System.out.println("Payment details:");
            System.out.println(payment.toString());
            payment.makePayment();

            if (payment instanceof CreditnDebit_Card) {
                ((CreditnDebit_Card) payment).saveFileCard();
            } else if (payment instanceof Ewallet) {
                ((Ewallet) payment).saveFileEwallet();
            }
        }

        scanner.close();
    }

//Credit and Debit
    private static Payment handleCreditOrDebitCardPayment(Scanner scanner) {
        System.out.println("Enter total amount:");
        double totalAmount = scanner.nextDouble();

        int cardNo = 0;
        while (true) {
            System.out.println("\nEnter card number (10 digits):");
            cardNo = scanner.nextInt();
            if (String.valueOf(cardNo).length() == 10) {
                break;
            } else {
                System.out.println("Invalid card number. Please enter a 10-digit card number.");
            }
        }

        String expDate = "";
        scanner.nextLine(); 
        while (true) {
            System.out.println("\nEnter Card Expiration Date (MM/YY):");
            expDate = scanner.nextLine();
            if (expDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                break;
            } else {
                System.out.println("Invalid expiration date format. Please enter in MM/YY format.");
            }
        }

        int cvv = 0;
        while (true) {
            System.out.println("\nEnter CVV (3 digits):");
            cvv = scanner.nextInt();
            if (String.valueOf(cvv).length() == 3) {
                break;
            } else {
                System.out.println("Invalid CVV. Please enter a 3-digit CVV.");
            }
        }

        return new CreditnDebit_Card(totalAmount, cardNo, expDate, cvv);
    }

//Ewallet
    private static Payment handleEwalletPayment(Scanner scanner) {
        System.out.println("Enter total amount:");
        double totalAmount = scanner.nextDouble(); 

        String phoneNo = "";
        scanner.nextLine();
        while (true) {
            System.out.println("\nEnter phone number (10 digits):");
            phoneNo = scanner.nextLine();
            if (phoneNo.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Invalid phone number format. Please enter a 10-digit phone number.");
            }
        }

        return new Ewallet(totalAmount, phoneNo);
    }
}
