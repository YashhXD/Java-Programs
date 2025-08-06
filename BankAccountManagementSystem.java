import java.util.Scanner;

// BankAccount class
class BankAccount
{
    private String accNum;
    private String accHolder;
    private double bal;

    // constructor
    public BankAccount(String a, String b, double c)
    {
        accNum = a;
        accHolder = b;
        bal = c;
    }

    // deposit money
    public void deposit(double amt)
    {
        if(amt>0)
        {
            bal = bal + amt;
            System.out.println("Money deposited. Balance now: Rs " + bal);
        }
        else
        {
          System.out.println("Invalid deposit.");
        }
        
    }

    // withdraw money
    public void withdraw(double amt)
    {
        if(amt>0 && amt<=bal)
        {
            bal = bal - amt;
            System.out.println("Money withdrawn. Balance now: Rs " + bal);
        }
        else
        {
          System.out.println("Not enough balance or wrong amount.");
        }
        
    }

    // check balance
    public void displayBalance()
    {
        System.out.println("Your balance is Rs " + bal);
    }

    // info
    public String getAccountInfo()
    {
        return "Account No: " + accNum + "\nHolder: " + accHolder + "\nBalance: Rs " + bal;
    }

    // getters and setters
    public double getBalance()
    {
        return bal;
    }

    public void setBalance(double x)
    {
        bal = x;
    }
}

// Savings account
class SavingsAccount extends BankAccount
{
    private double rate;

    public SavingsAccount(String a, String b, double c, double d)
    {
        super(a,b,c);
        rate = d;
    }

    // override withdraw
    public void withdraw(double amt)
    {
        if(amt>0 && (getBalance()-amt)>=100)
        {
            setBalance(getBalance()-amt);
            System.out.println("Money withdrawn. Balance now: Rs " + getBalance());
        }
        else
        {
            System.out.println("Cannot withdraw. Need to keep at least 100 Rs.");
        }
    }

    // add interest
    public void applyInterest()
    {
        double intr = getBalance()*(rate/100);
        setBalance(getBalance()+intr);
        System.out.println("Interest added. Balance now: Rs " + getBalance());
    }
}

// main program
public class BankAccountManagementSystem
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        BankAccount acc = null;
        int choice = 0;

        System.out.println("Welcome to Bank Account System");

        while(choice!=5)
        {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    System.out.print("Enter account type (1-Regular, 2-Savings): ");
                    int type = sc.nextInt();
                    sc.nextLine();//dummy line

                    System.out.print("Enter account number: ");
                    String num = sc.nextLine();
                    sc.nextLine();//dummy line

                    System.out.print("Enter account holder name: ");
                    String name = sc.nextLine();
                
                    System.out.print("Enter initial balance: ");
                    double bal = sc.nextDouble();

                    if(type==1)
                    {
                        acc = new BankAccount(num,name,bal);
                    }
                    else
                    {
                        acc = new SavingsAccount(num,name,bal,5.0);
                    }
                    System.out.println("Account made!");
                    System.out.println(acc.getAccountInfo());
                    break;

                case 2:
                    if(acc!=null)
                    {
                        System.out.print("Enter deposit amount: ");
                        double amt = sc.nextDouble();
                        acc.deposit(amt);
                    }
                    else
                    {
                        System.out.println("Make an account first!");
                    }
                    break;

                case 3:
                    if(acc!=null)
                    {
                        System.out.print("Enter amount to withdraw: ");
                        double amt = sc.nextDouble();
                        acc.withdraw(amt);
                    }
                    else
                    {
                        System.out.println("Make an account first!");
                    }
                    break;

                case 4:
                    if(acc!=null)
                    {
                        acc.displayBalance();
                    }
                    else
                    {
                        System.out.println("Make an account first!");
                    }
                    break;

                case 5:
                    System.out.println("Thanks for using the Bank Account System.");
                    break;

                default:
                    System.out.println("Wrong choice. Try again.");
                    break;
            }
        }
        sc.close();
    }
}