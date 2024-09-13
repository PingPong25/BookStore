import java.util.Scanner;


public class driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Report report = new Report();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Credit/Debit Card");
            System.out.println("2. Ewallet");
            System.out.println("3. Report");
            System.out.println("4. Exit");
            
            int choice = scanner.nextInt();

            if (choice == 1) {
                Payment payment = handleCardPayment(scanner);
                if (payment != null && ((CreditnDebit_Card) payment).validate()) {
                    processPayment(report, payment);
                }
            } else if (choice == 2) {
                Payment payment = handleEwalletPayment(scanner);
                if (payment != null && ((Ewallet) payment).validate()) {
                    processPayment(report, payment);
                }
            } else if (choice == 3) {
                displayReportMenu(scanner, report);
            } else if (choice == 4) {
                System.out.println("Exiting the program.");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

	// determine which payment method
    private static void processPayment(Report report, Payment payment) {
        System.out.println("Payment details:");
        System.out.println(payment.toString());
        payment.makePayment();
		
        if (payment instanceof CreditnDebit_Card) {
            ((CreditnDebit_Card) payment).saveFileCard();
        } else if (payment instanceof Ewallet) {
            ((Ewallet) payment).saveFileEwallet();
        }
        report.addPayment(payment);
    }

	//able to serach and see which report based on payment method
    private static void displayReportMenu(Scanner scanner, Report report) {
        System.out.println("Select a report filter:");
        System.out.println("1. All Payments");
        System.out.println("2. Credit/Debit Card Payments");
        System.out.println("3. E-wallet Payments");
        int filterChoice = scanner.nextInt();

        if (filterChoice == 1) {
            report.displayReport();
        } else if (filterChoice == 2) {
            report.displayReport("Credit/Debit Card");
        } else if (filterChoice == 3) {
            report.displayReport("E-wallet");
        } else {
            System.out.println("Invalid choice.");
        }
    }

	//for card
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

	// for ewallet 
    private static Payment handleEwalletPayment(Scanner scanner) {
        System.out.println("Enter total amount:");
        double totalAmount = scanner.nextDouble();
        
        scanner.nextLine();
        System.out.println("Enter phone number (10 digits):");
        String phoneNo = scanner.nextLine();

        return new Ewallet(totalAmount, phoneNo);
    }
}
