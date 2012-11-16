package cn.itcast.jpa;

import java.lang.reflect.Method;

/**
 * @author: weijiancai
 * @date: 2011-05-29 09:19
 */
public class Parser {
    private void print(Object obj, Method m) {
        String value = null;
        if (m.isAnnotationPresent(HelloWorld.class)) {
            HelloWorld hw = m.getAnnotation(HelloWorld.class);
            value = hw.name();
            //System.out.println(hw.name());
        } else if (m.isAnnotationPresent(HelloWorld1.class)) {
            HelloWorld1 hw1 = m.getAnnotation(HelloWorld1.class);
            value = hw1.value();
            //System.out.println(hw1.value());
        }

        try {
            System.out.println(value + " before...");
            m.invoke(obj);
            System.out.println(value + " after...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(Object obj, String methodName) {
        Method[] ms = obj.getClass().getMethods();
        for (Method m : ms) {
            if (m.getName().equals(methodName)) {
                print(obj, m);
            }
        }
    }
}
