import java.util.Scanner;

public class driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Report report = new Report();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Credit/Debit Card");
            System.out.println("2. Ewallet");
            //Report is for testing purpose
            System.out.println("3. Report");
            //------------------------------
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                Payment payment = handleCreditOrDebitCardPayment(scanner);
                if (payment != null) {
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
            } else if (choice == 2) {
                Payment payment = handleEwalletPayment(scanner);
                if (payment != null) {
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
            } else if (choice == 3) {
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
            } else if (choice == 4) {
                System.out.println("Exiting the program.");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    // Credit/Debit
    private static Payment handleCreditOrDebitCardPayment(Scanner scanner) {
        System.out.println("Enter total amount:");
        double totalAmount = scanner.nextDouble();
        
        int cardNo;
        while (true) {
            System.out.println("Enter card number (10 digits):");
            cardNo = scanner.nextInt();
            if (String.valueOf(cardNo).length() == 10) {
                break;
            } else {
                System.out.println("Invalid card number. Please enter a 10-digit card number.");
            }
        }

        String expDate;
        scanner.nextLine();
        while (true) {
            System.out.println("Enter Card Expiration Date (MM/YY):");
            expDate = scanner.nextLine();
            if (expDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                break;
            } else {
                System.out.println("Invalid expiration date format. Please enter in MM/YY format.");
            }
        }

        int cvv;
        while (true) {
            System.out.println("Enter CVV (3 digits):");
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
        
        String phoneNo;
        scanner.nextLine();
        
        while (true) {
            System.out.println("Enter phone number (10 digits):");
            phoneNo = scanner.nextLine();
            if (phoneNo.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Invalid phone number. Please enter a 10-digit phone number.");
            }
        }

        return new Ewallet(totalAmount, phoneNo);
    }
}
