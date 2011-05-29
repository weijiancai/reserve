package cn.itcast.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author weijiancai
 * @date 2011-05-29 11:11
 */
@Entity
@DiscriminatorValue("3")
public class Skiller extends Employee{
    private String skill;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
