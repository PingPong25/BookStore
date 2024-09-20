public class Book {
    private String bookID;
    private String bookName;
    private String genre;
    private double price;
    private String publisher;

    // Constructor
    public Book(String bookID, String bookName, String genre, double price, String publisher) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.genre = genre;
        this.price = price;
        this.publisher = publisher;
    }

    // Getters
    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setBookID(String bookID){
        this.bookID = bookID;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookID + ", Name: " + bookName + ", Genre: " + genre + 
               ", Price: " + price + ", Publisher: " + publisher;
    }
}
