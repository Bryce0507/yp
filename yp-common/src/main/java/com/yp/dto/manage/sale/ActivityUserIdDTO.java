package com.yp.dto.manage.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(value = "活动用户ID")
@Setter
@Getter
public class ActivityUserIdDTO {
    @ApiModelProperty(value = "活动ID")
    @NotNull
    private String activityId;

    @ApiModelProperty(value = "用户ID")
    @NotNull
    private String userId;

}
