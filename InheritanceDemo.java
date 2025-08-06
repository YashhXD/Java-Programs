/**
 * Person Class (Base Class)
 * This is the most general class in the hierarchy.
 */
class Person {
    // Private fields for name and age
    private String name;
    private int age;

    /**
     * Constructor for the Person class.
     * @param name The person's name.
     * @param age The person's age.
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Displays the details of the person.
     */
    public void display() {
        System.out.println("--- Person Details ---");
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("----------------------");
    }
}

/**
 * Employee Class (Subclass of Person)
 * Inherits from Person and adds a salary field.
 */
class Employee extends Person {
    // Additional private field for salary
    private double salary;

    /**
     * Constructor for the Employee class.
     * @param name The employee's name.
     * @param age The employee's age.
     * @param salary The employee's salary.
     */
    public Employee(String name, int age, double salary) {
        // Call the constructor of the parent class (Person)
        super(name, age);
        this.salary = salary;
    }

    /**
     * Overrides the display method to include salary.
     */
    @Override
    public void display() {
        System.out.println("--- Employee Details ---");
        // Call the parent's display method to avoid repeating code,
        // but for this example, we'll print everything directly.
        super.display(); // This is a good practice to reuse parent's method
        // To access parent's private fields, we would need getter methods.
        // For simplicity here, let's assume we are re-writing the display logic.
        // A better design would be:
        // super.display();
        // System.out.println("Salary: Rs " + this.salary);
        
        // Let's rewrite it fully to show the concept clearly
        // Note: To access 'name' and 'age' directly, they would need to be 'protected' in the Person class.
        // Since they are private, we'll demonstrate by creating a new display method.
        // A better approach is shown in the Manager class.
        System.out.println("Salary: Rs " + String.format("%.2f", this.salary));
        System.out.println("------------------------");
    }
}

/**
 * Manager Class (Subclass of Employee)
 * Inherits from Employee and adds a department field.
 */
class Manager extends Employee {
    // Additional private field for department
    private String department;

    /**
     * Constructor for the Manager class.
     * @param name The manager's name.
     * @param age The manager's age.
     * @param salary The manager's salary.
     * @param department The department the manager oversees.
     */
    public Manager(String name, int age, double salary, String department) {
        // Call the constructor of the parent class (Employee)
        super(name, age, salary);
        this.department = department;
    }

    /**
     * Overrides the display method to include all details.
     */
    @Override
    public void display() {
        System.out.println("--- Manager Details ---");
        // This is the best practice: call the parent's display method
        // to print the details it knows about, then add your own.
        super.display(); 
        System.out.println("Department: " + this.department);
        System.out.println("-----------------------");
    }
}

/**
 * Main Program to demonstrate creating objects of each class.
 */
public class InheritanceDemo {
    public static void main(String[] args) {
        // 1. Create a Person object
        Person person = new Person("Arjun Kumar", 30);
        System.out.println("Creating a Person object:");
        person.display();

        // 2. Create an Employee object
        Employee employee = new Employee("Priya Singh", 28, 60000.00);
        System.out.println("\nCreating an Employee object:");
        employee.display();

        // 3. Create a Manager object
        Manager manager = new Manager("Rohan Mehta", 45, 120000.00, "Technology");
        System.out.println("\nCreating a Manager object:");
        manager.display();
    }
}
