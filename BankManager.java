import java.util.ArrayList;
import java.util.List;

public class BankManager {
    private List<Customer> customers = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private int customerCount = 0;
    private int accountCount = 1000;

    public void createNewCustomer(String name, String phone) {
        customerCount++;
        String cusId = "C" + String.format("%04d", customerCount);
        Customer newCus = new Customer(cusId, name, phone);
        customers.add(newCus);
        System.out.println("\n>>> Đã tạo thành công: " + newCus);
    }

    public void openAccount(String customerId, int accountTypeChoice, double initialBalance) {
        Customer cus = findCustomerById(customerId);
        if (cus == null) {
            System.out.println("-> Lỗi: Không tìm thấy khách hàng có ID: " + customerId);
            return;
        }

        if (initialBalance < 50000) {
            System.out.println("-> Lỗi: Số dư ban đầu tối thiểu phải là 50,000 VNĐ.");
            return;
        }

        accountCount++;
        String accNum = "VN" + accountCount;
        Account newAccount = null;

        switch (accountTypeChoice) {
            case 1:
                newAccount = new SavingsAccount(accNum, cus, initialBalance);
                break;
            case 2:
                newAccount = new CheckingAccount(accNum, cus, initialBalance);
                break;
            default:
                System.out.println("-> Lỗi: Loại tài khoản không hợp lệ.");
                accountCount--;
                return;
        }

        if (newAccount != null) {
            accounts.add(newAccount);
            cus.addAccount(newAccount);
            System.out.println("\n>>> Mở tài khoản thành công! Số TK: " + accNum + " (" + newAccount.getAccountType()
                    + ") cho khách hàng " + cus.getName());
        }
    }

    public void transferMoney(String fromAccNum, String toAccNum, double amount) {
        if (fromAccNum.equals(toAccNum)) {
            System.out.println("-> Lỗi: Không thể chuyển khoản cho chính tài khoản nguồn.");
            return;
        }

        Account fromAcc = findAccountById(fromAccNum);
        Account toAcc = findAccountById(toAccNum);

        if (fromAcc == null || toAcc == null) {
            System.out.println("-> Lỗi: Tài khoản nguồn hoặc đích không tồn tại.");
            return;
        }

        System.out.println("Đang xử lý chuyển khoản...");
        if (fromAcc.withdraw(amount, "Chuyển khoản tới " + toAccNum)) {
            toAcc.receiveTransfer(amount, fromAccNum);
            System.out.println("\n>>> Giao dịch thành công! Đã chuyển " + String.format("%,.0f", amount) + " VNĐ từ "
                    + fromAccNum + " sang " + toAccNum);
        } else {
            System.out.println("-> Giao dịch chuyển khoản thất bại do lỗi rút tiền từ tài khoản nguồn.");
        }
    }

    public Account findAccountById(String accNum) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accNum)) {
                return acc;
            }
        }
        return null;
    }

    private Customer findCustomerById(String cusId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(cusId)) {
                return c;
            }
        }
        return null;
    }

    public void showAllCustomers() {
        System.out.println("\n--- DANH SÁCH KHÁCH HÀNG ---");
        if (customers.isEmpty()) {
            System.out.println("(Trống)");
        } else {
            for (Customer c : customers) {
                System.out.println(c);
                for (Account acc : c.getAccounts()) {
                    System.out.println("    -> TK: " + acc.getAccountNumber() + " | Số dư: "
                            + String.format("%,.0f", acc.getBalance()) + " VNĐ (" + acc.getAccountType() + ")");
                }
            }
        }
        System.out.println("----------------------------\n");
    }
}