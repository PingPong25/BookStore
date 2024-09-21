public class Item {
    private String itemID;
    private String name;
    private double price;
    private String publisher;

    public Item(String itemID, String name, double price, String publisher) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.publisher = publisher;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "|Book ID: " + itemID + ", Name: " + name + ", Price: " + price + ", Publisher: " + publisher;
    }
}
