package com.msimw.validation.exception;


import com.msimw.validation.result.ValidationResult;

/**
 * Created by msimw on 16-8-16.
 *
 * 教研异常
 */
public class ValidationException extends RuntimeException {

    protected ValidationResult validationResult;

    public ValidationException(ValidationResult validationResult) {
        super();
        this.validationResult = validationResult;
    }
}
