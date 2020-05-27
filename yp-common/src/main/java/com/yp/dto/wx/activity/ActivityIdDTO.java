package com.yp.dto.wx.activity;

import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ApiModel(value = "活动ID")
public class ActivityIdDTO {

    /**
     * 活动ID
     */
    @NotNull
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
