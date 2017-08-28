package com.msimw.validation.handler;

import com.msimw.validation.annotation.NotEmpty;
import com.msimw.validation.result.ValidationResult;
import com.msimw.validation.util.ValidationResultUtils;
import org.springframework.util.StringUtils;

/**
 * Created by msimw on 17-6-15.
 * 判空校验器
 */
public class NotEmptyHandler implements ValidationHandler<NotEmpty> {


    @Override
    public ValidationResult handler(NotEmpty annotation, String fieldName, Object param) {
        if (StringUtils.isEmpty(param)) {
            return ValidationResultUtils.buildValidationResultEntity(annotation.messageCode(), annotation.message(), fieldName);
        }
        return null;
    }
}
