package com.msimw.validation.annotation;

import java.lang.annotation.*;

/**
 * Created by msimw on 17-8-8.
 * 数字
 */
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Number {

    String fieldName() default "";

    String messageCode() default "";//系统消息码

    String message() default "必须是阿拉伯数字";//

    Class<?>[] groups() default {};//分组

}
