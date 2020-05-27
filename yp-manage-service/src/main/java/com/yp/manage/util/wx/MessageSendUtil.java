package com.yp.manage.util.wx;

import com.alibaba.fastjson.JSONObject;
import com.yp.manage.util.HttpSender;
import com.yp.manage.util.wx.dto.WxActivityNoticeDTO;
import com.yp.manage.util.wx.dto.WxActivityProgressDTO;
import com.yp.manage.util.wx.dto.WxActivityRewardDTO;
import com.yp.manage.util.wx.dto.WxApprovalRequDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class MessageSendUtil {


    private static Logger log = LoggerFactory.getLogger(MessageSendUtil.class);

    private final static String URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
    private final static String APPROVAL_TEMPLATE_ID = "ElOB9m6t10C-K7ICvclcS2vFBuE4uAGvDUwaFqrDWWM";

    private final static String ACTIVITY_TEMPLATE_ID = "Fh6xMzJq38J0u04BlFhT2DUy2RYVI403_AvwfqgXf-Q";

    private final static String ACTIVITY_END_TEMPLATE_ID = "EI8PE9EZTkvwUtTMuFX4142z0moQLTP2n_GdHYUPjO4";
    private final static String ACTIVITY_REWARD_ID = "ekR08eFZD3zUknxXB6GMOO6sbEIm-XMvZe4RX9Jb-ms";
    private final static String ACTIVITY_PROGRESS_ID = "ekR08eFZD3zUknxXB6GMOO6sbEIm-XMvZe4RX9Jb-ms";
    /**
     * 	业绩审批提醒
     *
     * @param approvalRequDTO
     * @return
     * @throws Exception
     */
    public static boolean sendApprovalMessage(WxApprovalRequDTO approvalRequDTO) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", approvalRequDTO.getOpenId());
        map.put("template_id", APPROVAL_TEMPLATE_ID);
        map.put("url", "");
        map.put("topcolor", "#FF0000");

        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, Object> first = new HashMap<String, Object>();
        first.put("value", approvalRequDTO.getApproverStatus());
        first.put("color", "#173177");
        data.put("phrase1", first);

        //Time
        Map<String, Object> keyword1 = new HashMap<String, Object>();
        keyword1.put("value", approvalRequDTO.getDate());
        keyword1.put("color", "#173177");
        data.put("date2", keyword1);

        //Host
        Map<String, Object> keyword2 = new HashMap<String, Object>();
        keyword2.put("value", approvalRequDTO.getDesc());
        keyword2.put("color", "#173177");
        data.put("thing3", keyword2);

        //Service
        Map<String, Object> keyword3 = new HashMap<String, Object>();
        keyword3.put("value", approvalRequDTO.getReason());
        keyword3.put("color", "#173177");
        data.put("thing5", keyword3);


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

    /**
     * 	活动通知
     *
     * @param activityNoticeDTO
     * @return
     * @throws Exception
     */
    public static boolean sendActivityMessage(WxActivityNoticeDTO activityNoticeDTO) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", activityNoticeDTO.getOpenId());
        map.put("template_id", ACTIVITY_TEMPLATE_ID);
        map.put("url", "");
        map.put("topcolor", "#FF0000");

        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, Object> first = new HashMap<String, Object>();
        first.put("value", activityNoticeDTO.getActivityName());
        first.put("color", "#173177");
        data.put("thing4", first);

        //Time
        Map<String, Object> keyword1 = new HashMap<String, Object>();
        keyword1.put("value", activityNoticeDTO.getStartTime()+"至"+activityNoticeDTO.getEndTime());
        keyword1.put("color", "#173177");
        data.put("thing2", keyword1);

        //Host
        Map<String, Object> keyword2 = new HashMap<String, Object>();
        keyword2.put("value", activityNoticeDTO.getReminder());
        keyword2.put("color", "#173177");
        data.put("thing3", keyword2);

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


    /**
     * 	活动结束通知
     *
     * @param activityNoticeDTO
     * @return
     * @throws Exception
     */
    public static boolean sendActivityEndMessage(WxActivityNoticeDTO activityNoticeDTO) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", activityNoticeDTO.getOpenId());
        map.put("template_id", ACTIVITY_END_TEMPLATE_ID);
        map.put("url", "");
        map.put("topcolor", "#FF0000");

        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, Object> first = new HashMap<String, Object>();
        first.put("value", activityNoticeDTO.getActivityName());
        first.put("color", "#173177");
        data.put("thing4", first);

        //Time
        Map<String, Object> keyword1 = new HashMap<String, Object>();
        keyword1.put("value", activityNoticeDTO.getStartTime()+"至"+activityNoticeDTO.getEndTime());
        keyword1.put("color", "#173177");
        data.put("thing2", keyword1);

        //Host
        Map<String, Object> keyword2 = new HashMap<String, Object>();
        keyword2.put("value", activityNoticeDTO.getReminder());
        keyword2.put("color", "#173177");
        data.put("thing3", keyword2);

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


    /**
     * 	公布奖励
     *
     * @param activityRewardDTO
     * @return
     * @throws Exception
     */
    public static boolean sendRewardActivity(WxActivityRewardDTO activityRewardDTO) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", activityRewardDTO.getOpenId());
        map.put("template_id", ACTIVITY_PROGRESS_ID);
        map.put("url", "");
        map.put("topcolor", "#FF0000");

        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, Object> first = new HashMap<String, Object>();
        first.put("value", activityRewardDTO.getActivityName());
        first.put("color", "#173177");
        data.put("thing1", first);

        //Time
        Map<String, Object> keyword1 = new HashMap<String, Object>();
        keyword1.put("value", activityRewardDTO.getStartTime()+"至"+activityRewardDTO.getEndTime());
        keyword1.put("color", "#173177");
        data.put("time2", keyword1);

        //Host
        Map<String, Object> keyword2 = new HashMap<String, Object>();
        keyword2.put("value", activityRewardDTO.getReminder());
        keyword2.put("color", "#173177");
        data.put("thing3", keyword2);

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


    /**
     * 	活动进度
     *
     * @param activityProgressDTO
     * @return
     * @throws Exception
     */
    public static boolean sendActivityProgress(WxActivityProgressDTO activityProgressDTO) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", activityProgressDTO.getOpenId());
        map.put("template_id", ACTIVITY_REWARD_ID);
        map.put("url", "");
        map.put("topcolor", "#FF0000");

        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, Object> first = new HashMap<String, Object>();
        first.put("value", activityProgressDTO.getActivityName());
        first.put("color", "#173177");
        data.put("thing1", first);

        //Time
        Map<String, Object> keyword1 = new HashMap<String, Object>();
        keyword1.put("value", activityProgressDTO.getStartTime()+"至"+activityProgressDTO.getEndTime());
        keyword1.put("color", "#173177");
        data.put("time2", keyword1);

        //Host
        Map<String, Object> keyword2 = new HashMap<String, Object>();
        keyword2.put("value", activityProgressDTO.getSurplusTime());
        keyword2.put("color", "#173177");
        data.put("thing3", keyword2);

        //Host
        Map<String, Object> keyword4 = new HashMap<String, Object>();
        keyword2.put("value", activityProgressDTO.getTarget());
        keyword2.put("color", "#173177");
        data.put("thing4", keyword4);

        //Host
        Map<String, Object> keyword5 = new HashMap<String, Object>();
        keyword2.put("value", activityProgressDTO.getProgress());
        keyword2.put("color", "#173177");
        data.put("thing5", keyword5);


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