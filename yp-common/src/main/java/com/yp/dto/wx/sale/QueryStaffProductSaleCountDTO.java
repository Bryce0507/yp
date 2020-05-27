package com.yp.dto.wx.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "查询同事产品业绩统计")
public class QueryStaffProductSaleCountDTO {


    @NotNull
    @ApiModelProperty(value = "活动ID")
    private String activityId;

    @NotNull
    @ApiModelProperty(value = "同事ID")
    private String staffUserId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getStaffUserId() {
        return staffUserId;
    }

    public void setStaffUserId(String staffUserId) {
        this.staffUserId = staffUserId;
    }
}
