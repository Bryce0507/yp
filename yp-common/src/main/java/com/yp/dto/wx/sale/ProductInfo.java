package com.yp.dto.wx.sale;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author zxb
 * @date 2020/5/25 10:04
 */
public class ProductInfo {
    @NotNull
    @ApiModelProperty(value = "产品Id")
    private Integer productId;

    @NotNull
    @ApiModelProperty(value = "产品名称")
    private String productName;

    @NotNull
    @ApiModelProperty(value = "产品金额")
    private String productPrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
