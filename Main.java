import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Inventory Management System ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Purchase Product");
            System.out.println("4. Sell Product");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Product ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    manager.addProduct(new Product(id, name, qty, price));
                    break;
                case 2:
                    manager.listProducts();
                    break;
                case 3:
                    System.out.print("Product ID to purchase: ");
                    String purchaseId = scanner.nextLine();
                    System.out.print("Quantity: ");
                    int purchaseQty = scanner.nextInt();
                    manager.processTransaction(purchaseId, purchaseQty, true);
                    break;
                case 4:
                    System.out.print("Product ID to sell: ");
                    String saleId = scanner.nextLine();
                    System.out.print("Quantity: ");
                    int saleQty = scanner.nextInt();
                    manager.processTransaction(saleId, saleQty, false);
                    break;
                case 5:
                    manager.listTransactions();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
        System.out.println("Exiting system.");
    }
}

