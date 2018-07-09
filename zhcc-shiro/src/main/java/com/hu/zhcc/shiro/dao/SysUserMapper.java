package com.hu.zhcc.shiro.dao;

import com.hu.zhcc.common.dao.BaseMapper;
import com.hu.zhcc.common.entity.Query;
import com.hu.zhcc.common.entity.SysUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户dao
 * <p>
 * Created by hulichao on 2018/6/20
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    SysUserEntity getByUserName(String username);

    List<Long> listAllMenuId(Long userId);

    int updatePswdByUser(Query query);

    int updateUserStatus(Query query);

    int updatePswd(SysUserEntity user);

}
