package com.yp.util.wx;

import com.alibaba.fastjson.JSONObject;
import com.yp.util.HttpSender;
import com.yp.util.wx.dto.WxActivityApprovalDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class MessageSendUtil {


    private static Logger log = LoggerFactory.getLogger(MessageSendUtil.class);

    private final static String URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
    private final static String APPROVAL_TEMPLATE_ID = "RuObCmXR034nPyXaFAw5Nn1ztmA6k6FphJHeXePRPz4";


    /**
     * 	待审核通知
     *
     * @param approvalRequDTO
     * @return
     * @throws Exception
     */
    public static boolean sendApprovalMessage(WxActivityApprovalDTO approvalRequDTO) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", approvalRequDTO.getOpenId());
        map.put("template_id", APPROVAL_TEMPLATE_ID);
        map.put("url", "http://manage.1haopei.com/index");
        map.put("topcolor", "#FF0000");

        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, Object> first = new HashMap<String, Object>();
        first.put("value", approvalRequDTO.getShopName());
        first.put("color", "#173177");
        data.put("thing1", first);

        //Time
        Map<String, Object> keyword1 = new HashMap<String, Object>();
        keyword1.put("value", approvalRequDTO.getApplyTime());
        keyword1.put("color", "#173177");
        data.put("date2", keyword1);

        //Host
        Map<String, Object> keyword2 = new HashMap<String, Object>();
        keyword2.put("value", approvalRequDTO.getName());
        keyword2.put("color", "#173177");
        data.put("name5", keyword2);

        //Service
        Map<String, Object> keyword3 = new HashMap<String, Object>();
        keyword3.put("value", approvalRequDTO.getReminder());
        keyword3.put("color", "#173177");
        data.put("thing4", keyword3);


        map.put("data", data);
        String param = JSONObject.toJSONString(map);
        log.info("param参数为>>>>" + param);
        String token = WeixinTokenUtil.getToken();
        StringBuffer result = HttpSender.postRequest(URL + token, param);
        JSONObject resultJson = JSONObject.parseObject(result.toString());
        log.info("返回结果为>>>>" + result);
        if (!StringUtils.isBlank(resultJson.getString("errmsg")) && "ok".equals(resultJson.getString("errmsg"))) {
            return true;
        }
        return false;
    }

}