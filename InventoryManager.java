import java.io.*;
import java.util.*;

public class InventoryManager {
    private Map<String, Product> products = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();
    private static final String PRODUCT_FILE = "products.csv";
    private static final String TRANSACTION_FILE = "transactions.csv";

    public InventoryManager() {
        loadProducts();
        loadTransactions();
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        saveProducts();
    }

    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        for (Product p : products.values()) {
            System.out.println(p);
        }
    }

    public void processTransaction(String productId, int quantity, boolean isPurchase) {
        Product product = products.get(productId);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }

        if (isPurchase) {
            product.addStock(quantity);
        } else {
            if (!product.removeStock(quantity)) {
                System.out.println("Not enough stock for sale.");
                return;
            }
        }

        Transaction transaction = new Transaction(productId, quantity, isPurchase);
        transactions.add(transaction);
        saveProducts();
        saveTransactions();
        System.out.println("Transaction completed.");
    }

    public void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    private void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = Product.fromCSV(line);
                products.put(product.getId(), product);
            }
        } catch (IOException e) {
            System.out.println("No products file found. Starting with empty inventory.");
        }
    }

    private void saveProducts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCT_FILE))) {
            for (Product product : products.values()) {
                writer.write(product.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving products.");
        }
    }

    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(Transaction.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("No transactions file found. Starting fresh.");
        }
    }

    private void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions.");
        }
    }
}

