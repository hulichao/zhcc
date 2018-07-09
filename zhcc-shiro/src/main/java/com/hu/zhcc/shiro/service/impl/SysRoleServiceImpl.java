package com.hu.zhcc.shiro.service.impl;

import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.common.utils.CommonUtils;
import com.hu.zhcc.shiro.entity.SysRoleEntity;
import com.hu.zhcc.shiro.manager.SysRoleManager;
import com.hu.zhcc.shiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统角色
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleManager sysRoleManager;

    @Override
    public Page<SysRoleEntity> listRole(Map<String, Object> params) {
        Query query = new Query(params);
        Page<SysRoleEntity> page = new Page<>(query);
        sysRoleManager.listRole(page, query);
        return page;
    }

    @Override
    public Result saveRole(SysRoleEntity role) {
        int count = sysRoleManager.saveRole(role);
        return CommonUtils.msg(count);
    }

    @Override
    public Result getRoleById(Long id) {
        SysRoleEntity role = sysRoleManager.getRoleById(id);
        return CommonUtils.msg(role);
    }

    @Override
    public Result updateRole(SysRoleEntity role) {
        int count = sysRoleManager.updateRole(role);
        return CommonUtils.msg(count);
    }

    @Override
    public Result batchRemove(Long[] id) {
        int count = sysRoleManager.batchRemove(id);
        return CommonUtils.msg(id, count);
    }

    @Override
    public Result listRole() {
        List<SysRoleEntity> roleList = sysRoleManager.listRole();
        return CommonUtils.msgNotCheckNull(roleList);
    }

    @Override
    public Result updateRoleAuthorization(SysRoleEntity role) {
        int count = sysRoleManager.updateRoleAuthorization(role);
        return CommonUtils.msg(count);
    }

}
