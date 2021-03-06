package Hibernate.onetomany.dto;

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

    @OneToMany(fetch = FetchType.EAGER)
    //@JoinColumn
    /*
    Без этой аннотации связывание сущностей будет происходить с помощью третьей таблицы.
    С этой аннотацияей связывание будет происходить на базе сушности Employer
    посредством дополнительной колонки department_id
    В остальном код такой же поэтому отдельную папку под него я не делала
     */
    private List<Employer> employers;

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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employers=" + employers +
                '}';
    }
}
