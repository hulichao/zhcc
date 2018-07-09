package com.hu.zhcc.shiro.manager;


import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.common.entity.SysUserEntity;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 * <p>
 * Created by hulichao on 2018/6/20
 */
public interface SysUserManager {

    SysUserEntity getByUserName(String username);

    List<SysUserEntity> listUser(Page<SysUserEntity> page, Query search);

    int saveUser(SysUserEntity user);

    SysUserEntity getById(Long userId);

    int updateUser(SysUserEntity user);

    int batchRemove(Long[] id);

    Set<String> listUserPerms(Long userId);

    Set<String> listUserRoles(Long userId);

    int updatePswdByUser(Query query);

    int updateUserEnable(Long[] id);

    int updateUserDisable(Long[] id);

    int updatePswd(SysUserEntity user);

    SysUserEntity getUserById(Long userId);

}
