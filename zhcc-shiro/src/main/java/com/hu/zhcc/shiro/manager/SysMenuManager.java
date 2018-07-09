package com.hu.zhcc.shiro.manager;


import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.shiro.entity.SysMenuEntity;

import java.util.List;

/**
 * 系统菜单
 * <p>
 * Created by hulichao on 2018/6/20
 */
public interface SysMenuManager {

    List<SysMenuEntity> listUserMenu(Long userId);

    List<SysMenuEntity> listParentId(Long parentId, List<Long> menuIdList);

    List<SysMenuEntity> listMenu(Query search);

    List<SysMenuEntity> listNotButton();

    int saveMenu(SysMenuEntity menu);

    SysMenuEntity getMenuById(Long id);

    int updateMenu(SysMenuEntity menu);

    int batchRemove(Long[] id);

    boolean hasChildren(Long[] id);

}
