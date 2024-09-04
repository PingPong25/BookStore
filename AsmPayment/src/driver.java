import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<Payment> payments;
    private int totalPayments;
    private double totalAmount;

    public Report() {
        this.payments = new ArrayList<>();
        this.totalPayments = 0;
        this.totalAmount = 0.0;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        totalPayments++;
        totalAmount += payment.getTotalAmount();
    }

    public void displayReport() {
        System.out.println("\n---------- Payment Report ----------");
        System.out.printf("| %-12s | %-15s | %-8s |\n", "Payment ID", "Payment Method", "Amount");
        System.out.println("--------------------------------------");

        for (Payment payment : payments) {
            System.out.printf("| %-12s | %-15s | $%-8.2f |\n", 
                             payment.getPaymentId(), payment.getPaymentMethod(), payment.getTotalAmount());
        }

        System.out.println("--------------------------------------");
        System.out.printf("Total Payments: %d\n", totalPayments);
        System.out.printf("Total Amount: $%.2f\n", totalAmount);
    }

    public void displayReport(String paymentMethod) {
        System.out.println("\n---------- " + paymentMethod + " Payment Report ----------");
        System.out.printf("| %-12s | %-15s | %-8s |\n", "Payment ID", "Payment Method", "Amount");
        System.out.println("--------------------------------------");

        double totalAmount = 0;
        int totalPayments = 0;

        for (Payment payment : payments) {
            if (payment.getPaymentMethod().equals(paymentMethod)) {
                System.out.printf("| %-12s | %-15s | $%-8.2f |\n", 
                                 payment.getPaymentId(), payment.getPaymentMethod(), payment.getTotalAmount());
                totalAmount += payment.getTotalAmount();
                totalPayments++;
            }
        }

        System.out.println("--------------------------------------");
        System.out.printf("Total " + paymentMethod + " Payments: %d\n", totalPayments);
        System.out.printf("Total " + paymentMethod + " Amount: $%.2f\n", totalAmount);
    }

    public void saveReport() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("payment_report.txt"))) {
            writer.println("---------- Payment Report ----------");
            writer.printf("| %-12s | %-15s | %-8s |\n", "Payment ID", "Payment Method", "Amount");
            writer.println("--------------------------------------");

            for (Payment payment : payments) {
                writer.printf("| %-12s | %-15s | $%-8.2f |\n", 
                             payment.getPaymentId(), payment.getPaymentMethod(), payment.getTotalAmount());
            }

            writer.println("--------------------------------------");
            writer.printf("Total Payments: %d\n", totalPayments);
            writer.printf("Total Amount: $%.2f\n", totalAmount);
        } catch (IOException e) {
            System.out.println("Error saving report: " + e.getMessage());
        }
    }
}
