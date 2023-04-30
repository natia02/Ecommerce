public class OrderReportEntry {
    private String product_id;
    private String product_name;
    private int quantity;
    private double price;
    private double cost_of_good_sold;
    private double selling_price;

    public OrderReportEntry(String product_id, String product_name, int quantity, double price, double costOfGoodsSold, double sellingPrice) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
        this.cost_of_good_sold = costOfGoodsSold;
        this.selling_price = sellingPrice;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost_of_good_sold() {
        return cost_of_good_sold;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCost_of_good_sold(double cost_of_good_sold) {
        this.cost_of_good_sold = cost_of_good_sold;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }
}