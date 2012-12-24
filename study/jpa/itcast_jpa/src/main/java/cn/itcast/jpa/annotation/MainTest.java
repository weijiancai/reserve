package cn.itcast.jpa.annotation;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class MainTest {
    public static void main(String[] args) {
        TestBean tb = new TestBean("abcd");
        System.out.println(tb);

        Parser p = new Parser();
        p.parse(tb, "toString");
    }
}
