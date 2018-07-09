package com.hu.zhcc.shiro.dao;

import com.hu.zhcc.common.dao.BaseMapper;
import com.hu.zhcc.shiro.entity.SysUserRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户与角色关系
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    List<Long> listUserRoleId(Long userId);

    int batchRemoveByUserId(Long[] id);

    int batchRemoveByRoleId(Long[] id);

}
