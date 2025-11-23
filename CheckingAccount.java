public class CheckingAccount extends Account {
    private double overdraftLimit = 2000000;

    public CheckingAccount(String accountNumber, Customer owner, double initialBalance) {
        super(accountNumber, owner, initialBalance);
    }

    @Override
    public boolean withdraw(double amount, String reason) {
        if (amount <= 0) {
            System.out.println("-> Lỗi: Số tiền rút không hợp lệ.");
            return false;
        }
        if ((this.balance - amount) < -overdraftLimit) {
            System.out.println("-> Giao dịch thất bại! Vượt quá hạn mức thấu chi cho phép ("
                    + String.format("%,.0f", overdraftLimit) + " VNĐ).");
            return false;
        }

        this.balance -= amount;
        addTransaction("RÚT TIỀN THANH TOÁN", -amount, reason);
        System.out.println("-> Rút thành công " + String.format("%,.0f", amount) + " VNĐ từ TK Thanh toán "
                + this.getAccountNumber());
        if (this.balance < 0) {
            System.out.println("!!! Cảnh báo: Bạn đang sử dụng thấu chi. Số dư hiện tại: "
                    + String.format("%,.0f", this.balance) + " VNĐ");
        }
        return true;
    }

    @Override
    protected String getAccountType() {
        return "Thanh Toán";
    }
}