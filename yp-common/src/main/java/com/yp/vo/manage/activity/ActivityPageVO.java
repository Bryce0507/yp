package com.yp.vo.manage.activity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动列表VO")
@Setter
@Getter
public class ActivityPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer pkid;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动状态 0:待发布 1:进行中  2：已结束")
    private String activityStatus;

    @ApiModelProperty(value = "奖励状态 0：未公布 1：已公布")
    private String rewardStatus;

    @ApiModelProperty(value = "是否公开 0:是 1：否")
    private String isPublic="0";

    @ApiModelProperty(value = "奖励方案类型： 0：人工 1：自动")
    private String rewardPlanType;

    @ApiModelProperty(value = "当前时间")
    private Date currentTime;

    @ApiModelProperty(value = "开始日期")
    private Date startTime;

    @ApiModelProperty(value = "结束日期")
    private Date endTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

}
