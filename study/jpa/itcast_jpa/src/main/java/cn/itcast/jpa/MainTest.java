package cn.itcast.jpa;

/**
 * @author: weijiancai
 * @date: 2011-05-29 09:15
 */
public class MainTest {
    public static void main(String[] args) {
        TestBean tb = new TestBean("abcd");
        //System.out.println(tb);

        Parser p = new Parser();
        p.parse(tb, "toString");
    }
}
