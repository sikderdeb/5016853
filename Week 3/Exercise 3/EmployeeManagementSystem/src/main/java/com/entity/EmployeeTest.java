package com.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EmployeeTest {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmployeeTest.class, args);

        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        DepartmentRepository departmentRepository = context.getBean(DepartmentRepository.class);

        //Department
        Department department = new Department();
        department.setName("IT");
        departmentRepository.save(department);

        Department department1 = new Department();
        department1.setName("Finance");
        departmentRepository.save(department1);

        //Employee
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setEmail("john.doe@gmail.com");
        Department foundDepartment = departmentRepository.findByName("IT");
        employee.setDepartment(foundDepartment);
        employeeRepository.save(employee);

        Employee employee1 = new Employee();
        employee1.setName("Mary Smith");
        employee1.setEmail("mary.smith@gmail.com");
        foundDepartment=departmentRepository.findByName("Finance");
        employee1.setDepartment(foundDepartment);
        employeeRepository.save(employee1);


        List<Employee> findEmployees = employeeRepository.findByName("Mary Smith");
        for (Employee emp : findEmployees) {
            String name = emp.getName();
            System.out.println("Employee: " + name);
            System.out.println("Email: " + emp.getEmail());
            System.out.println("Department: " + emp.getDepartment().getName());
        }
    }
}
