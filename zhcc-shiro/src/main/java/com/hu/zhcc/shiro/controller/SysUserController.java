package com.hu.zhcc.shiro.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.hu.zhcc.common.constant.SystemConstant;
import com.hu.zhcc.common.controller.AbstractController;
import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.common.entity.SysUserEntity;
import com.hu.zhcc.common.validator.LengthValidator;
import com.hu.zhcc.common.validator.NotNullValidator;
import com.hu.zhcc.shiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 系统用户
 * <p>
 * Created by hulichao on 2018/6/20
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public Page<SysUserEntity> list(@RequestBody Map<String, Object> params) {
        if (getUserId() != SystemConstant.SUPER_ADMIN) {
            params.put("userIdCreate", getUserId());
        }
        return sysUserService.listUser(params);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public Result info() {
        return Result.success().put("user", getUser());
    }

    /**
     * 用户权限
     *
     * @return
     */
    @RequestMapping("/perms")
    public Result listUserPerms() {
        return sysUserService.listUserPerms(getUserId());
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SysUserEntity user) {
        ComplexResult result = FluentValidator.checkAll()
                .on(user.getUsername(), new NotNullValidator("用户名"))
                .on(user.getPassword(), new LengthValidator(1, 5, "密码"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return Result.fail(result.getErrors());
        }
        user.setUserIdCreate(getUserId());
        return sysUserService.saveUser(user);
    }

    /**
     * 根据id查询详情
     *
     * @param userId
     * @return
     */
    @RequestMapping("/infoUser")
    public Result getById(@RequestBody Long userId) {
        return sysUserService.getUserById(userId);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysUserEntity user) {
        return sysUserService.updateUser(user);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    public Result batchRemove(@RequestBody Long[] id) {
        return sysUserService.batchRemove(id);
    }

    /**
     * 用户修改密码
     *
     * @param pswd
     * @param newPswd
     * @return
     */
    @RequestMapping("/updatePswd")
    public Result updatePswdByUser(String pswd, String newPswd) {
        SysUserEntity user = getUser();
        user.setPassword(pswd);//原密码
        user.setEmail(newPswd);//邮箱临时存储新密码
        return sysUserService.updatePswdByUser(user);
    }

    /**
     * 启用账户
     *
     * @param id
     * @return
     */
    @RequestMapping("/enable")
    public Result updateUserEnable(@RequestBody Long[] id) {
        return sysUserService.updateUserEnable(id);
    }

    /**
     * 禁用账户
     *
     * @param id
     * @return
     */
    @RequestMapping("/disable")
    public Result updateUserDisable(@RequestBody Long[] id) {
        return sysUserService.updateUserDisable(id);
    }

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    @RequestMapping("/reset")
    public Result updatePswd(@RequestBody SysUserEntity user) {
        return sysUserService.updatePswd(user);
    }
}
