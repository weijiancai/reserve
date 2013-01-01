package cn.itcast.jpa.tree;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weijiancai
 * @version 0.0.1
 */
@Entity
@Table(name = "t_organization")
public class Organization {
    @Id
    @GeneratedValue
    private Long id;

    /**组织名称*/
    @Column(length=64)
    private String name;

    /**组织编码*/
    @Column(length=64)
    private String code;

    /**父组织*/
    @ManyToOne
    @JoinColumn(name="parent_id")
    private Organization parent;

    /**子组织*/
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Set<Organization> children = new HashSet<Organization>();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    public Set<Organization> getChildren() {
        return children;
    }

    public void setChildren(Set<Organization> children) {
        this.children = children;
    }
}
