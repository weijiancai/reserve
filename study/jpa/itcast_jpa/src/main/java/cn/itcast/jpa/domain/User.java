package cn.itcast.jpa.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.Date;

/**
 * @author weijiancai
 * @date   2011-05-29 10:16
 */
@Entity
@Table(name = "user")
@GenericGenerator(name = "uuidpk", strategy = "uuid")
public class User {
    @Id
    @GeneratedValue(generator = "uuidpk")
    @Column(length = 32)
    private String id;

    @Index(name = "name_index")
    @Column(nullable = true, name = "user_name", unique = true)
    private String name;
    private Date birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
