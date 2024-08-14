package com.twoDB.repository.secondary;

import com.twoDB.entity.Department;
import com.twoDB.projection.DepartmentProjection;
import com.twoDB.projection.DepartmentProjectionDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByName(String name);

    @Query(name="findAllDepartment")
    List<Department> findAllDepartment();

    @Query(name = "findDepartmentById")
    Department findDepartmentById(@Param("n") int id);

    @Modifying
    @Transactional
    @Query(name = "deletebyDepartmentId")
    void deletebyDepartmentId(@Param("n") int id);

    @Query("select d.id as id, d.name as name from Department d")
    List<DepartmentProjection> findDepartmentProjections();

    @Query("select new com.twoDB.projection.DepartmentProjectionDto(d.id, d.name) from Department d")
    List<DepartmentProjectionDto> findDepartmentProjectionDtos();
}
