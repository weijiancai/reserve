package cn.itcast.jpa;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author: weijiancai
 * @date: 2011-05-29 09:08
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HelloWorld {
    public String name() default "hello";
}
