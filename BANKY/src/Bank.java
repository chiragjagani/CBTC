import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private Map<String, Account> accounts;
    private final String DATA_FILE = "accounts.dat";

    public Bank() {
        accounts = new HashMap<>();
        loadAccounts();
    }

    public void createAccount(String accountNumber, String accountHolder) {
        if (!accounts.containsKey(accountNumber)) {
            Account account = new Account(accountNumber, accountHolder);
            accounts.put(accountNumber, account);
            saveAccounts();
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account with this number already exists.");
        }
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            if (fromAccount.transfer(toAccount, amount)) {
                saveAccounts();
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private void saveAccounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    private void loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            accounts = (Map<String, Account>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing accounts found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    public void displayAccounts() {
        for (Account account : accounts.values()) {
            System.out.println(account);
        }
    }
}
