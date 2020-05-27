package com.yp.manage.util.wx.dto;

/**
 * 奖励进度
 */
public class WxActivityProgressDTO {

    private String openId;//用户微信唯一标识

    private String activityName;//活动名称

    private String startTime;//开始时间

    private String endTime;//结束时间

    private String surplusTime;//剩余时间

    private String target;//目标

    private String progress;//进度


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

    public String getSurplusTime() {
        return surplusTime;
    }

    public void setSurplusTime(String surplusTime) {
        this.surplusTime = surplusTime;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
