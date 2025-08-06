import java.util.Scanner;

class employee{
    private String name;
    private int id;
    private float salary;

    public employee(String name , int id , float salary){
        this.name=name;
        this.id=id;
        this.salary=salary;
    }
    
    public double raiseSalary(double percent){
        double raisedAmount = this.salary*(percent/100)+this.salary;
        return raisedAmount;
    }

    public void display(){
        System.out.println("Employee Name : "+this.name);
        System.out.println("Employee ID : "+this.id);
        System.out.println("Salary : "+"Rs "+salary);
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name : ");
        String inp_name = scanner.nextLine();
        System.out.println("Enter ID : ");
        int inp_id = scanner.nextInt();
        System.out.println("Enter salary : ");
        float inp_salary = scanner.nextFloat();
        System.out.println("Enter raise amount : ");
        double inp_amount = scanner.nextDouble();
        employee e1 = new employee(inp_name, inp_id, inp_salary);
        e1.display();

        double newSalary = e1.raiseSalary(inp_amount);
        System.out.println("After rasie : "+"Rs "+newSalary);
        scanner.close();
    }
}
