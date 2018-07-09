package com.hu.zhcc.shiro.manager;


import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.shiro.entity.SysRoleEntity;

import java.util.List;

/**
 * 系统角色
 * <p>
 * Created by hulichao on 2018/6/20
 */
public interface SysRoleManager {

    List<SysRoleEntity> listRole(Page<SysRoleEntity> page, Query search);

    int saveRole(SysRoleEntity role);

    SysRoleEntity getRoleById(Long id);

    int updateRole(SysRoleEntity role);

    int batchRemove(Long[] id);

    List<SysRoleEntity> listRole();

    int updateRoleAuthorization(SysRoleEntity role);

}
