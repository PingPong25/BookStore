import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    private static int orderCounter = 1;
    private String orderID;
    private int quantity;
    private double totalPrice;
    private List<Book> orders;

    // Constructor
    public Order(int quantity, double price) {
        this.orderID = generateOrderID();
        this.quantity = quantity;
        this.totalPrice = price;
        this.orders = new ArrayList<>();
    }

    // Generate a random order ID
    public String generateOrderID() {
        return "ORD" + String.format("%03d", orderCounter++);
    }

    // Getters and Setters
    public String getOrderID() {
        return orderID;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Book book : orders) {
            total += book.getPrice();
        }
        return total;
    }

    public void addItem(Book item) {
        orders.add(item);
        this.quantity++;
        System.out.printf("Book '%s' added to the order. Quantity is now %d.%n", item.getName(), this.quantity);
    }

    public List<Book> getItems() {
        return orders;
    }

    public int getTotalQuantity() {
        return this.quantity;
    }

    public void listCurrentOrders() {
        System.out.println("\n--- Current Orders ---");
        if (orders.isEmpty()) {
            System.out.println("No orders yet.");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                Book book = orders.get(i);
                System.out.printf("%d. Book: %s | Price: %.2f%n", i + 1, book.getName(), book.getPrice());
            }
        }
        System.out.printf("Total Quantity: %d | Total Price: %.2f%n", this.quantity, this.getTotalPrice());
        System.out.println("----------------------\n");
    }

    public static Order processOrder(Scanner scanner, Inventory inventory) {
        boolean continueOrdering = true; 
        Order currentOrder = new Order(0, 0.00); 

        while (continueOrdering) {
            System.out.println("--- Process Order ---");
            System.out.print("Enter the Book ID to order: ");
            String orderBookID = scanner.nextLine();

            Book bookToOrder = inventory.getBookByID(orderBookID);

            if (bookToOrder != null) {
                System.out.println("Book Found......");
                System.out.printf("%-10s %-20s %-15s %-10s %-20s%n", "Book ID", "Name", "Genre", "Price", "Publisher");
                System.out.printf("%-10s %-20s %-15s %-10s %-20s%n", 
                                  bookToOrder.getItemID(), 
                                  bookToOrder.getName(),
                                  bookToOrder.getGenre(), 
                                  bookToOrder.getPrice(),
                                  bookToOrder.getPublisher());

                double quantity = inventory.checkWord(scanner, "Quantity");

                for (int i = 0; i < quantity; i++) {
                    currentOrder.addItem(bookToOrder);
                }

                currentOrder.listCurrentOrders();

                System.out.print("Do you want to order another book? (Y/N): ");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("N")) {
                    continueOrdering = false;
                    System.out.println("Thank you for your order!");
                }
            } else {
                System.out.println("Book not found in the inventory.");
            }
        }
        return currentOrder;
    }
}
