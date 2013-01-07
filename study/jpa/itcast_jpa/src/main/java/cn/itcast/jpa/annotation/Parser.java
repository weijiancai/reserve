package cn.itcast.jpa.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class Parser {
    public void parse(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(HelloWord.class)) {
                HelloWord hw = method.getAnnotation(HelloWord.class);
                System.out.println(hw.value());
            }
        }
    }

    public void parse(Object obj, String methodName) {
        Method[] methods = obj.getClass().getMethods();

        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                if (method.isAnnotationPresent(HelloWord.class)) {
                    HelloWord hw = method.getAnnotation(HelloWord.class);
                    System.out.println(hw.value());

                    try {
                        System.out.println(hw.value() + " before...");
                        method.invoke(obj);
                        System.out.println(hw.value() + " after...");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
