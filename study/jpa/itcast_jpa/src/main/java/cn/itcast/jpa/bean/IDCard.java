package cn.itcast.jpa.bean;

import javax.jws.WebService;
import javax.persistence.*;

/**
 * @author weijiancai
 * @version 1.0.0
 */
@Entity
@WebService
public class IDCard {
    private Integer id;
    private String cardNo;
    private Person person;

    public IDCard(String cardNo) {
        this.cardNo = cardNo;
    }

    public IDCard() {
    }

    @Id @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 18, nullable = false)
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @OneToOne(mappedBy = "idCard", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
