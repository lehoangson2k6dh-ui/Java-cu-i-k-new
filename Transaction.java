import java.util.Date;

public class Transaction {
    private Date date;
    private String type;
    private double amount;
    private String details;

    public Transaction(String type, double amount, String details) {
        this.date = new Date();
        this.type = type;
        this.amount = amount;
        this.details = details;
    }

    @Override
    public String toString() {
        return " - [" + date + "] Loại: " + type + " | Số tiền: " + String.format("%,.0f", amount) + " VNĐ | CT: " + details;
    }
}