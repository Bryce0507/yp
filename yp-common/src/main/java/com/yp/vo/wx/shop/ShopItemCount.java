package com.yp.vo.wx.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "门店业绩统计-对象")
public class ShopItemCount implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门店编号")
    private Integer shopCode;

    @ApiModelProperty(value = "门店名称")
    private String shopName;

    @ApiModelProperty(value = "台数")
    private Integer quantity;

    @ApiModelProperty(value = "金额")
    private BigDecimal saleMoney;


    public Integer getShopCode() {
        return shopCode;
    }

    public void setShopCode(Integer shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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
