package com.twoDB;

import com.twoDB.Controller.DepartmentController;
import com.twoDB.Controller.EmployeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class EmployeeApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmployeeApplication.class, args);
        EmployeeController employeeController = context.getBean(EmployeeController.class);
        DepartmentController departmentController = context.getBean(DepartmentController.class);

    }
}
