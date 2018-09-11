package com.hu.zhcc.shiro.security;

import com.hu.zhcc.common.constant.AuthorizationConstants;
import com.hu.zhcc.common.utils.WebUtils;
import com.hu.zhcc.shiro.annotation.IgnoreSecurity;
import com.hu.zhcc.shiro.exception.TokenException;
import com.hu.zhcc.shiro.manager.TokenManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 安全检查切面，负责token的登录和维持
 * Created by hulichao on 2018/7/9.
 */
@Component
@Aspect
public class SecurityAspect {
    private static final Logger log = LoggerFactory.getLogger(SecurityAspect.class);

    private TokenManager tokenManager;

    @Resource(name = "tokenManager")
    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        log.debug("methodSignature : " + methodSignature);
        Method method = methodSignature.getMethod();
        log.debug("Method : " + method.getName() + " : "
                + method.isAnnotationPresent(IgnoreSecurity.class));
        // 若目标方法忽略了安全性检查,则直接调用目标方法
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return pjp.proceed();
        }

        // 从 request header 中获取当前 token
        String token = WebUtils.getRequest().getHeader(
                AuthorizationConstants.DEFAULT_TOKEN_NAME);
        System.out.println("----执行了后台生成的token");
        // 检查 token 有效性
        if (!tokenManager.checkToken(token)) {
            String message = String.format("token [%s] is invalid", token);
            log.debug("message : " + message);
            throw new TokenException(message);
        }
        // 调用目标方法
        return pjp.proceed();
    }
}
