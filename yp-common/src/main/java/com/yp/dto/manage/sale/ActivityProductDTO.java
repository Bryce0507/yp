package com.yp.dto.manage.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(value = "活动产品ID")
@Setter
@Getter
public class ActivityProductDTO {
    @ApiModelProperty(value = "活动ID")
    @NotNull
    private String activityId;

    @ApiModelProperty(value = "产品ID")
    @NotNull
    private String productId;

}
