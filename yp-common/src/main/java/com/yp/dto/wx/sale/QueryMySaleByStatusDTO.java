package com.yp.dto.wx.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

@ApiModel(value = "查询我的业绩")
public class QueryMySaleByStatusDTO {


    @NotNull
    @ApiModelProperty(value = "活动ID")
    private String activityId;

    @NotNull
    @ApiModelProperty(value = "业绩状态 0：待审核 2:驳回")
    private String approveStatus;


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }
}
