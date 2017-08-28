package com.msimw.validation.handler;

import com.msimw.validation.result.ValidationResult;
import com.msimw.validation.util.ValidationResultUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created by huming on 17-6-19.
 * <p>
 * 校验处理器超级接口
 */
public abstract class AbstractValidationHandler<T extends Annotation> implements ValidationHandler<T> {

    @Override
    public ValidationResult handler(T annotation, String fieldName, Object param) {
        if (handler(annotation, param)) {
            return null;
        }
        /**
         * 获取注解上的参数
         */
        Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(annotation);
        String messageCode = String.valueOf(attributes.get("messageCode"));
        String message = String.valueOf(attributes.get("message"));
        return ValidationResultUtils.buildValidationResultEntity(messageCode, message, fieldName);
    }


    /**
     * 校验　处理
     *
     * @param annotation
     * @param param
     * @return
     */
    protected abstract boolean handler(T annotation, Object param);
}
