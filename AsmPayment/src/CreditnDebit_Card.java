import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreditnDebit_Card extends Payment {
    private int cardNo;
    private String expDate;
    private int cvv;

    public CreditnDebit_Card(double totalAmount, int cardNo, String expDate, int cvv) {
        super(totalAmount);
        this.cardNo = cardNo;
        this.expDate = expDate;
        this.cvv = cvv;
        setPaymentMethod("Credit/Debit Card");
    }

    public boolean validate() {
        // card number
        if (String.valueOf(cardNo).length() != 10) {
            System.out.println("Invalid card number. Please enter a 10-digit card number.");
            return false;
        }
        // exp date
        if (!expDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            System.out.println("Invalid expiration date format. Please enter in MM/YY format.");
            return false;
        }
        // CVV
        if (String.valueOf(cvv).length() != 3) {
            System.out.println("Invalid CVV. Please enter a 3-digit CVV.");
            return false;
        }
        return true;
    }

    public void makePayment() {
        System.out.println("Processing credit/debit card payment of $" + totalAmount);
    }

    public void saveFileCard() {
        try {
            FileWriter fileWriter = new FileWriter("card_payment.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Credit/Debit Card Payment Details:");
            printWriter.println("Payment ID: " + paymentId);
            printWriter.println("Card Number: " + cardNo);
            printWriter.println("Expiration Date: " + expDate);
            printWriter.println("Total Amount: $" + totalAmount);
            printWriter.println("----------------------------------");
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving payment details to file: " + e.getMessage());
        }
    }
}
