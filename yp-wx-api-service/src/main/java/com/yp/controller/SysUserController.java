package com.yp.controller;


import com.yp.common.utils.R;
import com.yp.dto.wx.WxChangePwdDTO;
import com.yp.entity.SysUser;
import com.yp.service.ISysUserService;
import com.yp.util.PreUtil;
import com.yp.util.SecurityUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hcs
 * @since 2020-02-15
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;


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
    public R updatePass( @RequestBody WxChangePwdDTO wxChangePwdDTO) {
        // 校验密码流程
        SysUser sysUser = userService.findSecurityUserByUser(new SysUser().setUsername(SecurityUtil.getUser().getUsername()).setUserType("0"));
        // 修改密码流程
        SysUser user = new SysUser();
        user.setUserId(sysUser.getUserId());
        user.setPassword(PreUtil.encode(wxChangePwdDTO.getNewPass()));
        return R.ok(userService.updateUserInfo(user));
    }

}

