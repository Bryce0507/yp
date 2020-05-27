package com.yp.vo.wx.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动圈子汇总-视图")
public class CircleCountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer activityId;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "圈子人数")
    private String circleCount;

    @ApiModelProperty(value = "活动状态 0:待发布 1:进行中  2：已结束")
    private String activityStatus;


    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getCircleCount() {
        return circleCount;
    }

    public void setCircleCount(String circleCount) {
        this.circleCount = circleCount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }
}
