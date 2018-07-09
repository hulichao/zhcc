package com.hu.zhcc.shiro.manager.impl;

import com.hu.zhcc.common.constant.SystemConstant;
import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.common.entity.SysUserEntity;
import com.hu.zhcc.shiro.dao.SysMenuMapper;
import com.hu.zhcc.shiro.dao.SysRoleMapper;
import com.hu.zhcc.shiro.dao.SysUserMapper;
import com.hu.zhcc.shiro.dao.SysUserRoleMapper;
import com.hu.zhcc.shiro.manager.SysUserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统用户
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Component("sysUserManager")
public class SysUserManagerImpl implements SysUserManager {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


    @Override
    public List<SysUserEntity> listUser(Page<SysUserEntity> page, Query search) {
        return sysUserMapper.listForPage(page, search);
    }

    @Override
    public SysUserEntity getByUserName(String username) {
        return sysUserMapper.getByUserName(username);
    }

    @Override
    public int saveUser(SysUserEntity user) {
        int count = sysUserMapper.save(user);
        Query query = new Query();
        query.put("userId", user.getUserId());
        query.put("roleIdList", user.getRoleIdList());
        sysUserRoleMapper.save(query);
        return count;
    }

    @Override
    public SysUserEntity getById(Long userId) {
        SysUserEntity user = sysUserMapper.getObjectById(userId);
        user.setRoleIdList(sysUserRoleMapper.listUserRoleId(userId));
        return user;
    }

    @Override
    public int updateUser(SysUserEntity user) {
        int count = sysUserMapper.update(user);
        Long userId = user.getUserId();
        sysUserRoleMapper.remove(userId);
        Query query = new Query();
        query.put("userId", userId);
        query.put("roleIdList", user.getRoleIdList());
        sysUserRoleMapper.save(query);
        return count;
    }

    @Override
    public int batchRemove(Long[] id) {
        int count = sysUserMapper.batchRemove(id);
        sysUserRoleMapper.batchRemoveByUserId(id);
        return count;
    }

    @Override
    public Set<String> listUserPerms(Long userId) {
        List<String> perms = sysMenuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public Set<String> listUserRoles(Long userId) {
        List<String> roles = sysRoleMapper.listUserRoles(userId);
        Set<String> rolesSet = new HashSet<>();
        for (String role : roles) {
            if (StringUtils.isNotBlank(role)) {
                rolesSet.addAll(Arrays.asList(role.trim().split(",")));
            }
        }
        return rolesSet;
    }

    @Override
    public int updatePswdByUser(Query query) {
        return sysUserMapper.updatePswdByUser(query);
    }

    @Override
    public int updateUserEnable(Long[] id) {
        Query query = new Query();
        query.put("status", SystemConstant.StatusType.ENABLE.getValue());
        query.put("id", id);
        int count = sysUserMapper.updateUserStatus(query);
        return count;
    }

    @Override
    public int updateUserDisable(Long[] id) {
        Query query = new Query();
        query.put("status", SystemConstant.StatusType.DISABLE.getValue());
        query.put("id", id);
        int count = sysUserMapper.updateUserStatus(query);
        return count;
    }

    @Override
    public int updatePswd(SysUserEntity user) {
        return sysUserMapper.updatePswd(user);
    }

    @Override
    public SysUserEntity getUserById(Long userId) {//不包含角色信息
        return sysUserMapper.getObjectById(userId);
    }

}
