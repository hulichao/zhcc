package com.hu.zhcc.shiro.manager;

/**
 * 登录用户的身份授权，防止未经登录可访问任意系统内任意api
 * Created by hulichao on 2018/7/9.
 */
public interface TokenManager {
    String createToken(String username);

    boolean checkToken(String token);

    void deleteToken(String token);
}
