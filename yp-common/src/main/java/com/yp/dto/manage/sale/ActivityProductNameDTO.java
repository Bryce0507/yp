package com.yp.dto.manage.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(value = "活动产品名ID")
@Setter
@Getter
public class ActivityProductNameDTO {

    @ApiModelProperty(value = "活动ID")
    @NotNull
    private String activityId;

    @ApiModelProperty(value = "产品名称")
    private String productName;


}
