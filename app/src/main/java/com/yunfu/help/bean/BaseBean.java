package com.yunfu.help.bean;

import java.io.Serializable;

/**
 * 网络请求状态实体类
 */

public class BaseBean implements Serializable {
    private String code;
    private String message;
    private String token;

    public BaseBean(){}

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSussess() {
        if (code.equals("SUCCESS")) {
            return true;
        }
        return false;
    }

}
