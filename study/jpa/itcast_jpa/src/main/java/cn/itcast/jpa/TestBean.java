package cn.itcast.jpa;

/**
 * @author: weijiancai
 * @date: 2011-05-29 09:02
 */
@HelloWorld
public class TestBean {
    @HelloWorld(name = "���")
    private String name;

    public TestBean(String name) {
        this.name = name;
    }

    @Override
    @HelloWorld1("����")
    public String toString() {
        System.out.println(name);
        return this.name;
    }
}
