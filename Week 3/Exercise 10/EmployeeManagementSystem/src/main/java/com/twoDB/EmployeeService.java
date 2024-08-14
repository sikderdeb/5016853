package com.twoDB;

import com.twoDB.entity.Employee;
import com.twoDB.repository.primary.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveEmployees(List<Employee> employees) {
        final int batchSize = 50;
        int count = 0;

        for (int i = 0; i < employees.size(); i++) {
            entityManager.persist(employees.get(i));

            if (++count % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
