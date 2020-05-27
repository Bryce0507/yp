package com.yp.dto.wx.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "查询活动圈子列表")
public class QueryActivityPageDTO {

    @ApiModelProperty(value = "活动ID")
    @NotNull
    private String activityId;

    @ApiModelProperty(value = "显示调试")
    private int size=10;

    @ApiModelProperty(value = "当前页数")
    private int current=0;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
