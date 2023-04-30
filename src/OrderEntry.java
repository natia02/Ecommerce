public class OrderEntry {

    private int quantity;
    private double price;

    public OrderEntry(int quantity, double price){
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
