package com.hu.zhcc.shiro.service.impl;

import com.hu.zhcc.common.constant.MsgConstant;
import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.common.utils.CommonUtils;
import com.hu.zhcc.shiro.entity.SysMenuEntity;
import com.hu.zhcc.shiro.manager.SysMenuManager;
import com.hu.zhcc.shiro.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuManager sysMenuManager;

    @Override
    public Result listUserMenu(Long userId) {
        return Result.success().put("menuList", sysMenuManager.listUserMenu(userId));
    }

    @Override
    public List<SysMenuEntity> listMenu(Map<String, Object> params) {
        Query query = new Query(params);
        List<SysMenuEntity> menuList = sysMenuManager.listMenu(query);
        return menuList;
    }

    @Override
    public Result listNotButton() {
        List<SysMenuEntity> menuList = sysMenuManager.listNotButton();
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);
        return CommonUtils.msgNotCheckNull(menuList);
    }

    @Override
    public Result saveMenu(SysMenuEntity menu) {
        int count = sysMenuManager.saveMenu(menu);
        return CommonUtils.msg(count);
    }

    @Override
    public Result getMenuById(Long id) {
        SysMenuEntity menu = sysMenuManager.getMenuById(id);
        return CommonUtils.msg(menu);
    }

    @Override
    public Result updateMenu(SysMenuEntity menu) {
        int count = sysMenuManager.updateMenu(menu);
        return CommonUtils.msg(count);
    }

    @Override
    public Result batchRemove(Long[] id) {
        boolean children = sysMenuManager.hasChildren(id);
        if (children) {
            return Result.fail(MsgConstant.MSG_HAS_CHILD);
        }
        int count = sysMenuManager.batchRemove(id);
        return CommonUtils.msg(id, count);
    }

}
