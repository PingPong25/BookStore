import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreditnDebit_Card extends Payment {
    private int cardNo;
    private String exp;
    private int cvv;

    public CreditnDebit_Card(double totalAmount, int cardNo, String exp, int cvv) {
        super(totalAmount);
        
        if (validateCardNumber(cardNo) && validateExpirationDate(exp) && validateCVV(cvv)) {
            this.cardNo = cardNo;
            this.exp = exp;
            this.cvv = cvv;
        } else {
            throw new IllegalArgumentException("Invalid card details provided.");
        }
    }

//CardNo, ExpDate and CVV Validation
    private boolean validateCardNumber(int cardNo) {
        String cardNoStr = String.valueOf(cardNo);
        return cardNoStr.length() == 10;  
    }

    private boolean validateExpirationDate(String exp) {
        return exp.matches("(0[1-9]|1[0-2])/\\d{2}");  
    }

    private boolean validateCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);
        return cvvStr.length() == 3;  
    }

    public void makePayment() {
        System.out.println("Processing credit/debit card payment of $" + totalAmount +
                " with card number " + cardNo + ", expiring on " + exp);
    }

//save(need to do few more testing)
    public void saveFileCard() {
        try {
            FileWriter fileWriter = new FileWriter("creditDebitCard.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Credit/Debit Card Payment Details:");
            printWriter.println("Payment ID: " + paymentId);
            printWriter.println("Card Number: " + cardNo);
            printWriter.println("Expiration Date: " + exp);
            printWriter.println("CVV: " + cvv);
            printWriter.println("Total Amount: $" + totalAmount);
            printWriter.println("----------------------------------");

            printWriter.close();
            System.out.println("Payment successfully!");
        } catch (IOException e) {
            System.err.println("Error saving credit/debit card details to file: " + e.getMessage());
        }
    }

    public String toString() {
        return super.toString() + "\nPayment Type: Credit/Debit Card\nCard Number: " + cardNo +
                "\nExpiration Date: " + exp + "\nCVV: " + cvv;
    }
}
