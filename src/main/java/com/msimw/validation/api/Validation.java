package com.msimw.validation.api;


import com.msimw.validation.handler.ValidationHandler;
import com.msimw.validation.result.ValidationResult;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by msimw on 17-3-27.
 * 参数校验核心接口
 */
public interface Validation {
    /**
     * 校验方法
     *
     * @param method
     * @param args
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    ValidationResult validated(Method method, Object... args) throws NoSuchFieldException, IllegalAccessException;

    /**
     * 设置校验器
     *
     * @param validationHandlers
     */
    public void setValidationHandlers(List<ValidationHandler> validationHandlers);

    /**
     * 新增加校验器
     *
     * @param validationHandlers
     */
    public void addValidationHandlers(List<ValidationHandler> validationHandlers);
}
