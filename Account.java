import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private String accountNumber;
    protected double balance;
    private Customer owner;
    protected List<Transaction> transactions;

    public Account(String accountNumber, Customer owner, double initialBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        addTransaction("MỞ TÀI KHOẢN", initialBalance, "Số dư ban đầu");
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addTransaction("NẠP TIỀN", amount, "Nạp tại quầy/ATM");
            System.out.println(
                    "-> Nạp thành công " + String.format("%,.0f", amount) + " VNĐ vào TK " + this.accountNumber);
        } else {
            System.out.println("-> Lỗi: Số tiền nạp không hợp lệ.");
        }
    }

    public void receiveTransfer(double amount, String fromAcc) {
        this.balance += amount;
        addTransaction("NHẬN CHUYỂN KHOẢN", amount, "Từ TK: " + fromAcc);
    }

    public abstract boolean withdraw(double amount, String reason);

    protected void addTransaction(String type, double amount, String details) {
        this.transactions.add(new Transaction(type, amount, details));
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getOwner() {
        return owner;
    }

    public void printStatement() {
        System.out.println("\n--- SAO KÊ TÀI KHOẢN: " + accountNumber + " (" + getAccountType() + ") ---");
        System.out.println("Chủ TK: " + owner.getName());
        System.out.println("Số dư hiện tại: " + String.format("%,.0f", balance) + " VNĐ");
        System.out.println("Lịch sử giao dịch:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("----------------------------------------------\n");
    }

    protected abstract String getAccountType();
}