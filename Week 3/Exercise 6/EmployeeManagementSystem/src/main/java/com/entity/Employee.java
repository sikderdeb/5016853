package com.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Employee")
@NamedQueries(
        value = {
                @NamedQuery(
                        name = "findAllEmployee",
                        query = "select e from Employee e "
                ),
                @NamedQuery(
                        name = "findEmployeeById",
                        query = "select e from Employee e Where e.id = :n"
                ),
                @NamedQuery(
                        name = "deletebyEmployeeId",
                        query = "delete from Employee where id= :n"
                )
        }
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dept_Id")
    @JsonBackReference
    private Department department;

    public Employee(){

    }

    public Employee(String name, String email, Department department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }
}
