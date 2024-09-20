import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        List<Order> orderList = new ArrayList<>();  // List to save all orders

        inventory.loadBooksFromFile();

        while (true) {
            System.out.println("Inventory Menu:");
            System.out.println("1. View Book");
            System.out.println("2. Add Book");
            System.out.println("3. Edit Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Order");
            System.out.println("6. Order List");
            System.out.println("7. Exit");
            System.out.print("Please choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Book");
                    System.out.println("Book ID\tName\tGenre\tPrice\tPublisher");
                    //inventory.displayInventory();
                    inventory.readBookFromDirectory();
                    break;

                case 2:
                    System.out.print("Book Name :");
                    String bookName = inventory.checkWord(scanner.nextLine(),"Book Name");

                    System.out.print("Genre :");
                    String genre = inventory.checkWord(scanner.nextLine(),"Genre");

                    double price = inventory.checkWord(scanner, "Price");
                    
                    System.out.print("Publisher :");
                    String publisher = inventory.checkWord(scanner.nextLine(),"Publisher");

                    String bookID = "B" + (inventory.getInventorySize() + 1);
                    Book newBook = new Book(bookID, bookName, genre, price, publisher);
                    inventory.addBook(bookID, bookName, genre, price, publisher);
                    inventory.saveBook(newBook);
                    System.err.println("Successful");
                    break;

                case 3:
                    System.out.print("Please enter the BookID to edit: ");
                    String editBookID = scanner.nextLine();
                    inventory.editBook(editBookID, scanner);
                    break;

                case 4:
                    System.out.print("Please enter the BookID to delete: ");
                    String deleteBookID = scanner.next();
                    inventory.deleteBook(deleteBookID);
                    inventory.deleteBookFromFile(deleteBookID);
                    break;

                case 5:
                    Order newOrder = Order.processOrder(scanner, inventory);
                    if (newOrder != null) {
                        orderList.add(newOrder);  
                        System.out.println("Order saved successfully.");
                    }
                    break;

                case 6:
                    // View all orders in the list
                    System.out.println("\n--- All Orders ---");
                    if (orderList.isEmpty()) {
                        System.out.println("No orders placed yet.");
                    } else {
                        for (Order order : orderList) {
                            System.out.printf("Order ID: %s | Total Quantity: %d | Total Price: %.2f%n",
                                    order.getOrderID(),
                                    order.getTotalQuantity(),
                                    order.getTotalPrice());
                        }
                    }
                    System.out.println("------------------\n");
                    break;
                
                    case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

   
}
