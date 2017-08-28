package com.msimw.validation.util;



import com.msimw.validation.result.ValidationResult;
import org.springframework.util.StringUtils;


/**
 * Created by huming on 12:58 2017/3/22.
 *
 * @Author: huming
 * @Description:校验工具类
 * @Modified By:
 */

public abstract class ValidationResultUtils {

    /**
     * 生成一个校验结果
     *
     * @param code
     * @param message
     * @param fieldName
     * @return
     */
    public static ValidationResult buildValidationResultEntity(String code, String message, String fieldName) {
        ValidationResult validationResult = new ValidationResult(fieldName,code,message);

        if (!StringUtils.isEmpty(code)) {
            validationResult.setMessage(ResourcesUtil.getValue("validation.validation", code));
        }
        return validationResult;
    }

}
