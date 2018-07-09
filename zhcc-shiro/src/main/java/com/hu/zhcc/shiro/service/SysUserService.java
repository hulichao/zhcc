package com.hu.zhcc.shiro.service;


import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.common.entity.SysUserEntity;

import java.util.Map;

/**
 * 系统用户
 * <p>
 * Created by hulichao on 2018/6/20
 */
public interface SysUserService {

    Page<SysUserEntity> listUser(Map<String, Object> params);

    Result saveUser(SysUserEntity user);

    Result getUserById(Long userId);

    Result updateUser(SysUserEntity user);

    Result batchRemove(Long[] id);

    Result listUserPerms(Long userId);

    Result updatePswdByUser(SysUserEntity user);

    Result updateUserEnable(Long[] id);

    Result updateUserDisable(Long[] id);

    Result updatePswd(SysUserEntity user);

}
