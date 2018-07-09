package com.hu.zhcc.common.controller;

import com.hu.zhcc.common.entity.SysUserEntity;
import com.hu.zhcc.common.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * Created by hulichao on 2018/6/21
 */
public abstract class AbstractController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
	
}
