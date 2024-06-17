import java.io.Serializable;

public class Account implements Serializable {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Account(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + " successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " successfully.");
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal.");
        }
    }

    public boolean transfer(Account toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred " + amount + " to account " + toAccount.getAccountNumber() + " successfully.");
            return true;
        } else {
            System.out.println("Invalid or insufficient funds for transfer.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolder + ", Balance: " + balance;
    }
}
