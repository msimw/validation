package com.msimw.validation.annotation;

import java.lang.annotation.*;

/**
 * Created by msimw on 17-6-15.
 * 长度控制
 */
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Length {
    String fieldName() default "";

    int min() default 0;//最小长度

    int max() default Integer.MAX_VALUE;//最大

    String messageCode() default "";//系统消息码

    String message() default "参数长度不合法";//

    Class<?>[] groups() default {};//分组
}
