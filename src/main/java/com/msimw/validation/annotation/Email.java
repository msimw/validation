package com.msimw.validation.annotation;

import java.lang.annotation.*;

/**
 * Created by msimw on 17-8-8.
 *
 * 邮件校验
 */
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Email {

    String fieldName() default "";

    String messageCode() default "";//系统消息码

    String message() default "邮件格式不正确";//

    Class<?>[] groups() default {};//分组
}
