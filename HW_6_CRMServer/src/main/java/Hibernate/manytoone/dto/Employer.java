package Hibernate.manytoone.dto;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne
    @JoinColumn
    private Department department;
    //Много сотрудников в одном департаменте
    //присоединяем колонку с айди департамента

    public Employer(){

    }

    public Employer(String name, Double salary, Department department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


}
