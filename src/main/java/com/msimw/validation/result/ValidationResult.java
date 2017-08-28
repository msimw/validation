package com.msimw.validation.result;

import java.io.Serializable;

/**
 * Created by msimw on 17-8-28.
 *
 * 校验结果
 */
public class ValidationResult implements Serializable {
    /**
     * 校验不通过字段
     */
    private String filedName;

    /**
     * 校验码
     */
    private String code;

    /**
     * 消息
     */
    private String message;

    public ValidationResult() {
    }

    public ValidationResult(String filedName, String code, String message) {
        this.filedName = filedName;
        this.code = code;
        this.message = message;
    }


    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
