import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private final List<Book> books;

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
        File file = new File("Book.txt");

        if(!file.exists()){
            System.out.println("File didn't exist");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine())!= null){
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.printf("Error Message: %s%n", e.getMessage());
        }
    }

    public void loadBooksFromFile() {
        File file = new File("Book.txt");
    
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

    public String checkWord(String input){
        if(input.trim().isEmpty() ){
           if (!(input instanceof String)) {
               System.out.println("Invalid input. Please try again");
           }
        }
        return input;
    } 

    public void editBook(String bookID,Scanner scanner){
       Book book = readBook(bookID);
       if(book != null){
        System.out.printf("Editing %s%n",book.getBookID());
        System.out.printf("Book Name :%s%n",book.getBookName());
        System.out.print("New Book Name: ");
        String bookName = scanner.nextLine();
        checkWord(bookName);

        System.out.printf("Genre :%s%n",book.getGenre());
        System.out.print("New Genre: ");
        String genre = scanner.nextLine();
        checkWord(genre);

        System.out.printf("Price :%.2f%n",book.getPrice());
        System.out.print("New Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.printf("Publisher :%s%n",book.getPublisher());
        System.out.print("New Publisher :");
        String publisher = scanner.nextLine();
        checkWord(publisher);

        book.setBookName(bookName);
        book.setGenre(genre);
        book.setPrice(price);
        book.setPublisher(publisher); 

        updateBookInFile(book);

        System.out.println("Book successfully updated");
       }else{
        System.out.printf("Book ID %s not found\n",bookID);
       }

    }

    public void updateBookInFile(Book updatedBook) {
        File file = new File("Book.txt");
        File tempFile = new File("tempBook.txt");
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
             
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split("\\|");
                if (bookDetails[0].equals(updatedBook.getBookID())) {
                    // Write the updated book details
                    writer.printf("%s %s %s %.2f %s%n", 
                        updatedBook.getBookID(), 
                        updatedBook.getBookName(), 
                        updatedBook.getGenre(), 
                        updatedBook.getPrice(), 
                        updatedBook.getPublisher());
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
    }
    

    public void deleteBook(String bookID){
        Book bookToDelete = null;
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                bookToDelete = book;
                break;
            }
        }
    
        if (bookToDelete != null) {
            books.remove(bookToDelete);
            System.out.println("Book with ID " + bookID + " has been successfully deleted.");
        } else {
            System.out.println("Book with ID " + bookID + " not found.");
        }
    }
        

    public void deleteBookFromFile(String bookID) {
        File file = new File("Book.txt");
        File tempFile = new File("tempBook.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split(" ");
                if (!bookDetails[0].equals(bookID)) {
                    writer.println(line);  
                } else {
                    found = true;  
                }
            }

            if (found) {
                System.out.println("Book with ID " + bookID + " has been deleted.");
            } else {
                System.out.println("Book with ID " + bookID + " not found.");
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
        System.out.printf("Temp file path: %s, Exists: %b%n", tempFile.getAbsolutePath(), tempFile.exists());
    }
    }
    


    public void saveBook(Book book){
         
            try(FileWriter filewriter = new FileWriter("Book.txt",true); 
            PrintWriter printWriter = new PrintWriter(filewriter)){
           
            printWriter.printf("%s %s %s %.2f %s%n",book.getBookID(),book.getBookName(),book.getGenre(),book.getPrice(),book.getPublisher());
           
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
