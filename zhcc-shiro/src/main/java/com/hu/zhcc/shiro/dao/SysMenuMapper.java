package com.hu.zhcc.shiro.dao;

import com.hu.zhcc.common.dao.BaseMapper;
import com.hu.zhcc.shiro.entity.SysMenuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统菜单dao
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    List<SysMenuEntity> listParentId(Long parentId);

    List<SysMenuEntity> listNotButton();

    List<String> listUserPerms(Long userId);

    int countMenuChildren(Long parentId);

}
