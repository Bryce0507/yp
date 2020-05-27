package com.yp.manage.controller;


import com.yp.common.utils.R;
import com.yp.dto.MenuDTO;
import com.yp.entity.SysMenu;
import com.yp.security.PreSecurityUser;
import com.yp.service.ISysMenuService;
import com.yp.util.PreUtil;
import com.yp.util.SecurityUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Api(tags = "菜单")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:add')")
    @PostMapping
    public R save(@RequestBody SysMenu menu) {
        return R.ok(menuService.save(menu));
    }

    /**
     * 获取菜单树
     *
     * @return
     */
    @GetMapping
    public R getMenuTree() {
        PreSecurityUser securityUser = SecurityUtil.getUser();
        return R.ok(menuService.selectMenuTree(securityUser.getUserId()));
    }


    /**
     * 获取所有菜单
     *
     * @return
     */
    @GetMapping("/getMenus")
    public R getMenus() {
        return R.ok(menuService.selectMenuTree(0));
    }

    /**
     * 修改菜单
     *
     * @param menuDto
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:update')")
    @PutMapping
    public R updateMenu(@RequestBody MenuDTO menuDto) {
        return R.ok(menuService.updateMenuById(menuDto));
    }

    /**
     * 根据id删除菜单
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @DeleteMapping("/{id}")
    public R deleteMenu(@PathVariable("id") Integer id) {
        return menuService.removeMenuById(id);
    }

    /**
     * 获取路由
     *
     * @return
     */
    @GetMapping("/getRouters")
    public R getRouters() {
        PreSecurityUser securityUser = SecurityUtil.getUser();
//        List<MenuVo> menuVos = PreUtil.buildMenus(menuService.selectMenuTree(securityUser.getUserId()));
        return R.ok(PreUtil.buildMenus(menuService.selectMenuTree(securityUser.getUserId())));
    }

}

