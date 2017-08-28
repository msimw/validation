package com.msimw.validation.expand;

import com.msimw.validation.api.Validation;
import com.msimw.validation.handler.ValidationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by msimw on 17-6-16.
 * 校验处理器扩增工具
 */
public class ValidationHandlerExpand {

    @Autowired
    private Validation validation;

    /**
     * 参数校验器
     */
    private List<ValidationHandler> validationHandlers;


    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;

    }

    public List<ValidationHandler> getValidationHandlers() {
        return validationHandlers;
    }

    public void setValidationHandlers(List<ValidationHandler> validationHandlers) {
        this.validationHandlers = validationHandlers;
        if (CollectionUtils.isEmpty(validationHandlers)) {
            return;
        }
        validation.addValidationHandlers(validationHandlers);
    }
}
