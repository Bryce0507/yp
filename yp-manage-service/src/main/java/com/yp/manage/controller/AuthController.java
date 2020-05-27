package com.yp.manage.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.yp.common.constant.PreConstant;
import com.yp.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Classname AuthController
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019/12/15 4:04 下午
 * @Version 1.0
 */
@Api(tags = "验证码")
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 生成验证码
     *
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("/captcha.jpg")
    public R captcha() throws IOException {
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();

        String key = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(PreConstant.PRE_IMAGE_KEY + key, result, 2, TimeUnit.MINUTES);
        Map map = new HashMap();
        map.put("key", key);
        map.put("img", captcha.toBase64());
        return R.ok(map);
    }
}
