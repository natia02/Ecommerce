import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Catalog {
    private final Map<String, Product> products;

    public Catalog() {
        products = new HashMap<>();
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void save_product(String product_id, String product_name, double price) {
        if(!products.containsKey(product_id)){
            products.put(product_id, new Product(product_id, product_name, price));
        }else{
            Product product = products.get(product_id);
            product.setProduct_name(product_name);
            product.setPrice(price);
            products.put(product_id, product);
        }

    }

    public void purchase_product(String product_id, int quantity, double price) {
        Product product = products.get(product_id);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            PurchaseEntry entry = new PurchaseEntry(quantity, price);
            List<PurchaseEntry> list = product.getPurchaseHistory();
            list.add(entry);
            product.setPurchaseHistory(list);
            products.put(product_id, product);
        } else {
            System.out.println("There is not product with ID: " + product_id + " in our catalog");
        }
    }

    public void order_product(String product_id, int quantity) {
        Product product = products.get(product_id);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            OrderEntry entry = new OrderEntry(quantity, product.getPrice());
            List<OrderEntry> list = product.getOrderHistory();
            list.add(entry);
            products.put(product_id, product);
        } else if (product == null) {
            System.out.println("There is not product with ID: " + product_id + " in our catalog");
        } else {
            System.out.println("There is not enough product with ID: " + product_id + " in our catalog");
        }
    }

    public int get_quantity_of_product(String product_id) {
        Product product = products.get(product_id);
        if (product != null) {
            return product.getQuantity();
        } else {
            System.out.println("There is not product with ID: " + product_id + " in our catalog");
            return 0;
        }
    }

    public double get_average_price(String product_id) {
        Product product = products.get(product_id);
        if (product != null) {
            List<PurchaseEntry> history = product.getPurchaseHistory();
            double avg = 0;
            int quantity = 0;
            for (PurchaseEntry p : history) {
                quantity += p.getQuantity();
                avg += p.getPrice() * p.getQuantity();
            }
            return (avg / quantity);
        } else {
            System.out.println("There is not product with ID: " + product_id + " in our catalog");
            return 0;
        }
    }

    public double get_average_order_price(String product_id) {
        Product product = products.get(product_id);
        if (product != null) {
            List<OrderEntry> history = product.getOrderHistory();
            double avg = 0;
            int quantity = 0;
            for (OrderEntry p : history) {
                quantity += p.getQuantity();
                avg += p.getPrice() * p.getQuantity();
            }
            return (avg / quantity);
        } else {
            System.out.println("There is not product with ID: " + product_id + " in our catalog");
            return 0;
        }
    }

    public double get_product_profit(String product_id) {
        Product product = products.get(product_id);
        if (product != null) {
            List<OrderEntry> history = product.getOrderHistory();
            int quantity = 0;
            for (OrderEntry p : history) {
                quantity += p.getQuantity();
            }
            return (get_average_order_price(product_id) - get_average_price(product_id)) * quantity;
        } else {
            System.out.println("There is not product with ID: " + product_id + " in our catalog");
            return 0;
        }

    }

    public String get_fewest_product() {
        int min_quantity = Integer.MAX_VALUE;
        String product_name = "";
        for (Product product : products.values()) {
            if (min_quantity >= product.getQuantity()) {
                min_quantity = product.getQuantity();
                product_name = product.getProduct_name();
            }
        }
        if (product_name.equals("")) {
            return "There is no such product";

        }
        return product_name;
    }

    public String get_most_popular_product() {
        String product_name = "";
        int max_orders = Integer.MIN_VALUE;

        for (Product product : products.values()) {
            List<OrderEntry> list = product.getOrderHistory();
            int total_orders = 0;
            for (OrderEntry order_entry : list) {
                total_orders += order_entry.getQuantity();
            }
            if (total_orders > max_orders) {
                max_orders = total_orders;
                product_name = product.getProduct_name();
            }
        }

        return product_name;
    }

    public List<OrderReportEntry> get_orders_report() {
        List<OrderReportEntry> report = new ArrayList<>();

        for (Product product : products.values()) {
            for (OrderEntry order : product.getOrderHistory()) {
                double avg_purchase_price = get_average_price(product.getProduct_id());
                double cost_of_goods_sold = avg_purchase_price * order.getQuantity();
                double selling_price = order.getPrice() * order.getQuantity();

                report.add(new OrderReportEntry(product.getProduct_id(), product.getProduct_name(), product.getQuantity(), product.getPrice(), cost_of_goods_sold, selling_price));
            }
        }

        return report;
    }

    public void export_orders_report(String path) {
        List<OrderReportEntry> report = get_orders_report();
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("Product ID, Product Name, Quantity,Price, COGS, Selling Price\n");
            for (OrderReportEntry entry : report) {
                writer.write(String.format("%s, %s, %d, %.2f, %.2f, %.2f\n",
                        entry.getProduct_id(),
                        entry.getProduct_name(),
                        entry.getQuantity(),
                        entry.getPrice(),
                        entry.getCost_of_good_sold(),
                        entry.getSelling_price()));
            }
        } catch (IOException e) {
            System.err.println("Error exporting orders report: " + e.getMessage());
        }

    }

    public void exit(Scanner scanner) {
        System.out.println("Exiting...");
        scanner.close();
        System.exit(0);
    }


}
