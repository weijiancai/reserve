package cn.itcast.jpa.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author weijiancai
 * @date 2011-05-29 10:55
 */
@Entity
public class Department {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "department", targetEntity = Employee.class)
    private Set<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
