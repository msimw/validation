package com.msimw.validation.handler;

import com.msimw.validation.annotation.ChineseCharacters;

/**
 * Created by msimw on 17-8-8.
 * 中国文字 校验器
 */
public class ChineseCharactersHandler extends AbstractValidationHandler<ChineseCharacters>{

    @Override
    protected boolean handler(ChineseCharacters annotation, Object param) {
        if(param==null){
        return true;
        }
        return String.valueOf(param).matches("^[\\u4e00-\\u9fa5]*$");
    }

}
