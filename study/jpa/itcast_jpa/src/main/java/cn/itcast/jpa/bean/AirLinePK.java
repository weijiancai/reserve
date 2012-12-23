package cn.itcast.jpa.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 航线主键类
 *
 * @author weijiancai
 * @version 0.0.1
 */
@Embeddable
public class AirLinePK implements Serializable {
    private String startCity;
    private String endCity;

    public AirLinePK() {}

    public AirLinePK(String startCity, String endCity) {
        this.startCity = startCity;
        this.endCity = endCity;
    }

    @Column(length = 3)
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    @Column(length = 3)
    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirLinePK airLinePK = (AirLinePK) o;

        return !(endCity != null ? !endCity.equals(airLinePK.endCity) : airLinePK.endCity != null) &&
                !(startCity != null ? !startCity.equals(airLinePK.startCity) : airLinePK.startCity != null);
    }

    @Override
    public int hashCode() {
        int result = startCity != null ? startCity.hashCode() : 0;
        result = 31 * result + (endCity != null ? endCity.hashCode() : 0);
        return result;
    }
}
