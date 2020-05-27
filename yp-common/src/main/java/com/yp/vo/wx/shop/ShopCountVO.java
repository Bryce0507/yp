package com.yp.vo.wx.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "所有门店业绩统计-视图")
public class ShopCountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前排名")
    private String top;

    @ApiModelProperty(value = "指标")
    private Integer target;

    @ApiModelProperty(value = "台数")
    private Integer quantity;

    @ApiModelProperty(value = "金额")
    private BigDecimal saleMoney;

    @ApiModelProperty(value = "排序方式 0：台数 1：金额")
    private String orderType;

    @ApiModelProperty(value = "是否店长 0：是 1：不是")
    private String isDirector;

    private List<ShopItemCount> shopItemCounts;


    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<ShopItemCount> getShopItemCounts() {
        return shopItemCounts;
    }

    public void setShopItemCounts(List<ShopItemCount> shopItemCounts) {
        this.shopItemCounts = shopItemCounts;
    }

    public String getIsDirector() {
        return isDirector;
    }

    public void setIsDirector(String isDirector) {
        this.isDirector = isDirector;
    }
}
