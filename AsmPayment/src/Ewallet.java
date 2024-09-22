import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ewallet extends Payment {
    private String phoneNo;

    public Ewallet(double totalAmount, String phoneNo) {
        super(totalAmount);
        this.phoneNo = phoneNo;
        setPaymentMethod("E-wallet");
    }

    public boolean validate() {
        // phone number
        if (!phoneNo.matches("\\d{10}")) {
            System.out.println("\nInvalid phone number. Please enter a 10-digit phone number.");
            return false;
        }
        return true;
    }

    public void makePayment() {
        System.out.println("\nProcessing E-wallet payment of $" + totalAmount);
    }

    public void saveFileEwallet() {
        try {
            FileWriter fileWriter = new FileWriter("ewallet_payment.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("E-wallet Payment Details:");
            printWriter.println("Payment ID: " + paymentId);
            printWriter.println("Phone Number: " + phoneNo);
            printWriter.println("Total Amount: $" + totalAmount);
            printWriter.println("----------------------------------");
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving payment details to file: " + e.getMessage());
        }
    }
}
