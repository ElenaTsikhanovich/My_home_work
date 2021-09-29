package Hibernate.manytomany.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "employer")
public class Employer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Double salary;

    @ManyToMany(mappedBy = "employers", fetch = FetchType.EAGER)
    private List<Department> departments;

    public Employer(){

    }

    public Employer(String name, Double salary, List<Department> departments) {
        this.name = name;
        this.salary = salary;
        this.departments = departments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
