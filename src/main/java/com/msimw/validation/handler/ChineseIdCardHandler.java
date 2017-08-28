package com.msimw.validation.handler;

import com.msimw.validation.annotation.ChineseIdCard;

/**
 * Created by msimw on 17-8-8.
 *
 * 中华人民共和国身份证校验
 */
public class ChineseIdCardHandler extends AbstractValidationHandler<ChineseIdCard> {

    @Override
    protected boolean handler(ChineseIdCard annotation, Object param) {
        if(param==null){
          return true;
        }
        return String.valueOf(param).matches("(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)||(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$)");
    }
}
