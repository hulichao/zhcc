package com.hu.zhcc.shiro.service;


import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.shiro.entity.SysRoleEntity;

import java.util.Map;

/**
 * 系统角色
 * <p>
 * Created by hulichao on 2018/6/20
 */
public interface SysRoleService {

    Page<SysRoleEntity> listRole(Map<String, Object> params);

    Result saveRole(SysRoleEntity role);

    Result getRoleById(Long id);

    Result updateRole(SysRoleEntity role);

    Result batchRemove(Long[] id);

    Result listRole();

    Result updateRoleAuthorization(SysRoleEntity role);

}
