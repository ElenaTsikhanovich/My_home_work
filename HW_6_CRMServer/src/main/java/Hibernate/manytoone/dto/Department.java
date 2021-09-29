package Hibernate.manytoone.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Employer> employers;
    //у одного департемента много сотрудников
    //fetch это тип получения данных
    // EAGER - сможем получить getEmployers();
    // если LAZY то не сможем
    //mappedBY указывает на владельца связи

    public Department(){

    }

    public Department(String name, List<Employer> employers) {
        this.name = name;
        this.employers = employers;
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

    public List<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }


}
