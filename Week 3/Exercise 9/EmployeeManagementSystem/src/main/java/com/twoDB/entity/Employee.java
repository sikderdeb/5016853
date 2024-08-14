package com.twoDB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.twoDB.Audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
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
public class Employee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dept_Id")
    @JsonBackReference
    private Department department;
    public Employee() {

    }
    public Employee(int id, String name, String email, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }
}
