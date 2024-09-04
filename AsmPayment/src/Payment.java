import java.util.Random;

public abstract class Payment {
    protected String paymentId;        
    protected double totalAmount;      
    protected String paymentMethod;    
    protected String payDate;          

    
    public Payment(double totalAmount) {
        this.totalAmount = totalAmount;
        this.paymentId = generateRandomPaymentId(); 
        this.payDate = generateDate();
    }

   
    public abstract void makePayment();

    
    public String getPaymentMethod() {
        return paymentMethod;
    }

    
    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }

    
    private String generateRandomPaymentId() {
        Random random = new Random();
        int randomId = 1000 + random.nextInt(9000); 
        return "PAY" + randomId;
    }

   
    private String generateDate() { //Need to search how to get ramdom date T_T......
        return "24/08/2024"; 
    }

    
    public String toString() {
        return "Payment ID: " + paymentId + 
               "\nAmount: $" + totalAmount + 
               "\nPayment Method: " + paymentMethod + 
               "\nPayment Date: " + payDate;
    }
}
