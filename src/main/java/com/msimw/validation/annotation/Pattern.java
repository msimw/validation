package com.msimw.validation.annotation;

/**
 * Created by msimw on 16-6-17.
 */

import java.lang.annotation.*;

@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Pattern {
    String fieldName() default "";

    String value() default ""; //正则

    String messageCode() default "";//系统消息码

    String message() default "参数格式校验不通过";

    boolean required() default true;//是否必须

    Class<?>[] groups() default {};//分组
}
