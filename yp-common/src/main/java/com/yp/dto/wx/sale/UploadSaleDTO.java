package com.yp.dto.wx.sale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "上传业绩DTO")
public class UploadSaleDTO {


    @NotNull
    @ApiModelProperty(value = "活动Id")
    private Integer activityId;

    @NotNull
    @ApiModelProperty(value = "商品信息")
    private List<ProductInfo> productInfoList;

    @NotNull
    @ApiModelProperty(value = "车牌号码")
    private String carNumber;

    @NotNull
    @ApiModelProperty(value = "销售凭据")
    private List<String> files;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> productInfoList) {
        this.productInfoList = productInfoList;
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
