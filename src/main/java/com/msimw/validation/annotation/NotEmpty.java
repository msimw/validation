package com.msimw.validation.annotation;

import java.lang.annotation.*;

/**
 * Created by msimw on 17-6-15.
 */
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NotEmpty {

    String fieldName() default "";

    String messageCode() default "";//系统消息码

    String message() default "参数不能为空";//

    Class<?>[] groups() default {};//分组
}
