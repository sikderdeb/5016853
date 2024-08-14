package com.entity;

import com.entity.projection.EmployeeProjection;
import com.entity.projection.EmployeeProjectionDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Employee> findAll(Pageable pageable);

    @Query("select e.id as id, e.name as name, e.email as email from Employee e")
    List<EmployeeProjection> findEmployeeProjections();

    @Query("select new com.entity.projection.EmployeeProjectionDto(e.id, e.name, e.email) from Employee e")
    List<EmployeeProjectionDto> findEmployeeProjectionDtos();
}
