package com.yp.dto.manage.reward;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 活动奖励
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动奖励")
@Setter
@Getter
public class ActivityAwardItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "奖励ID")
    private Integer pkid;

    @ApiModelProperty(value = "活动ID")
    private Integer activityId;

    @ApiModelProperty(value = "门店编号")
    private Integer shopCode;

    @ApiModelProperty(value = "奖项名称")
    private String awardName;

    @ApiModelProperty(value = "奖励")
    private String award;

    @ApiModelProperty(value = "活动业绩")
    private Integer sale;

    @ApiModelProperty(value = "排名")
    private Integer top;

    @ApiModelProperty(value = "业绩单位 0：台数 1：金额")
    private String unit;
	
}
