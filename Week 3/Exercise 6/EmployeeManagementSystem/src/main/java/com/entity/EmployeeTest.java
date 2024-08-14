package com.entity;

import com.entity.controller.DepartmentController;
import com.entity.controller.EmployeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EmployeeTest {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmployeeTest.class, args);
        EmployeeController employeeController = context.getBean(EmployeeController.class);
        DepartmentController departmentController = context.getBean(DepartmentController.class);
    }
}
