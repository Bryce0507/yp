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
@ApiModel(value = "员工销售VO")
@Setter
@Getter
public class StaffSaleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "真实姓名")
    private String trueName;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "门店名")
    private String companyName;

    @ApiModelProperty(value = "已审核金额")
    private BigDecimal saleMoney;

    @ApiModelProperty(value = "已审核台数")
    private Integer quantity;
    @ApiModelProperty(value = "待审金额")
    private BigDecimal approveSaleMoney;

    @ApiModelProperty(value = "待审台数")
    private Integer approveQuantity;

    @ApiModelProperty(value = "单位：0 台  1 元")
    private String unit;

}
