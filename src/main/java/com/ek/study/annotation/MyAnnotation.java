package com.ek.study.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/5
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String value() default "";
}
