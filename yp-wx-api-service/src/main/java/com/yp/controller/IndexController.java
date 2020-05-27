package com.yp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yp.common.utils.R;
import com.yp.dto.wx.WxBindDTO;
import com.yp.service.ISysUserService;
import com.yp.util.wx.WxUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname IndexController
 * @Description 主页模块
 * @Author hcs
 * @Date 2020-02-16 12:38
 * @Version 1.0
 */
@Api(tags = "主页")
@RestController
public class IndexController {

    @Autowired
    private ISysUserService userService;
    @Autowired
    WxUtil wxUtil;

    @ApiOperation(value = "登录")
    @GetMapping(value = "/login")
    public R login(String username, String password, HttpServletRequest request) {
        return R.ok(userService.login(username, password));
    }

    @ApiOperation(value = "获取openId")
    @GetMapping(value = "/openid")
    public R openid(String code) {
       String openid=wxUtil.getOpenId(code);
       Map result =new HashMap();
       result.put("openId",openid);
       return R.ok(result);
    }

    @ApiOperation(value = "绑定微信及刷新")
    @PostMapping("/bind")
    public R register(@RequestBody @Valid WxBindDTO user) {
        return R.ok(userService.bind(user));
    }

    /**
     * @Author 李号东
     * @Description 使用jwt前后分离 只需要前端清除token即可 暂时返回success
     * @Date 08:13 2019-06-22
     **/
    @ApiOperation(value = "登出")
    @PostMapping("/logout")
    public String logout() {
        return "success";
    }

}
