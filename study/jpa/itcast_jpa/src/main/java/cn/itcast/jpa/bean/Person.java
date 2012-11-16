package cn.itcast.jpa.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_person")
public class Person {
    private Integer id;
    private String name;
    private Date birthday;
    private Gender gender = Gender.MAN;
    private String info;
    private byte[] file;
    private String imagePath;

    private IDCard idCard;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    @Id @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 10, nullable = false, name = "personName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 5, nullable = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Lob
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Lob @Basic(fetch = FetchType.LAZY)
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Transient
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @OneToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "idCard_id")
    public IDCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IDCard idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Person");
        sb.append("{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", gender=").append(gender);
        sb.append('}');
        return sb.toString();
    }
}
