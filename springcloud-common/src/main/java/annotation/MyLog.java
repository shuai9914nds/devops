package annotation;

import java.lang.annotation.*;

/**
 * @author: liushuai
 * @date: 2020/12/16
 * @description：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    String value() default "";
}
