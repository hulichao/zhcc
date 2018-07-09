package com.hu.zhcc.shiro.controller;

import com.hu.zhcc.common.constant.SystemConstant;
import com.hu.zhcc.common.controller.AbstractController;
import com.hu.zhcc.common.entity.Page;
import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.shiro.entity.SysRoleEntity;
import com.hu.zhcc.shiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 系统角色
 * <p>
 * Created by hulichao on 2018/6/20
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public Page<SysRoleEntity> list(@RequestBody Map<String, Object> params) {
        if (getUserId() != SystemConstant.SUPER_ADMIN) {
            params.put("userIdCreate", getUserId());
        }
        return sysRoleService.listRole(params);
    }

    /**
     * 用户角色
     *
     * @return
     */
    @RequestMapping("/select")
    public Result listRole() {
        return sysRoleService.listRole();
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public Result saveRole(@RequestBody SysRoleEntity role) {
        role.setUserIdCreate(getUserId());
        return sysRoleService.saveRole(role);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/info")
    public Result getRoleById(@RequestBody Long id) {
        return sysRoleService.getRoleById(id);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/update")
    public Result updateRole(@RequestBody SysRoleEntity role) {
        return sysRoleService.updateRole(role);
    }

    /**
     * 批量删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    public Result batchRemove(@RequestBody Long[] id) {
        return sysRoleService.batchRemove(id);
    }

    /**
     * 分配权限
     *
     * @param role
     * @return
     */
    @RequestMapping("/authorize")
    public Result updateRoleAuthorization(@RequestBody SysRoleEntity role) {
        return sysRoleService.updateRoleAuthorization(role);
    }

}
