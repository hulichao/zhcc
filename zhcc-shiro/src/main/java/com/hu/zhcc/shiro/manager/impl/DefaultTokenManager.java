package com.hu.zhcc.shiro.manager.impl;

import com.alibaba.druid.util.StringUtils;
import com.hu.zhcc.common.utils.MD5Utils;
import com.hu.zhcc.common.utils.ShiroUtils;
import com.hu.zhcc.shiro.manager.TokenManager;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hulichao on 2018/7/9.
 */
@Component("tokenManager")
public class DefaultTokenManager implements TokenManager {
    /** 将token存储到JVM内存(ConcurrentHashMap)中   (@author: rico) */
    private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

    /**
     * @description 用户登录时候创建token，创建token方式使用MD5Utils加密sessionId,当然也可以用随机数来代替，
     */
    public String createToken(String username) {

        String token = MD5Utils.encrypt(username + ShiroUtils.getSession().getId());
        tokenMap.put(token, username);
        return token;
    }


    /**
     * @description Token验证
     * (用户登录验证)
     */
    public boolean checkToken(String token) {
        return !StringUtils.isEmpty(token) && tokenMap.containsKey(token);
    }


    /**
     * @description Token删除
     * (用户登出时，删除Token)
     */
    @Override
    public void deleteToken(String token) {
        tokenMap.remove(token);
    }
}
