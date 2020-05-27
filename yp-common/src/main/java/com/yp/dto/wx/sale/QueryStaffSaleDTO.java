package com.yp.dto.wx.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

@ApiModel(value = "查询同事的业绩")
public class QueryStaffSaleDTO {


    @NotNull
    @ApiModelProperty(value = "活动ID")
    private String activityId;

    @NotNull
    @ApiModelProperty(value = "同事ID")
    private String staffUserId;

    @NotNull
    @ApiModelProperty(value = "产品ID")
    private String productId;

    @NotNull
    @ApiModelProperty(value = "销售时间")
    private String saleTime;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }
}
