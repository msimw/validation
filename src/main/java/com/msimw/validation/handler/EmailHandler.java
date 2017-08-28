package com.msimw.validation.handler;

import com.msimw.validation.annotation.Email;

/**
 * Created by msimw on 17-8-8.
 *
 * 邮件 校验器
 */
public class EmailHandler extends AbstractValidationHandler<Email>{
    @Override
    protected boolean handler(Email annotation, Object param) {
        if(param==null){
            return true;
        }
        return   String.valueOf(param).matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }

}
