package com.hu.zhcc.shiro.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.common.utils.CaptchaUtil;
import com.hu.zhcc.common.utils.ShiroUtils;
import com.hu.zhcc.shiro.manager.TokenManager;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 用户controller
 * Created by hulichao on 2018/6/20
 */
@Controller
@RequestMapping("/sys")
public class SysLoginController {

    @Autowired
    private Producer producer;

    @Autowired
    private TokenManager tokenManager;

    /**
     * 验证码
     *
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/code.jpg")
    public void checkCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        int width = NumberUtils.toInt(request.getParameter("width"), 100);
        int height = NumberUtils.toInt(request.getParameter("height"), 30);
        int codeCount = NumberUtils.toInt(request.getParameter("codeCount"), 4);
        int lineCount = NumberUtils.toInt(request.getParameter("lineCount"), 10);
        if (width > 1000) {
            width = 100;
        }
        if (height > 300) {
            height = 30;
        }
        if (codeCount > 10) {
            codeCount = 4;
        }
        if (lineCount > 100) {
            lineCount = 10;
        }
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 自定义参数
        CaptchaUtil code = new CaptchaUtil(width, height, codeCount, lineCount);
        String sessionId = session.getId();
//		RedisUtil.set("captcha_" + sessionId, code.getCode(), 60 * 30);
        code.write(response.getOutputStream());
    }

    /**
     * 验证码
     *
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(String username, String password, String captcha) throws IOException {
//        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//        if (!captcha.equalsIgnoreCase(kaptcha)) {
//            return Result.fail("验证码不正确");
//        }

        try {
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
//            password = MD5Utils.encrypt(username, password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Result.fail(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return Result.fail(e.getMessage());
        } catch (LockedAccountException e) {
            return Result.fail(e.getMessage());
        } catch (AuthenticationException e) {
            return Result.fail("账户验证失败");
        }

//        return Result.success();
        return Result.success(tokenManager.createToken(username));
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }

}
