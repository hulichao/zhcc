package com.hu.zhcc.shiro.manager.impl;

import com.hu.zhcc.common.constant.SystemConstant;
import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.common.utils.CommonUtils;
import com.hu.zhcc.shiro.dao.SysMenuMapper;
import com.hu.zhcc.shiro.dao.SysRoleMenuMapper;
import com.hu.zhcc.shiro.dao.SysUserMapper;
import com.hu.zhcc.shiro.entity.SysMenuEntity;
import com.hu.zhcc.shiro.manager.SysMenuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Component("sysMenuManager")
public class SysMenuManagerImpl implements SysMenuManager {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenuEntity> listUserMenu(Long userId) {
        List<Long> menuIdList = sysUserMapper.listAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = listParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for (SysMenuEntity entity : menuList) {
            if (entity.getType() == SystemConstant.MenuType.CATALOG.getValue()) {//目录
                entity.setList(getMenuTreeList(listParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }

    @Override
    public List<SysMenuEntity> listParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = sysMenuMapper.listParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> listMenu(Query search) {
        return sysMenuMapper.list(search);
    }

    @Override
    public List<SysMenuEntity> listNotButton() {
        return sysMenuMapper.listNotButton();
    }

    @Override
    public int saveMenu(SysMenuEntity menu) {
        return sysMenuMapper.save(menu);
    }

    @Override
    public SysMenuEntity getMenuById(Long id) {
        return sysMenuMapper.getObjectById(id);
    }

    @Override
    public int updateMenu(SysMenuEntity menu) {
        return sysMenuMapper.update(menu);
    }

    @Override
    public int batchRemove(Long[] id) {
        int count = sysMenuMapper.batchRemove(id);
        sysRoleMenuMapper.batchRemoveByMenuId(id);
        return count;
    }

    @Override
    public boolean hasChildren(Long[] id) {
        for (Long parentId : id) {
            int count = sysMenuMapper.countMenuChildren(parentId);
            if (CommonUtils.isIntThanZero(count)) {
                return true;
            }
        }
        return false;
    }

}
