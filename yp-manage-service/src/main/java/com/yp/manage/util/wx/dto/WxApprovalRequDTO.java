package com.yp.manage.util.wx.dto;


/**
 * 项目审批微信模板通知输入
 */
public class WxApprovalRequDTO {

    private String openId;//用户微信唯一标识

    private String approverStatus;//审批状态

    private String desc;//备注
    
    private String date;//审核时间
    
    private String reason;//原因

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getApproverStatus() {
        return approverStatus;
    }

    public void setApproverStatus(String approverStatus) {
        this.approverStatus = approverStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
