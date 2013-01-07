package cn.itcast.jpa.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author weijiancai
 * @version 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HelloWord {
    public String value() default "hello";
}
