package com.hu.zhcc.shiro.exception;

/**
 * 自定义exception，在token过期或者不存在时候使用
 * Created by hulichao on 2018/7/9.
 */
public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;

    public TokenException(String msg) {
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
