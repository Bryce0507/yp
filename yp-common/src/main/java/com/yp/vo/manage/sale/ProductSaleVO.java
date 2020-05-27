package com.yp.vo.manage.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "产品销售VO")
@Setter
@Getter
public class ProductSaleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品Id")
    private String productId;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "单位：0 台  1 元")
    private String unit;

    @ApiModelProperty(value = "金额")
    private BigDecimal saleMoney;

    @ApiModelProperty(value = "台数")
    private Integer quantity;

}
