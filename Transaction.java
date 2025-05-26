import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String productId;
    private int quantity;
    private boolean isPurchase;
    private LocalDateTime timestamp;

    public Transaction(String productId, int quantity, boolean isPurchase) {
        this.productId = productId;
        this.quantity = quantity;
        this.isPurchase = isPurchase;
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(String productId, int quantity, boolean isPurchase, LocalDateTime timestamp) {
        this.productId = productId;
        this.quantity = quantity;
        this.isPurchase = isPurchase;
        this.timestamp = timestamp;
    }

    public String toCSV() {
        return productId + "," + quantity + "," + isPurchase + "," + timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static Transaction fromCSV(String line) {
        String[] parts = line.split(",");
        return new Transaction(
            parts[0],
            Integer.parseInt(parts[1]),
            Boolean.parseBoolean(parts[2]),
            LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }

    @Override
    public String toString() {
        return (isPurchase ? "Purchase" : "Sale") + " | Product ID: " + productId + " | Qty: " + quantity + " | Date: " + timestamp;
    }
}


