package BankingApplication;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // This will Store the Customer's Object in ArrayList.
    private static final ArrayList<Customer> customers = new ArrayList<>();

    // This method is used to print the star that separate the messages in prompt
    public static void printStar() {
        System.out.println("\n*************************************************\n");
    }

    // This method is used to add new Customers By asking User's Data
    public static void addNewCustomer(Scanner sc) {
        System.out.println("Enter Customer Name :");
        String name = sc.nextLine();
        System.out.println("Enter Account Type : (Saving Or Current)");
        String accType;
        while (true) {
            /*
             * This will handle the exception to Account Type.
             * If Admin Enters Wrong Type Then a message appears and says Account Type
             * Either Saving Or Current !
             */
            try {
                accType = sc.nextLine();
                if (accType.equalsIgnoreCase("Saving") || accType.equalsIgnoreCase("Current")) {
                    break;
                } else {
                    throw new SomethingWrong("Account Type Either Saving Or Current !\n");
                }
            } catch (SomethingWrong e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Enter Account Number : ");
        String accNum;
        while (true){
            accNum=sc.next();
            if (Customer.isAccountNumberUnique(accNum)){
               break;
            }else{
                System.out.println("Please Type another Account Number This is already assigned to other Customer");
            }
        }
        System.out.println("Enter Opening Balance : Minimum Balance 500 ");
        double balance;
        while (true) {
            /*
             * This try and catch block handle the exception in our program. The exception
             * type that handled by this try and
             * Catch block is.
             * If the user enter wrong input. Then a message will occur saying
             * "Only Number is Supported  : "
             * If the user enter a negative number. Then a message will also say
             * "Hey Balance can't be Negative"
             */
            try {
                balance = sc.nextDouble();
                if (balance < 0) {
                    throw new SomethingWrong("Hey Balance can't be negative !");
                }if (balance<500){
                    throw new SomethingWrong("Please enter more than 500");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Only Number is Supported");
            } catch (SomethingWrong e) {
                System.out.println(e.getMessage());
            } finally {
                sc.nextLine();
            }
        }
        Customer c = new Customer(name, accNum, balance, accType);
        customers.add(c);
        System.out.println("Account Opened Successfully !");
        System.out.println("Details are : ");
        System.out.println(c);
    }

    /*
     * This credit method is used to credit money in the respective Customers
     * Account.
     * It will first ask to enter Bank Account Number and then matches with
     * Customers.
     * If match then asks credit amount.
     */
    public static void credit(Scanner sc) {
        System.out.println("Enter Account Number : ");
        String accNum = sc.nextLine();
        for (Customer c : customers) {
            // This if authenticate the Account Number
            if (c.getBankAccount().equals(accNum)) {
                System.out.println("Enter amount to be credit : ");
                long amount;
                while (true) {
                    /*
                     * This try and catch block handle the exception in our program. The exception
                     * type that handled by this try and
                     * Catch block is.
                     * If the user enter wrong input. Then a message will occur saying
                     * "Only Number is Supported  : "
                     * If the user enter a negative number. Then a message will also say
                     * "Hey Balance can't be Negative"
                     */
                    try {
                        amount = sc.nextLong();
                        if (amount < 0) {
                            throw new SomethingWrong("Hey Amount Should Be Positive!! \n");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Only Number is Supported!! \n");
                    } catch (SomethingWrong e) {
                        System.out.println(e.getMessage());
                    } finally {
                        sc.nextLine();
                    }
                }
                c.addBalance(amount);
                System.out.println("Transaction Successful !");
                System.out.println("Available Balance : " + c.getBalance() + " INR");
                printStar();
                return;
            }
        }
        System.out.println("Not Found Any Customer With Entered Credentials !");

    }

    public static void debit(Scanner sc) {
        System.out.println("Enter Account Number : ");
        String accNum = sc.nextLine();
        for (Customer c : customers) {
            // This if authenticate the Account Number
            if (c.getBankAccount().equals(accNum)) {
                System.out.println("Enter amount to be debit : ");
                long amount;
                while (true) {
                    /*
                     * This try and catch block handle the exception in our program. The exception
                     * type that handled by this try and
                     * Catch block is.
                     * If the user enter wrong input. Then a message will occur saying
                     * "Only Number is Supported  : "
                     * If the user enter a negative number. Then a message will also say
                     * "Hey Balance can't be Negative"
                     */
                    try {
                        amount = sc.nextLong();
                        if (amount < 0) {
                            throw new SomethingWrong("Hey Amount Should Be Positive!! \n");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Only Number is Supported!! \n");
                        sc.nextLine();
                    } catch (SomethingWrong e) {
                        System.out.println(e.getMessage());
                    }
                }
                /*
                 * This if checks, If account don't have more than 500 rupees then you can't
                 * withdraw
                 * If you have then again checks the after the deducting enter amount if balance is
                 * less than 500 then show a message "Insufficient Balance"
                 */
                if (c.getBalance() <= 500 || c.getBalance() - amount <= 500) {
                    System.out.println("Insufficient Balance ");
                } else {
                    double balance = c.getBalance() - amount;
                    c.setBalance(balance);
                    System.out.println("Transaction Successful !");
                    System.out.println("Available Balance : " + c.getBalance() + " INR");
                }
                printStar();
                return;
            }
            System.out.println("Not Found Any Customer With Entered Credentials !");
        }
    }

    public static void checkBalance(Scanner sc) {
        System.out.println("Enter Account Number : ");
        String accNum = sc.nextLine();
        printStar();
        for (Customer c : customers) {
            // This if authenticate the Account Number
            if (c.getBankAccount().equals(accNum)) {
                System.out.println("Holder's Name : " + c.getName());
                System.out.println("Available Balance : " + c.getBalance() + " INR");
                printStar();
                return;
            }
        }
        System.out.println("Not Found Any Customer With Entered Credentials !");
        printStar();
    }

    public static void viewAllCustomers() {
        System.out.println("Name \t Type Of Account\n");
        for (Customer c : customers) {
            System.out.println(c.getName() + "\t " + c.getAccountType());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printStar();
        System.out.println("*** Author : Karan ***\n");
        System.out.println("************** Welcome In ABC Bank ************** ");
        while (true) {
            printStar();
            System.out.println("Hello Admin !");
            System.out.println("Choose What You Want To Do ?");
            System.out.println("1. Create a new Bank Account.");
            System.out.println("2. Debit.");
            System.out.println("3. Credit.");
            System.out.println("4. Check Balance.");
            System.out.println("5. View All Customers.");
            System.out.println("6. Delete a Customer");
            System.out.println("7. Exit");
            printStar();
            int choice;
            while (true) {
                try {
                    choice = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Please Enter Only Number : ");
                } finally {
                    sc.nextLine();
                }
            }
            switch (choice) {
                case 1 -> addNewCustomer(sc);
                case 2 -> debit(sc);
                case 3 -> credit(sc);
                case 4 -> checkBalance(sc);
                case 5 -> viewAllCustomers();
                case 6 -> deleteCustomer(sc);
                case 7 -> {
                    System.out.println("Thank You !!");
                    System.exit(0);
                }
                default -> System.out.println("Wrong input");
            }
        }
    }

    private static void deleteCustomer(Scanner sc) {
        System.out.println("Enter Bank Account Number : ");
        String accNum=sc.next();
        boolean found=false;
        for (Customer customer : customers) {
            if (customer.getBankAccount().equals(accNum)) {
                customers.remove(customer);
                System.out.println("Account Successfully deleted");
                found=true;
                break;
            }
        }
        if (!found){
            System.out.println("Not Found Any Account With Entered Account Number");
        }
    }
}
