package com.hu.zhcc.shiro.service;


import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.shiro.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 * <p>
 * Created by hulichao on 2018/6/20
 */
public interface SysMenuService {

    Result listUserMenu(Long userId);

    List<SysMenuEntity> listMenu(Map<String, Object> params);

    Result listNotButton();

    Result saveMenu(SysMenuEntity menu);

    Result getMenuById(Long id);

    Result updateMenu(SysMenuEntity menu);

    Result batchRemove(Long[] id);

}
