package com.yp.dto.manage.reward;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 活动奖励
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "人工活动奖励")
@Setter
@Getter
public class ActivityAwardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动ID")
    private Integer activityId;

    @ApiModelProperty(value = "奖励列表")
    private List<ActivityAwardItem> awards;
}
