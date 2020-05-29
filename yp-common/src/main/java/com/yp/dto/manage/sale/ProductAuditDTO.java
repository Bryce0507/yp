package com.yp.dto.manage.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zxb
 * @date 2020/5/29 9:32
 */
@Data
@ApiModel(value = "商品审核情况")
public class ProductAuditDTO {

    @ApiModelProperty(value = "商品销售记录ID")
    @NotNull
    private Integer saleRecordId;

    @ApiModelProperty(value = "审核情况  1-通过  2-驳回")
    @NotNull
    private Integer approveStatus;

    @ApiModelProperty(value = "驳回原因")
    private String overruleReason;
}
