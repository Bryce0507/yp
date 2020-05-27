package com.yp.util.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yp.util.HttpUtils;
import com.yp.util.RedisUtil;
import com.yp.util.wx.config.WeixinConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WxUtil {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    public String getOpenId(String wxcode) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", WeixinConfiguration.appID);
        paramMap.put("secret", WeixinConfiguration.appSecret);
        paramMap.put("js_code", wxcode);
        paramMap.put("grant_type", "authorization_code");
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String result = HttpUtils.sendPost(url, paramMap);
        JSONObject tokenJson = JSONObject.parseObject(result);
        //用户的唯一标识（openid）
        String openId = (String) tokenJson.get("openid");
        log.info("获取微信openId{}", openId);
        redisTemplate.opsForHash().put("wxuserInfo",wxcode,tokenJson);
        return openId;
    }
}
