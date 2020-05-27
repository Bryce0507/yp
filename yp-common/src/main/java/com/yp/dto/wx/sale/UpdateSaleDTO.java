package com.yp.dto.wx.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "修改业绩DTO")
public class UpdateSaleDTO {
    /**
     * 活动ID
     */
    @NotNull
    @ApiModelProperty(value = "业绩ID")
    private String saleId;

    @NotNull
    @ApiModelProperty(value = "产品Id")
    private Integer productId;

    @NotNull
    @ApiModelProperty(value = "产品名称")
    private String productNmae;

    @NotNull
    @ApiModelProperty(value = "产品金额")
    private String productPrice;


    @NotNull
    @ApiModelProperty(value = "车牌号码")
    private String carNumber;

    @NotNull
    @ApiModelProperty(value = "销售凭据")
    private List<String> files;

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductNmae() {
        return productNmae;
    }

    public void setProductNmae(String productNmae) {
        this.productNmae = productNmae;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
