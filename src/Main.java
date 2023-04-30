import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Welcome to the Product Catalog!");
        printHelp();

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                catalog.exit(scanner);
                break;
            } else {
                String[] parts = input.split("\\s+");

                try {
                    switch (parts[0]) {
                        case "help":
                            printHelp();
                            break;
                        case "add":
                            if (parts.length != 4) {
                                System.out.println("Invalid input parameters. Usage: add <product_id> <product_name> <price>");
                                break;
                            }
                            catalog.save_product(parts[1], parts[2], Double.parseDouble(parts[3]));
                            System.out.println("Product added.");
                            break;
                        case "purchase":
                            if (parts.length != 4) {
                                System.out.println("Invalid input parameters. Usage: purchase <product_id> <quantity> <price>");
                                break;
                            }
                            catalog.purchase_product(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
                            System.out.println("Product purchased.");
                            break;
                        case "order":
                            if (parts.length != 3) {
                                System.out.println("Invalid input parameters. Usage: order <product_id> <quantity>");
                                break;
                            }
                            catalog.order_product(parts[1], Integer.parseInt(parts[2]));
                            System.out.println("Product ordered.");
                            break;
                        case "quantity":
                            if (parts.length != 2) {
                                System.out.println("Invalid input parameters. Usage: quantity <product_id>");
                                break;
                            }
                            System.out.println("Quantity: " + catalog.get_quantity_of_product(parts[1]));
                            break;
                        case "avgprice":
                            if (parts.length != 2) {
                                System.out.println("Invalid input parameters. Usage: avgprice <product_id>");
                                break;
                            }
                            System.out.println("Average Purchase Price: " + catalog.get_average_price(parts[1]));
                            break;
                        case "avgorder":
                            if (parts.length != 2) {
                                System.out.println("Invalid input parameters. Usage: avgorder <product_id>");
                                break;
                            }
                            System.out.println("Average Order Price: " + catalog.get_average_order_price(parts[1]));
                            break;
                        case "profit":
                            if (parts.length != 2) {
                                System.out.println("Invalid input parameters. Usage: profit <product_id>");
                                break;
                            }
                            System.out.println("Product Profit: " + catalog.get_product_profit(parts[1]));
                            break;
                        case "fewest":
                            System.out.println("Fewest Product: " + catalog.get_fewest_product());
                            break;
                        case "popular":
                            System.out.println("Most Popular Product: " + catalog.get_most_popular_product());
                            break;
                        case "export":
                            if (parts.length != 2) {
                                System.out.println("Invalid input parameters. Usage: export <filename>");
                                break;
                            }
                            catalog.export_orders_report(parts[1]);
                            System.out.println("Orders report exported to: " + parts[1]);
                            break;
                        default:
                            System.out.println("Invalid command. Type 'help' for available commands.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Please enter valid numbers for the required parameters.");
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
            }
        }

        scanner.close();
    }

    private static void printHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println("help - Show available commands");
        System.out.println("add <product_id> <product_name> <price> - Add a new product");
        System.out.println("purchase <product_id> <quantity> <price> - Purchase a product");
        System.out.println("order <product_id> <quantity> - Order a product");
        System.out.println("quantity <product_id> - Get the quantity of a product");
        System.out.println("avgprice <product_id> - Get the average purchase price of a product");
        System.out.println("avgorder <product_id> - Get the average order price of a product");
        System.out.println("profit <product_id> - Get the profit of a product");
        System.out.println("fewest - Get the product with the fewest quantity");
        System.out.println("popular - Get the most popular product");
        System.out.println("export <filename> - Export orders report to a file");
        System.out.println("exit - Exit the application\n");
    }
}
