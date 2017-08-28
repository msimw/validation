package com.msimw.validation.annotation;

import java.lang.annotation.*;

/**
 * 参数校验 标记
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Validated {
    Class<?>[] value() default {};
}