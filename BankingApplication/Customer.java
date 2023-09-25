package BankingApplication;
import java.util.HashSet;
import java.util.Set;

public class Customer {
    private String name;
    private String bankAccount;
    private double balance;
    private String accountType;

    // Keep track of used account numbers to ensure uniqueness
    private static Set<String> usedAccountNumbers = new HashSet<>();
    public Customer(String name, String bankAccount, double balance, String accountType) {
        this.name = name;

        // Check if the account number is unique
        if (isAccountNumberUnique(bankAccount)) {
            this.bankAccount = bankAccount;
            usedAccountNumbers.add(bankAccount);
        } else {
            throw new IllegalArgumentException("Account number is not unique.");
        }

        this.balance = balance;
        this.accountType = accountType;
    }

    public void addBalance(long balance){
        this.balance+=balance;
    }

    public void setBalance(double balance){
        this.balance=balance;
    }
    public String getName() {
        return name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public static boolean isAccountNumberUnique(String accountNumber) {
        return !usedAccountNumbers.contains(accountNumber);
    }

    @Override
    public String toString() {
        return "Customer Name : " + name + '\n' +
                "Account Number : " + bankAccount + '\n' +
                "Balance : " + balance +" INR"+'\n'+
                "Account Type : " + accountType + '\n';
    }
}
