package cn.itcast.jpa.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
public class Teacher {
    private Integer id;
    private String name;

    private Set<Student> studuents = new HashSet<Student>();

    public Teacher() {}

    public Teacher(String name) {
        this.name = name;
    }

    @Id @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 10, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = {CascadeType.REFRESH}, mappedBy = "teachers")
    public Set<Student> getStuduents() {
        return studuents;
    }

    public void setStuduents(Set<Student> studuents) {
        this.studuents = studuents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        return !(id != null ? !id.equals(teacher.id) : teacher.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
