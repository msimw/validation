package com.msimw.validation.handler;

import com.msimw.validation.annotation.EnglishCharacters;

/**
 * Created by msimw on 17-8-8.
 */
public class EnglishCharactersHandler extends AbstractValidationHandler<EnglishCharacters>{

    @Override
    protected boolean handler(EnglishCharacters annotation, Object param) {
        if(param==null){
            return true;
        }
        return String.valueOf(param).matches("^[a-zA-Z]*$");
    }
}
