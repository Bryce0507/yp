package com.yp.dto.manage.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(value = "业绩审核驳回")
@Setter
@Getter
public class SaleRefundDTO {

    @ApiModelProperty(value = "活动ID")
    @NotNull
    private String saleId;

    @ApiModelProperty(value = "原因")
    @NotNull
    private String overruleReason;


}
