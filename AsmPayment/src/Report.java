import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<Payment> payments;
    private List<Order> orders; 
    private int totalPayments;
    private double totalAmount;

    public Report() {
        this.payments = new ArrayList<>();
        this.orders = new ArrayList<>(); 
        this.totalPayments = 0;
        this.totalAmount = 0.0;
    }

    public void addPayment(Payment payment, Order order) {
        payments.add(payment);
        orders.add(order); 
        totalPayments++;
        totalAmount += payment.getTotalAmount();
    }

    public void displayReport() {
        System.out.println("\n---------- Payment Report --------------------------------------------------------------");
        System.out.printf("| %-12s | %-15s | %-8s | %-12s | %-12s | %-10s |\n", 
                          "Payment ID", "Payment Method", "Amount", "Order ID", "Total Qty", "Total Price");
        System.out.println("----------------------------------------------------------------------------------------");

        for (int i = 0; i < payments.size(); i++) {
            Payment payment = payments.get(i);
            Order order = orders.get(i); // Get the corresponding order

            System.out.printf("| %-12s | %-15s | $%-8.2f | %-12s | %-12d | $%-10.2f |\n", 
                              payment.getPaymentId(), 
                              payment.getPaymentMethod(), 
                              payment.getTotalAmount(),
                              order.getOrderID(),
                              order.getTotalQuantity(),
                              order.getTotalPrice());
        }

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("Total Payments: %d\n", totalPayments);
        System.out.printf("Total Amount: $%.2f\n", totalAmount);
    }

    public void displayReport(String paymentMethod) {
        System.out.println("\n---------- " + paymentMethod + " Payment Report --------------------------------------------");
        System.out.printf("| %-12s | %-15s | %-8s | %-12s | %-12s | %-10s |\n", 
                          "Payment ID", "Payment Method", "Amount", "Order ID", "Total Qty", "Total Price");
        System.out.println("----------------------------------------------------------------------------------------");

        double filteredTotalAmount = 0;
        int filteredTotalPayments = 0;

        for (int i = 0; i < payments.size(); i++) {
            Payment payment = payments.get(i);
            Order order = orders.get(i);

            if (payment.getPaymentMethod().equalsIgnoreCase(paymentMethod)) {
                System.out.printf("| %-12s | %-15s | $%-8.2f | %-12s | %-12d | $%-10.2f |\n", 
                                  payment.getPaymentId(), 
                                  payment.getPaymentMethod(), 
                                  payment.getTotalAmount(),
                                  order.getOrderID(),
                                  order.getTotalQuantity(),
                                  order.getTotalPrice());
                filteredTotalAmount += payment.getTotalAmount();
                filteredTotalPayments++;
            }
        }

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("Total " + paymentMethod + " Payments: %d\n", filteredTotalPayments);
        System.out.printf("Total " + paymentMethod + " Amount: $%.2f\n", filteredTotalAmount);
    }

    public void saveReport() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("payment_report.txt"))) {
            writer.println("---------- Payment Report -------------------------------------------------------------");
            writer.printf("| %-12s | %-15s | %-8s | %-12s | %-12s | %-10s |\n", 
                          "Payment ID", "Payment Method", "Amount", "Order ID", "Total Qty", "Total Price");
            writer.println("---------------------------------------------------------------------------------------");

            for (int i = 0; i < payments.size(); i++) {
                Payment payment = payments.get(i);
                Order order = orders.get(i);

                writer.printf("| %-12s | %-15s | $%-8.2f | %-12s | %-12d | $%-10.2f |\n", 
                              payment.getPaymentId(), 
                              payment.getPaymentMethod(), 
                              payment.getTotalAmount(),
                              order.getOrderID(),
                              order.getTotalQuantity(),
                              order.getTotalPrice());
            }

            writer.println("---------------------------------------------------------------------------------------");
            writer.printf("Total Payments: %d\n", totalPayments);
            writer.printf("Total Amount: $%.2f\n", totalAmount);
        } catch (IOException e) {
            System.out.println("Error saving report: " + e.getMessage());
        }
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public int getTotalPayments() {
        return totalPayments;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
