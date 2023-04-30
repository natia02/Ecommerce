import java.util.LinkedList;
import java.util.List;

public class Product {
    private String product_id;
    private String product_name;
    private double price;
    private int quantity;
    private List<PurchaseEntry> purchaseHistory;
    private List<OrderEntry> orderHistory;

    public Product(String product_id, String product_name, double price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.quantity = 0;
        this.orderHistory = new LinkedList<>();
        this.purchaseHistory = new LinkedList<>();
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<OrderEntry> getOrderHistory() {
        return orderHistory;
    }

    public List<PurchaseEntry> getPurchaseHistory() {
        return purchaseHistory;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setOrderHistory(List<OrderEntry> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setPurchaseHistory(List<PurchaseEntry> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

