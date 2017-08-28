package com.msimw.validation.handler;

import com.msimw.validation.annotation.Length;
import com.msimw.validation.result.ValidationResult;
import com.msimw.validation.util.ValidationResultUtils;

/**
 * Created by msimw on 17-6-15.
 * 长度校验器
 */
public class LengthHandler implements ValidationHandler<Length> {


    @Override
    public ValidationResult handler(Length annotation, String fieldName, Object param) {
        String paramStr = null;
        if (param == null) {
            paramStr = "";
        }
        int min = annotation.min();
        int max = annotation.max();

        if (paramStr.length() < min || paramStr.length() > max) {
            return ValidationResultUtils.buildValidationResultEntity(annotation.messageCode(), annotation.message(), fieldName);
        }

        return null;
    }
}
