import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private final List<Book> books;
    private File file = new File("Book.txt");
    private File tempFile = new File("tempBook.txt");

    // Constructor
    public Inventory() {
        this.books = new ArrayList<>();
    }

    public void addBook(String bookID, String bookName, String genre, double price, String publisher) {
        Book book = new Book(bookID, bookName, genre, price, publisher);
        books.add(book);
    }

    public Book readBook(String bookID) {
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                return book;
            }
        }
        return null;
    }

    public void displayInventory() {
        if (books.isEmpty()) {
            System.out.println("No books available in the inventory.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void readBookFromDirectory(){
        
        if(!file.exists()){
            System.out.println("File didn't exist");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            boolean status=true;
            while((line = reader.readLine())!= null){
                String[] bookDetails = line.split("\\|");
            if (bookDetails.length == 5) {
                
                System.out.printf("%-10s %-20s %-15s %-10s %-20s%n", 
                                  bookDetails[0],  
                                  bookDetails[1],  
                                  bookDetails[2],  
                                  bookDetails[3],  
                                  bookDetails[4]); 
            }
            status = false;
            }
            if(status){
                System.out.println("There's no book available");
            }
        } catch (Exception e) {
            System.out.printf("Error Message: %s%n", e.getMessage());
        }
    }

    public void loadBooksFromFile() {
        if (!file.exists()) {
            System.out.println("No books found. The file doesn't exist.");
            return;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split("\\|");
                if (bookDetails.length == 5) {
                    String bookID = bookDetails[0];
                    String bookName = bookDetails[1];
                    String genre = bookDetails[2];
                    double price = Double.parseDouble(bookDetails[3]);
                    String publisher = bookDetails[4];
                    books.add(new Book(bookID, bookName, genre, price, publisher)); 
                }
            }
        } catch (IOException e) {
            System.out.printf("Error Message: %s%n", e.getMessage());
        }
    }
    

    public int getInventorySize() {
        return books.size();
    }

    public double checkWord(Scanner scanner, String name) {
        double input = 0.0;
        boolean validInput = false;

        while (!validInput) {
            System.out.printf("%s: ", name);
            String userInput = scanner.nextLine();
            if (userInput == null || userInput.trim().isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
    
            try {
                input = Double.parseDouble(userInput);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        scanner.nextLine();  
        return input;  
    }

    public String checkWord(String input,String name){
        while(input ==  null||input.trim().isEmpty()){
            System.out.printf("%s cannot be empty. Please try again.\n",name);
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s: ",name);
        input = scanner.nextLine();
        }
        return input;
    } 
    

    public void editBook(String bookID,Scanner scanner){
       Book book = readBook(bookID);
       if(book != null){
        System.out.printf("Editing %s%n",book.getBookID());
        System.out.printf("Book Name: %s%n",book.getBookName());
        System.out.print("New Book Name: ");
        String bookName = checkWord(scanner.nextLine(),"Book Name");

        System.out.printf("Genre: %s%n",book.getGenre());
        System.out.print("New Genre: ");
        String genre = checkWord(scanner.nextLine(),"Genre");

        System.out.printf("Price: %.2f%n",book.getPrice());
        double price = checkWord(scanner, "New Price: ");

        System.out.printf("Publisher :%s%n",book.getPublisher());
        System.out.print("New Publisher: ");
        String publisher = checkWord(scanner.nextLine(),"Publisher");

        book.setBookName(bookName);
        book.setGenre(genre);
        book.setPrice(price);
        book.setPublisher(publisher); 

        if(updateBookInFile(book)){
            System.out.printf("Book with ID %s successfully updated",bookID);
        }else{
            System.out.println("Failed to update book.");
        }

        //System.out.println("Book successfully updated");
       }else{
        System.out.printf("Book ID %s not found\n",bookID);
       }

    }

    public boolean updateBookInFile(Book updatedBook) {
        boolean status = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
             
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split("\\|");
                if (bookDetails[0].equals(updatedBook.getBookID())) {
                    // Write the updated book details
                    writer.printf("%s|%s|%s|%.2f|%s%n", 
                        updatedBook.getBookID(), 
                        updatedBook.getBookName(), 
                        updatedBook.getGenre(), 
                        updatedBook.getPrice(), 
                        updatedBook.getPublisher());
                        status = true;
                } else {
                    writer.println(line);
                }
            }
            
        } catch (Exception e) {
            System.out.printf("Error Message: %s%n", e.getMessage());
        }
    
        if (file.delete()) {
            System.out.println("Original file deleted.");
            if (tempFile.renameTo(file)) {
                System.out.println("Temporary file renamed to Book.txt. Update successful.");
            } else {
                System.out.println("Failed to rename temporary file. Update failed.");
            }
        } else {
            System.out.println("Failed to delete the original file.");
        }
        return status;
    }
    

    public void deleteBook(String bookID,Scanner scanner){
        Book bookToDelete = null;
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                bookToDelete = book;
                break;
            }
        }
    
        if (bookToDelete != null) {
            System.out.printf("Are you sure you want to delete the book with ID %s? (Y/N) : ",bookID);
            String confirmation = scanner.nextLine().trim();
    
            if (confirmation.equals("Y") || confirmation.equals("y")) {
                books.remove(bookToDelete);
                deleteBookFromFile(bookID);
                System.out.printf("Book with ID %s has been successfully deleted.\n",bookID);
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("Book with ID " + bookID + " not found.");
        }
    }
        

    public void deleteBookFromFile(String bookID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split("\\|");
                if (!bookDetails[0].equals(bookID)) {
                    writer.println(line);  
                } else {
                    found = true;  
                }
            }

            if (found) {
                System.out.printf("Book with ID %s has been deleted.",bookID);
            } else {
                System.out.printf("Book with ID %s not found.",bookID);
            }

        } catch (IOException e) {
            System.out.printf("Error Message: %s%n", e.getMessage());
        }

        if(file.exists()){
        if (!file.delete()) {
            System.out.println("Failed to delete the original file.");
            return;
        }else{
            System.out.println("Original file deleted successfully.");
        }
    }else{
        System.out.println("Original file does not exist.");
    }

    if (tempFile.renameTo(file)) {
        System.out.println("Temp file renamed to original file successfully.");
    } else {
        System.out.println("Failed to rename the temp file.");
    }
    }
    


    public void saveBook(Book book){
         
            try(FileWriter filewriter = new FileWriter("Book.txt",true); 
            PrintWriter printWriter = new PrintWriter(filewriter)){
           
            printWriter.printf("%s|%s|%s|%.2f|%s%n",book.getBookID(),book.getBookName(),book.getGenre(),book.getPrice(),book.getPublisher());
           
           System.out.println("Book have been saved");
        } catch (Exception e) {
            System.out.printf("Error Message: %s",e.getMessage());
        }
    }

    public Book getBookByID(String bookID) {
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                return book;
            }
        }
        return null; 
    }

}
