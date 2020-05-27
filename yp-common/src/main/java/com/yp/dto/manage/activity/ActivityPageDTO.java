package com.yp.dto.manage.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动列表DTO")
@Setter
@Getter
public class ActivityPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动状态 0:待发布 1:进行中  2：已结束")
    private String activityStatus;

    @ApiModelProperty(value = "奖励状态 0：未公布 1：已公布")
    private String rewardStatus;

    @ApiModelProperty(value = "开始日期")
    private Date startTime;

    @ApiModelProperty(value = "结束日期")
    private Date endTime;

    @ApiModelProperty(value = "显示条数")
    private int size=10;

    @ApiModelProperty(value = "当前页数")
    private int current=0;
}
