import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ewallet extends Payment {
    private String phoneNo;

    public Ewallet(double totalAmount, String phoneNo) {
        super(totalAmount);

        if (validatePhoneNumber(phoneNo)) {
            this.phoneNo = phoneNo;
        } else {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
    }

    private boolean validatePhoneNumber(String phoneNo) {
        return phoneNo.matches("\\d{10}");  // Matches a 10-digit phone number
    }

    public void makePayment() {
        System.out.println("Processing e-wallet payment of $" + totalAmount +
                " using phone number " + phoneNo);
    }

    public void saveFileEwallet() {
        try {
            FileWriter fileWriter = new FileWriter("ewallet.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Ewallet Payment Details:");
            printWriter.println("Payment ID: " + paymentId);
            printWriter.println("Phone Number: " + phoneNo);
            printWriter.println("Total Amount: $" + totalAmount);
            printWriter.println("----------------------------------");

            printWriter.close();
            System.out.println("Payment details saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving e-wallet details to file: " + e.getMessage());
        }
    }

    public String toString() {
        return super.toString() + "\nPayment Type: Ewallet\nPhone Number: " + phoneNo;
    }
}
