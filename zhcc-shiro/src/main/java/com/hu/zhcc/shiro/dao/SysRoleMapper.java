package com.hu.zhcc.shiro.dao;

import com.hu.zhcc.common.dao.BaseMapper;
import com.hu.zhcc.shiro.entity.SysRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统角色
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    List<String> listUserRoles(Long userId);

}
