package com.msimw.validation.handler;

import com.msimw.validation.annotation.Number;
/**
 * Created by msimw on 17-8-8.
 * 数字 校验器
 */
public class NumberHandler extends AbstractValidationHandler<Number> {
    @Override
    protected boolean handler(Number annotation, Object param) {
        if(param==null){
            return true;
        }
        return String.valueOf(param).matches("^[0-9]*$");
    }
}
