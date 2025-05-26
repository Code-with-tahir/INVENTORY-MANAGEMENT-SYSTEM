
public class Product {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public Product(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void addStock(int amount) {
        this.quantity += amount;
    }

    public boolean removeStock(int amount) {
        if (amount <= quantity) {
            this.quantity -= amount;
            return true;
        }
        return false;
    }

    public String toCSV() {
        return id + "," + name + "," + quantity + "," + price;
    }

    public static Product fromCSV(String line) {
        String[] parts = line.split(",");
        return new Product(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
    }

    @Override
    public String toString() {
        return id + " - " + name + " | Qty: " + quantity + " | Price: $" + price;
    }
}

