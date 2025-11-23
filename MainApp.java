import java.util.Scanner;

public class MainApp {
    private static BankManager bankManager = new BankManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            System.out.print("Nhập lựa chọn của bạn: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    createNewCustomerUI();
                    break;
                case 2:
                    openAccountUI();
                    break;
                case 3:
                    depositUI();
                    break;
                case 4:
                    withdrawUI();
                    break;
                case 5:
                    transferUI();
                    break;
                case 6:
                    bankManager.showAllCustomers();
                    break;
                case 7:
                    printStatementUI();
                    break;
                case 0:
                    System.out.println("Cảm ơn đã sử dụng ứng dụng. Hẹn gặp lại!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n====== HỆ THỐNG QUẢN LÝ NGÂN HÀNG JAVA OOP ======");
        System.out.println("1. Đăng ký khách hàng mới");
        System.out.println("2. Mở tài khoản (Tiết kiệm/Thanh toán)");
        System.out.println("3. Nạp tiền vào tài khoản");
        System.out.println("4. Rút tiền từ tài khoản");
        System.out.println("5. Chuyển khoản giữa 2 tài khoản");
        System.out.println("6. Xem danh sách Khách hàng & Tài khoản");
        System.out.println("7. In sao kê giao dịch của một tài khoản");
        System.out.println("0. Thoát");
        System.out.println("=================================================");
    }

    private static void createNewCustomerUI() {
        System.out.println("\n--- Đăng ký khách hàng ---");
        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();
        bankManager.createNewCustomer(name, phone);
    }

    private static void openAccountUI() {
        System.out.println("\n--- Mở tài khoản ---");
        System.out.print("Nhập Mã Khách Hàng (ví dụ C0001): ");
        String cusId = scanner.nextLine();
        System.out.println("Chọn loại tài khoản: 1. Tiết kiệm | 2. Thanh toán");
        int type = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập số dư ban đầu (tối thiểu 50,000): ");
        double bal = Double.parseDouble(scanner.nextLine());
        bankManager.openAccount(cusId, type, bal);
    }

    private static void depositUI() {
        System.out.println("\n--- Nạp tiền ---");
        System.out.print("Nhập số tài khoản cần nạp: ");
        String accNum = scanner.nextLine();
        Account acc = bankManager.findAccountById(accNum);
        if (acc != null) {
            System.out.print("Nhập số tiền muốn nạp: ");
            double amount = Double.parseDouble(scanner.nextLine());
            acc.deposit(amount);
        } else {
            System.out.println("-> Lỗi: Không tìm thấy tài khoản.");
        }
    }

    private static void withdrawUI() {
        System.out.println("\n--- Rút tiền ---");
        System.out.print("Nhập số tài khoản muốn rút: ");
        String accNum = scanner.nextLine();
        Account acc = bankManager.findAccountById(accNum);
        if (acc != null) {
            System.out.println("Tài khoản: " + acc.getAccountType() + ". Số dư hiện tại: "
                    + String.format("%,.0f", acc.getBalance()));
            System.out.print("Nhập số tiền muốn rút: ");
            double amount = Double.parseDouble(scanner.nextLine());
            acc.withdraw(amount, "Rút tiền tại ATM");
        } else {
            System.out.println("-> Lỗi: Không tìm thấy tài khoản.");
        }
    }

    private static void transferUI() {
        System.out.println("\n--- Chuyển khoản ---");
        System.out.print("Nhập Số tài khoản NGUỒN: ");
        String fromAcc = scanner.nextLine();
        System.out.print("Nhập Số tài khoản ĐÍCH: ");
        String toAcc = scanner.nextLine();
        System.out.print("Nhập số tiền muốn chuyển: ");
        double amount = Double.parseDouble(scanner.nextLine());
        bankManager.transferMoney(fromAcc, toAcc, amount);
    }

    private static void printStatementUI() {
        System.out.println("\n--- Xem sao kê ---");
        System.out.print("Nhập số tài khoản cần xem: ");
        String accNum = scanner.nextLine();
        Account acc = bankManager.findAccountById(accNum);
        if (acc != null) {
            acc.printStatement();
        } else {
            System.out.println("-> Lỗi: Không tìm thấy tài khoản.");
        }
    }
}