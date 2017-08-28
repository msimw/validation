package com.msimw.validation.handler;

import com.msimw.validation.annotation.Pattern;
import com.msimw.validation.result.ValidationResult;
import com.msimw.validation.util.ValidationResultUtils;
import com.msimw.validation.util.clasz.ClassUtil;
import org.springframework.util.StringUtils;


/**
 * Created by 胡明 on 17-6-15.
 * 正则校验器
 */
public class PatternHandler implements ValidationHandler<Pattern> {

    /**
     * @param pattern
     * @param obj
     * @return
     */
    private static boolean access(Pattern pattern, Object obj) {
        if (StringUtils.isEmpty(obj)) {
            return !pattern.required();
        }
        if (StringUtils.isEmpty(pattern.value())) {
            return true;
        }
        if (!ClassUtil.isWrapClassAndString(obj.getClass())) {
            return true;
        }
        String str = obj.toString();
        return str.matches(pattern.value());
    }

    @Override
    public ValidationResult handler(Pattern annotation, String fieldName, Object param) {
        if (access(annotation, param)) {
            return null;
        }
        return ValidationResultUtils.buildValidationResultEntity(annotation.messageCode(), annotation.message(), fieldName);
    }
}



