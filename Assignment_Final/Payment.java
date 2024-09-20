import java.util.Random;

public abstract class Payment {
    protected String paymentId;
    protected double totalAmount;
    protected String paymentMethod;
    private Order orderDetails;

    public Payment(double totalAmount) {
        this.totalAmount = totalAmount;
        this.paymentId = generateRandomPaymentId();
    }

    // Generate random Payment ID
    private String generateRandomPaymentId() {
        Random random = new Random();
        int randomId = 1000 + random.nextInt(9000);
        return "PAY" + randomId;
    }

    public abstract void makePayment();

    // Getter methods
    public String getPaymentId() {
        return paymentId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // Setter for payment method
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    //To store details
    public void setOrderDetails(Order order) {
        this.orderDetails = order;
    }

    public Order getOrderDetails() {
        return orderDetails;
    }

    public String toString() {
        return "Payment ID: " + paymentId + "\nAmount: $" + totalAmount + "\nPayment Method: " + paymentMethod;
    }
}
