package com.yp.util.wx.dto;

/**
 * 活动通知模板
 */
public class WxActivityApprovalDTO {

    private String openId;//用户微信唯一标识

    private String shopName;//门店名称

    private String applyTime;//申请时间

    private String name;//姓名

    private String reminder;//温馨提示


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
