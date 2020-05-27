package com.yp.dto.manage.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(value = "活动门店ID")
@Setter
@Getter
public class ActivityShopDTO {
    /**
     * 活动ID
     */
    @ApiModelProperty(value = "活动ID")
    @NotNull
    private String activityId;

    @ApiModelProperty(value = "门店ID")
    @NotNull
    private String companyId;

}
