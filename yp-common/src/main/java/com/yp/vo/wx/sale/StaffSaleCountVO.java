package com.yp.vo.wx.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "门店员工业绩-视图")
public class StaffSaleCountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer userId;

    @ApiModelProperty(value = "姓名")
    private String trueName;

    @ApiModelProperty(value = "台数")
    private Integer quantity;

    @ApiModelProperty(value = "金额")
    private BigDecimal saleMoney;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }
}
