package cn.itcast.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author weijiancai
 * @date 2011-05-29 11:09
 */
@Entity
@DiscriminatorValue("2")
public class Sales extends Employee {
    private int sell;

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }
}
