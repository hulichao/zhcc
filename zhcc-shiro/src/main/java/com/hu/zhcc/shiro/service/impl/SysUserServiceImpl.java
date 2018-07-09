package com.hu.zhcc.shiro.service.impl;

import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.common.entity.SysUserEntity;
import com.hu.zhcc.common.utils.CommonUtils;
import com.hu.zhcc.common.utils.MD5Utils;
import com.hu.zhcc.shiro.manager.SysUserManager;
import com.hu.zhcc.shiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * 系统用户
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserManager sysUserManager;

    @Override
    public Page<SysUserEntity> listUser(Map<String, Object> params) {
        Query form = new Query(params);
        Page<SysUserEntity> page = new Page<>(form);
        sysUserManager.listUser(page, form);
        return page;
    }

    @Override
    public Result saveUser(SysUserEntity user) {
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        int count = sysUserManager.saveUser(user);
        return CommonUtils.msg(count);
    }

    @Override
    public Result getUserById(Long userId) {
        SysUserEntity user = sysUserManager.getById(userId);
        return CommonUtils.msg(user);
    }

    @Override
    public Result updateUser(SysUserEntity user) {
        int count = sysUserManager.updateUser(user);
        return CommonUtils.msg(count);
    }

    @Override
    public Result batchRemove(Long[] id) {
        int count = sysUserManager.batchRemove(id);
        return CommonUtils.msg(count);
    }

    @Override
    public Result listUserPerms(Long userId) {
        Set<String> perms = sysUserManager.listUserPerms(userId);
        return CommonUtils.msgNotCheckNull(perms);
    }

    @Override
    public Result updatePswdByUser(SysUserEntity user) {
        String username = user.getUsername();
        String pswd = user.getPassword();
        String newPswd = user.getEmail();
        pswd = MD5Utils.encrypt(username, pswd);
        newPswd = MD5Utils.encrypt(username, newPswd);
        Query query = new Query();
        query.put("userId", user.getUserId());
        query.put("pswd", pswd);
        query.put("newPswd", newPswd);
        int count = sysUserManager.updatePswdByUser(query);
        if (!CommonUtils.isIntThanZero(count)) {
            return Result.fail("原密码错误");
        }
        return CommonUtils.msg(count);
    }

    @Override
    public Result updateUserEnable(Long[] id) {
        int count = sysUserManager.updateUserEnable(id);
        return CommonUtils.msg(id, count);
    }

    @Override
    public Result updateUserDisable(Long[] id) {
        int count = sysUserManager.updateUserDisable(id);
        return CommonUtils.msg(id, count);
    }

    @Override
    public Result updatePswd(SysUserEntity user) {
        SysUserEntity currUser = sysUserManager.getUserById(user.getUserId());
        user.setPassword(MD5Utils.encrypt(currUser.getUsername(), user.getPassword()));
        int count = sysUserManager.updatePswd(user);
        return CommonUtils.msg(count);
    }

}
