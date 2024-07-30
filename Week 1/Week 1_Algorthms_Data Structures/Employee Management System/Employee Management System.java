class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;  
    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", name=" + name + ", position=" + position + ", salary=" + salary + "]";
    }
}

class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;
    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }
    public void addEmployee(Employee employee) {
        if (size < employees.length) {
            employees[size] = employee;
            size++;
        } else {
            System.out.println("Employee array is full");
        }
    }
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }
    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }
    public void deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                employees[i] = employees[size - 1]; 
                employees[size - 1] = null;
                size--;
                return;
            }
        }
        System.out.println("Employee not found");
    }
}

class management {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);
        Employee e1 = new Employee(1, "Alice", "Developer", 75000);
        Employee e2 = new Employee(2, "Bob", "Designer", 65000);
        Employee e3 = new Employee(3, "Charlie", "Manager", 85000);
        ems.addEmployee(e1);
        ems.addEmployee(e2);
        ems.addEmployee(e3);
        System.out.println("Traverse all employees:");
        ems.traverseEmployees();
        System.out.println("Search for employee with ID 2:");
        Employee found = ems.searchEmployee(2);
        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Employee not found");
        }
        System.out.println("Delete employee with ID 2:");
        ems.deleteEmployee(2);
        ems.traverseEmployees();
    }
}
