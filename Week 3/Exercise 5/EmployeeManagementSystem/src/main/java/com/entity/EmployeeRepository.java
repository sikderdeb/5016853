package com.entity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByName(String name);
    @Query(name="findAllEmployee")
    List<Employee> findAllEmployee();

    @Query(name = "findEmployeeById")
    Employee findEmployeeById(@Param("n") int id);

    @Modifying
    @Transactional
    @Query(name = "deletebyEmployeeId")
    void deletebyEmployeeId(@Param("n") int id);
}
