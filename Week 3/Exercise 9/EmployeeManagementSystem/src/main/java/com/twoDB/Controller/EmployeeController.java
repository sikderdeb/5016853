package com.twoDB.Controller;

import com.twoDB.entity.Employee;
import com.twoDB.repository.primary.EmployeeRepository;
import com.twoDB.projection.EmployeeProjection;
import com.twoDB.projection.EmployeeProjectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping
    public Page<Employee> getAllEmployees(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sortby", defaultValue = "id") String sortby) {
        List<String> validSortFields = Arrays.asList("id", "name", "email");
        if (!validSortFields.contains(sortby)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortby);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortby).ascending());
        return employeeRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeRepository.findEmployeeById(id);
    }

    @GetMapping("/projections")
    public List<EmployeeProjection> getEmployeeProjections() {
        return employeeRepository.findEmployeeProjections();
    }

    @GetMapping("/projection-dtos")
    public List<EmployeeProjectionDto> getEmployeeProjectionDtos() {
        return employeeRepository.findEmployeeProjectionDtos();
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee employeeToUpdate = employeeRepository.findEmployeeById(id);
        if (employeeToUpdate!=null) {
            employeeToUpdate.setName(employee.getName());
            employeeToUpdate.setEmail(employee.getEmail());
            employeeToUpdate.setDepartment(employee.getDepartment());
            return employeeRepository.save(employeeToUpdate);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeRepository.deletebyEmployeeId(id);
    }
}
