package com.yp.manage.controller;

import cn.hutool.core.util.StrUtil;
import com.yp.common.utils.R;
import com.yp.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname IndexController
 * @Description 登录模块
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-05-07 12:38
 * @Version 1.0
 */
@Api(tags = "登录模块")
@RestController
public class IndexController {

    @Autowired
    private ISysUserService userService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public R login(String username, String password, HttpServletRequest request) {
        // 社交快速登录
        String token = request.getParameter("token");
        if (StrUtil.isNotEmpty(token)) {
            return R.ok(token);
        }
        return R.ok(userService.login(username, password));
    }

    /**
     * @Author 李号东
     * @Description 使用jwt前后分离 只需要前端清除token即可 暂时返回success
     * @Date 08:13 2019-06-22
     **/
    @RequestMapping("/logout")
    public String logout() {
        return "success";
    }


    /**
     * @Author 李号东
     * @Description 暂时这样写
     * @Date 08:12 2019-06-22
     **/
    @RequestMapping("/info")
    public R info() {
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("admin");
        map.put("roles", list);
        map.put("avatar", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561394014552&di=17b6c1233048e5276f48309b306c7699&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201804%2F29%2F20180429210111_gtsnf.jpg");
        map.put("name", "Super Admin");
        return R.ok(map);
    }

}
