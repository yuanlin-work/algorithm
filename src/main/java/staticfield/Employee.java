package staticfield;

public class Employee {
    public static int empCount = 0;

    public Employee(){
        Employee.empCount++;
    }

    private void private_method(){
        System.out.println("private_method");
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        System.out.println(Employee.empCount);
        Employee employee2 = new Employee();
        System.out.println(Employee.empCount);

        employee.private_method();
    }
}
