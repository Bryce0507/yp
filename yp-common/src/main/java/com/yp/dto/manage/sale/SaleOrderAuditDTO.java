package com.yp.dto.manage.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "销售单业绩审核")
@Data
public class SaleOrderAuditDTO {

    @ApiModelProperty(value = "销售订单ID")
    @NotNull
    private Integer saleOrderId;


    @ApiModelProperty(value = "商品审核情况")
    @NotNull
    private List<ProductAuditDTO> productAuditDTOList;






}
