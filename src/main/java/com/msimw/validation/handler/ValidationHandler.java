package com.msimw.validation.handler;

import com.msimw.validation.result.ValidationResult;
import java.lang.annotation.Annotation;


/**
 * Created by 胡明 on 17-6-15.
 * <p>
 * 参数数据校验器,当ResultValidationEntity有返回时，表示校验不通过
 */
public interface ValidationHandler<T extends Annotation> {

    /**
     * 　处理
     *
     * @param param
     * @return
     */
    public ValidationResult handler(T annotation, String fieldName, Object param);
}
