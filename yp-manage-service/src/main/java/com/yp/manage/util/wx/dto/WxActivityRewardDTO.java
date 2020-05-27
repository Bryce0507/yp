package com.yp.manage.util.wx.dto;

/**
 * 奖励公布
 */
public class WxActivityRewardDTO {

    private String openId;//用户微信唯一标识

    private String activityName;//活动名称

    private String startTime;//开始时间

    private String endTime;//结束时间

    private String reminder;//温馨提示


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
