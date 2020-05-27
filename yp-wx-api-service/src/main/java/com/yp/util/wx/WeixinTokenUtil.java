package com.yp.util.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yp.util.HttpSender;
import com.yp.util.wx.config.WeixinConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * <p>Title: WeixinTokenUtil</p>
 * <p>Description: 微信tokenUtil</p>
 * @version 1.0
 */
public class WeixinTokenUtil {

	private static final Logger log = LoggerFactory.getLogger(WeixinTokenUtil.class);


    private final static String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
            + WeixinConfiguration.appID+"&secret=" + WeixinConfiguration.appSecret;
    /**
     *
     * getToken (获取token)
     * @return
     * @throws Exception
     */
    public static String getToken() throws Exception{
        String sb = HttpSender.getRequest(URL);
        try {
            JSONObject jsonObject = JSON.parseObject(sb);
            String access_token = jsonObject.getString("access_token");
            log.info("调用getToken获取token>>>" + access_token);
            if(StringUtils.isNotBlank(access_token)){
                return access_token;
            }
        } catch (Exception e) {
        	log.error("调用getToken获取token出错！");
        }
        return null;
    }

}
