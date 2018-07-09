package com.hu.zhcc.shiro.manager.impl;

import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.shiro.dao.SysRoleMapper;
import com.hu.zhcc.shiro.dao.SysRoleMenuMapper;
import com.hu.zhcc.shiro.dao.SysUserRoleMapper;
import com.hu.zhcc.shiro.entity.SysRoleEntity;
import com.hu.zhcc.shiro.manager.SysRoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统角色
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Component("sysRoleManager")
public class SysRoleManagerImpl implements SysRoleManager {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleEntity> listRole(Page<SysRoleEntity> page, Query search) {
        return sysRoleMapper.listForPage(page, search);
    }

    @Override
    public int saveRole(SysRoleEntity role) {
        return sysRoleMapper.save(role);
    }

    @Override
    public SysRoleEntity getRoleById(Long id) {
        SysRoleEntity role = sysRoleMapper.getObjectById(id);
        List<Long> menuId = sysRoleMenuMapper.listMenuId(id);
        role.setMenuIdList(menuId);
        return role;
    }

    @Override
    public int updateRole(SysRoleEntity role) {
        return sysRoleMapper.update(role);
    }

    @Override
    public int batchRemove(Long[] id) {
        int count = sysRoleMapper.batchRemove(id);
        sysUserRoleMapper.batchRemoveByRoleId(id);
        sysRoleMenuMapper.batchRemoveByRoleId(id);
        return count;
    }

    @Override
    public List<SysRoleEntity> listRole() {
        return sysRoleMapper.list();
    }

    @Override
    public int updateRoleAuthorization(SysRoleEntity role) {
        Long roleId = role.getRoleId();
        int count = sysRoleMenuMapper.remove(roleId);
        Query query = new Query();
        query.put("roleId", roleId);
        List<Long> menuId = role.getMenuIdList();
        if (menuId.size() > 0) {
            query.put("menuIdList", role.getMenuIdList());
            count = sysRoleMenuMapper.save(query);
        }
        return count;
    }

}
