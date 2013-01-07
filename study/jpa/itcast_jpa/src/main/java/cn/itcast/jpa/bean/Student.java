package cn.itcast.jpa.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
public class Student {
    private Integer id;
    private String name;

    private Set<Teacher> teachers = new HashSet<Teacher>();

    public Student() {}

    public Student(String name) {
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

    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "student_teacher", inverseJoinColumns = @JoinColumn(name = "teacher_id"),
            joinColumns = @JoinColumn(name = "student_id"))
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        if (this.teachers.contains(teacher)) {
            this.teachers.remove(teacher);
        }
    }
}
