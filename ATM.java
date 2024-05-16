package atm_server;

import java.util.*;

public class ATM {
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeUsers();

        
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (authenticateUser(userID, pin)) {
            System.out.println("Authentication successful.");
            displayMenu();
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }

    private static void initializeUsers() {
        
        users.put("518122", new User("518122", "1028", 1000.0));
        users.put("953311", new User("953311", "0709", 500.0));
    }

    private static boolean authenticateUser(String userID, String pin) {
        User user = users.get(userID);
        if (user != null && user.getPin().equals(pin)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    private static void displayMenu() {
        boolean quit = false;
        while (!quit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: ₹" + currentUser.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    Withdraw.withdraw(currentUser, withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    Deposit.deposit(currentUser, depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's user ID: ");
                    String recipientID = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    Transfer.transfer(currentUser, users.get(recipientID), transferAmount);
                    break;
                case 5:
                    TransactionHistory.displayTransactionHistory(currentUser);
                    break;
                case 6:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        Quit.quit();
    }
}

class User {
    private String userID;
    private String pin;
    private double balance;
    private List<Transaction> transactionHistory;

    public User(String userID, String pin, double balance) {
        this.userID = userID;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
}

class Transaction {
    private String type;
    private double amount;
    private String date;

    public Transaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}

class TransactionHistory {
    public static void displayTransactionHistory(User user) {
        List<Transaction> transactions = user.getTransactionHistory();
        if (transactions.isEmpty()) {
            System.out.println("No transactions to display.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}

class Withdraw {
    public static void withdraw(User user, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > user.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }

        user.setBalance(user.getBalance() - amount);
        user.addTransaction(new Transaction("Withdrawal", amount, new Date().toString()));
        System.out.println("Withdrawal successful. Your new balance is: ₹" + user.getBalance());
    }
}

class Deposit {
    public static void deposit(User user, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        user.setBalance(user.getBalance() + amount);
        user.addTransaction(new Transaction("Deposit", amount, new Date().toString()));
        System.out.println("Deposit successful. Your new balance is: ₹" + user.getBalance());
    }
}

class Transfer {
    public static void transfer(User sender, User recipient, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > sender.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        sender.addTransaction(new Transaction("Transfer (To " + recipient.getUserID() + ")", amount, new Date().toString()));
        recipient.addTransaction(new Transaction("Transfer (From " + sender.getUserID() + ")", amount, new Date().toString()));

        System.out.println("Transfer successful. Your new balance is: ₹" + sender.getBalance());
    }
}

class Quit {
    public static void quit() {
        System.out.println("Thank you for using the ATM. Goodbye!");
        System.exit(0);
    }
}

