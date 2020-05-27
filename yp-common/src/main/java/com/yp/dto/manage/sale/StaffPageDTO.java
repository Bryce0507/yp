package com.yp.dto.manage.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(value = "活动员工DTO")
@Setter
@Getter
public class StaffPageDTO {
    @ApiModelProperty(value = "活动ID")
    @NotNull
    private String activityId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String trueName;

    @ApiModelProperty(value = "产品名")
    private String productName;

    @ApiModelProperty(value = "业绩状态：0 待审核 1:通过 2：驳回")
    private String approveStatus;

    @ApiModelProperty(value = "销售时间")
    private String saleTime;

    @ApiModelProperty(value = "产品ID")
    private Integer productId;

    @ApiModelProperty(value = "显示条数")
    private int size=10;

    @ApiModelProperty(value = "当前页数")
    private int current=0;

}
