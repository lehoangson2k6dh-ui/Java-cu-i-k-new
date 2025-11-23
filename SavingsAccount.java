public class SavingsAccount extends Account {
    private double minBalance = 50000;

    public SavingsAccount(String accountNumber, Customer owner, double initialBalance) {
        super(accountNumber, owner, initialBalance);
    }

    @Override
    public boolean withdraw(double amount, String reason) {
        if (amount <= 0) {
            System.out.println("-> Lỗi: Số tiền rút không hợp lệ.");
            return false;
        }
        if ((this.balance - amount) < minBalance) {
            System.out.println("-> Giao dịch thất bại! Số dư sau khi rút phải còn ít nhất "
                    + String.format("%,.0f", minBalance) + " VNĐ.");
            return false;
        }

        this.balance -= amount;
        addTransaction("RÚT TIỀN TIẾT KIỆM", -amount, reason);
        System.out.println("-> Rút thành công " + String.format("%,.0f", amount) + " VNĐ từ TK Tiết kiệm "
                + this.getAccountNumber());
        return true;
    }

    @Override
    protected String getAccountType() {
        return "Tiết Kiệm";
    }
}