package com.entity.controller;

import com.entity.Department;
import com.entity.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Department> getDepartmentsById(@PathVariable Integer id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department;
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Integer id, @RequestBody Department department) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isPresent()) {
            Department updatedDepartment = optional.get();
            updatedDepartment.setName(department.getName());
            return departmentRepository.save(updatedDepartment);
        }
        else
            return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        departmentRepository.deleteById(id);
    }
}
