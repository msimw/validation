package com.msimw.validation.annotation;

import java.lang.annotation.*;

/**
 * Created by msimw on 17-8-8.
 *
 * 中国身份证
 */
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ChineseIdCard {

    String fieldName() default "";

    String messageCode() default "";//系统消息码

    String message() default "必须是中华人民共和国身份证号码";//

    Class<?>[] groups() default {};//分组
}
