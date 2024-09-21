public class Book extends Item {
    private String genre; 

    public Book(String bookID, String bookName, String genre, double price, String publisher) {
        super(bookID, bookName, price, publisher); 
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return super.toString() + ", Genre: " + genre;
    }
}
