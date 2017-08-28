package com.msimw.validation.annotation;

import java.lang.annotation.*;

/**
 * Created by msimw on 17-8-8.
 * 英文字符
 */
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EnglishCharacters {

    String fieldName() default "";

    String messageCode() default "";//系统消息码

    String message() default "必须是英文字符";//

    Class<?>[] groups() default {};//分组

}
