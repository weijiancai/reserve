package cn.itcast.jpa.annotation;

/**
 * @author weijiancai
 * @version 0.0.1
 */
@HelloWord
public class TestBean {
    @HelloWord("你好")
    private String name;

    public TestBean(String name) {
        this.name = name;
    }

    @Override @HelloWord
    public String toString() {
        System.out.println(this.name);
        final StringBuilder sb = new StringBuilder();
        sb.append("TestBean");
        sb.append("{name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
