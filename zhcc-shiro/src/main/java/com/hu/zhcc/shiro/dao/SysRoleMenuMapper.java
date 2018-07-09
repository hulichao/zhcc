package com.hu.zhcc.shiro.dao;

import com.hu.zhcc.common.dao.BaseMapper;
import com.hu.zhcc.shiro.entity.SysRoleMenuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统角色与菜单关系
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

    int batchRemoveByMenuId(Long[] id);

    int batchRemoveByRoleId(Long[] id);

    List<Long> listMenuId(Long id);

}
