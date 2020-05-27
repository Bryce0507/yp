package com.yp.manage.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yp.common.exception.PreBaseException;
import com.yp.common.utils.R;
import com.yp.dto.UserDTO;
import com.yp.dto.manage.user.AddUserDTO;
import com.yp.dto.manage.user.UserPageDTO;
import com.yp.entity.SysUser;
import com.yp.service.ISysUserService;
import com.yp.util.PreUtil;
import com.yp.util.SecurityUtil;
import com.yp.vo.PageBean;
import com.yp.vo.manage.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @ApiOperation(value = "新增用户")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public R insert(@RequestBody @Valid AddUserDTO addUserDTO) {
        return R.ok(userService.insertUser(addUserDTO));
    }


    @ApiOperation(value = "获取用户列表集合")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:user:view')")
    public R<PageBean<UserVO>> getList(@Valid UserPageDTO userDTO) {
        return R.ok(userService.getUsersWithRolePage(userDTO));
    }

    /**
     * 更新用户包括角色和部门
     *
     * @param userDto
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:update')")
    public R update(@RequestBody @Valid AddUserDTO userDto) {
        return R.ok(userService.updateUser(userDto));
    }

    /**
     * 删除用户包括角色和部门
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public R delete(@PathVariable("userId") Integer userId) {
        return R.ok(userService.removeUser(userId));
    }


    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('sys:user:rest')")
    public R restPass(@PathVariable("userId") Integer userId) {
        return R.ok(userService.restPass(userId));
    }


    /**
     * 获取个人信息
     *
     * @return
     */
    @GetMapping("/info")
    public R getUserInfo() {
        return R.ok(userService.findByUserInfoName(SecurityUtil.getUser().getUsername()));
    }

    /**
     * 修改密码
     *
     * @return
     */
    @PutMapping("updatePass")
    @PreAuthorize("hasAuthority('sys:user:updatePass')")
    public R updatePass(@RequestParam String oldPass, @RequestParam String newPass) {
        // 校验密码流程

        SysUser sysUser = userService.findSecurityUserByUser(new SysUser().setUsername(SecurityUtil.getUser().getUsername()).setUserType("1"));
        if (!PreUtil.validatePass(oldPass, sysUser.getPassword())) {
            throw new PreBaseException("原密码错误");
        }
        if (StrUtil.equals(oldPass, newPass)) {
            throw new PreBaseException("新密码不能与旧密码相同");
        }
        // 修改密码流程
        SysUser user = new SysUser();
        user.setUserId(sysUser.getUserId());
        user.setPassword(PreUtil.encode(newPass));
        return R.ok(userService.updateUserInfo(user));
    }

    /**
     * 检测用户名是否存在 避免重复
     *
     * @param userName
     * @return
     */
    @PostMapping("/vailUserName")
    public R vailUserName(@RequestParam String userName) {
        SysUser sysUser = userService.findSecurityUserByUser(new SysUser().setUsername(userName));
        return R.ok(ObjectUtil.isNull(sysUser));
    }

}

