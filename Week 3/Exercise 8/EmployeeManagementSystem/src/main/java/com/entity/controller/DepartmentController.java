package com.entity.controller;

import com.entity.Department;
import com.entity.DepartmentRepository;
import com.entity.projection.DepartmentProjection;
import com.entity.projection.DepartmentProjectionDto;
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
        return departmentRepository.findAllDepartment();
    }

    @GetMapping("/projections")
    public List<DepartmentProjection> getDepartmentProjections() {
        return departmentRepository.findDepartmentProjections();
    }

    @GetMapping("/projection-dtos")
    public List<DepartmentProjectionDto> getDepartmentProjectionDtos() {
        return departmentRepository.findDepartmentProjectionDtos();
    }

    @GetMapping("/{id}")
    public Department getDepartmentsById(@PathVariable Integer id) {
        return departmentRepository.findDepartmentById(id);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Integer id, @RequestBody Department department) {
        Department updatedDepartment = departmentRepository.findDepartmentById(id);
        if (updatedDepartment!=null) {
            updatedDepartment.setName(department.getName());
            return departmentRepository.save(updatedDepartment);
        }
        else
            return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        departmentRepository.deletebyDepartmentId(id);
    }
}
