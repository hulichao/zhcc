package com.hu.zhcc.shiro.security;

import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.common.utils.WebUtils;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * dao ajax 认证
 * <p>
 * Created by hulichao on 2018/6/20
 */
public class AjaxAuthenticationFilter extends AuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        if (WebUtils.isAjax(httpReq)) {
            System.err.println("ajax 登录验证");
            HttpServletResponse httpRes = (HttpServletResponse) response;
            Result r = Result.fail(401, "未登录");
            WebUtils.write(httpRes, r);
        }
        return false;
    }


}
