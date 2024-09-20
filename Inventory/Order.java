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
        this.orderID = generaterOrderID();
        this.quantity = quantity;
        this.totalPrice = price;
        this.orders = new ArrayList<>();
    }

    // Generate a random order ID
    public String generaterOrderID(){
        return "O" + String.format("%03d", orderCounter++);
    }

    // Getters and Setters
    public String getOrderID() {
        return orderID;
    }

    public String getBookName(Book book) {
        return book.getBookName();
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Book book : orders) {
            total += book.getPrice();
        }
        return total;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPrice(Book book) {
        return book.getPrice();
    }

    public void addItem(Book item) {
        orders.add(item);
        this.quantity++;
        System.out.printf("Book '%s' added to the order. Quantity is now %d.%n", item.getBookName(), this.quantity);
    }

    public void removeItem(String bookID) {
        Book bookToRemove = null;
        for (Book book : orders) {
            if (book.getBookID().equals(bookID)) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            orders.remove(bookToRemove);
            this.quantity--;
            System.out.printf("Book '%s' removed from the order. Remaining quantity: %d%n", bookToRemove.getBookName(), this.quantity);
        } else {
            System.out.println("Book not found in order.");
        }
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
                System.out.printf("%d. Book: %s | Price: %.2f%n", i + 1, book.getBookName(), book.getPrice());
            }
        }
        System.out.printf("Total Quantity: %d | Total Price: %.2f%n", this.quantity, this.getTotalPrice());
        System.out.println("----------------------\n");
    }

    public static Order processOrder(Scanner scanner, Inventory inventory) {
        boolean continueOrdering = true; 
        Order currentOrder = new Order(0, 0.0); 

        while (continueOrdering) {
            System.out.print("Enter the Book ID to order: ");
            String orderBookID = scanner.nextLine();

            Book bookToOrder = inventory.getBookByID(orderBookID);

            if (bookToOrder != null) {
                System.out.printf("Book Found: %s - %s - %s, Price: %.2f%n", 
                                  bookToOrder.getBookID(), 
                                  bookToOrder.getBookName(),
                                  bookToOrder.getGenre(), 
                                  bookToOrder.getPrice());

                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();

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


