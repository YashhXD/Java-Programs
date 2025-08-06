import java.util.ArrayList;
import java.util.Scanner;

/**
 * BankAccount Class
 * This is the base class (superclass) for a generic bank account.
 * It holds the fundamental properties and actions common to all accounts.
 */
class BankAccount {
    // Private instance variables to encapsulate the data.
    // This means they can only be accessed by methods within this class.
    private String accountNumber;
    private String accountHolder;
    private double balance;

    /**
     * Constructor for the BankAccount class.
     * It initializes the account with the provided details.
     * The 'this' keyword is used to distinguish between instance variables and parameters.
     * @param accountNumber The unique number for the account.
     * @param accountHolder The name of the account holder.
     * @param balance The initial balance.
     */
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Public getter method for accountNumber
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Deposits a specified amount into the account.
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Successfully deposited Rs " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    /**
     * Withdraws a specified amount from the account.
     * This method uses an if-else statement to check for sufficient funds.
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Successfully withdrew Rs " + amount);
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or invalid amount.");
        }
    }

    /**
     * Displays the current balance of the account.
     */
    public void displayBalance() {
        System.out.println("Current Balance: Rs " + this.balance);
    }

    /**
     * Returns a string with the account's details.
     * @return A formatted string with account information.
     */
    public String getAccountInfo() {
        return "Account Number: " + this.accountNumber + "\nAccount Holder: " + this.accountHolder + "\nBalance: Rs " + this.balance;
    }
}

/**
 * SavingsAccount Class
 * This is a subclass that inherits from BankAccount.
 * It adds specific features like an interest rate and different withdrawal rules.
 */
class SavingsAccount extends BankAccount {
    // Additional private instance variable specific to SavingsAccount
    private double interestRate;

    /**
     * Constructor for the SavingsAccount class.
     * It calls the constructor of the superclass (BankAccount) using 'super()'.
     * @param accountNumber The unique number for the account.
     * @param accountHolder The name of the account holder.
     * @param balance The initial balance.
     * @param interestRate The annual interest rate.
     */
    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        // Call the constructor of the parent class (BankAccount)
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }

    /**
     * Overrides the withdraw method from the BankAccount class.
     * This is an example of Method Overriding.
     * It adds a new rule: the balance cannot drop below Rs 100.
     * @param amount The amount to withdraw.
     */
    @Override
    public void withdraw(double amount) {
        // Get the current balance by calling the superclass's getAccountInfo method indirectly
        // For this simple example, we'll just re-check the balance directly.
        // A better way in complex systems would be a protected getBalance() method.
        double currentBalance = Double.parseDouble(super.getAccountInfo().split("Rs ")[1]);
        
        if (amount > 0 && (currentBalance - amount) >= 100) {
            super.withdraw(amount); // Call the original withdraw method from BankAccount
        } else {
            System.out.println("Withdrawal failed. Amount is invalid or balance would fall below Rs 100.");
        }
    }

    /**
     * Applies interest to the account balance.
     */
    public void applyInterest() {
        double interest = Double.parseDouble(super.getAccountInfo().split("Rs ")[1]) * (interestRate / 100);
        deposit(interest); // Use the inherited deposit method
        System.out.println("Interest of Rs " + interest + " applied.");
    }
}

/**
 * Main Program
 * This class contains the main method to run the bank simulation.
 */
public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Use an ArrayList to hold different types of accounts (demonstrates polymorphism)
        ArrayList<BankAccount> accounts = new ArrayList<>();
        
        // --- DEMONSTRATION OF CORE CONCEPTS ---
        System.out.println("--- Initial OOP Demonstrations ---");
        // 1. Creating objects
        BankAccount genericAccount = new BankAccount("101", "John Doe", 500.0);
        SavingsAccount savings = new SavingsAccount("202", "Jane Smith", 1500.0, 5.0);
        
        // 2. Polymorphism: Storing a subclass object (SavingsAccount) in a superclass reference (BankAccount)
        BankAccount specialAccount = new SavingsAccount("303", "Peter Jones", 200.0, 4.5);
        
        // 3. Method Overriding Demonstration
        System.out.println("\nTesting withdraw on a generic account (Balance Rs 500):");
        genericAccount.withdraw(450); // This will succeed
        genericAccount.displayBalance();

        System.out.println("\nTesting withdraw on a savings account (Balance Rs 200):");
        specialAccount.withdraw(150); // This will fail because balance would go below 100
        specialAccount.displayBalance();
        System.out.println("-------------------------------------\n");
        scanner.close();


        // --- MENU-DRIVEN INTERFACE ---
        // This loop will continue until the user chooses to exit
        while (true) {
            System.out.println("\n--- Bank Account Menu ---");
            System.out.println("1. Create a new Account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Check balance and Account Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String holderName = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    accounts.add(new BankAccount(accNum, holderName, balance));
                    System.out.println("Account created successfully!");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    String depositAccNum = scanner.nextLine();
                    BankAccount depositAccount = findAccount(accounts, depositAccNum);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    String withdrawAccNum = scanner.nextLine();
                    BankAccount withdrawAccount = findAccount(accounts, withdrawAccNum);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    String checkAccNum = scanner.nextLine();
                    BankAccount checkAccount = findAccount(accounts, checkAccNum);
                    if (checkAccount != null) {
                        System.out.println("\n--- Account Details ---");
                        System.out.println(checkAccount.getAccountInfo());
                        System.out.println("-----------------------");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using the bank system. Goodbye!");
                    scanner.close();
                    return; // Exit the main method

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * A helper method to find an account in the list by its account number.
     * @param accounts The list of bank accounts.
     * @param accountNumber The account number to search for.
     * @return The BankAccount object if found, otherwise null.
     */
    public static BankAccount findAccount(ArrayList<BankAccount> accounts, String accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null; // Return null if no account is found
    }
    
}
