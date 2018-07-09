package com.hu.zhcc.shiro.controller;

import com.hu.zhcc.common.controller.AbstractController;
import com.hu.zhcc.common.entity.Result;
import com.hu.zhcc.shiro.entity.SysMenuEntity;
import com.hu.zhcc.shiro.service.SysMenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单controller
 * Created by hulichao on 2018/6/20
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 用户菜单
     *
     * @return
     */
    @RequestMapping("/user")
    public Result user() {
        return sysMenuService.listUserMenu(getUserId());
    }

    /**
     * 菜单列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public List<SysMenuEntity> listMenu(@RequestParam Map<String, Object> params) {
        return sysMenuService.listMenu(params);
    }

    /**
     * 选择菜单(添加、修改)
     *
     * @return
     */
    @RequestMapping("/select")
    public Result select() {
        return sysMenuService.listNotButton();
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SysMenuEntity menu) {
        return sysMenuService.saveMenu(menu);
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/info")
    public Result info(@RequestBody Long id) {
        return sysMenuService.getMenuById(id);
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysMenuEntity menu) {
        return sysMenuService.updateMenu(menu);
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    public Result remove(@RequestBody Long[] id) {
        return sysMenuService.batchRemove(id);
    }

}
