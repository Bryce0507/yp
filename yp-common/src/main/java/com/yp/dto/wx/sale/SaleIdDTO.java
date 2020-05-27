package com.yp.dto.wx.sale;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;

@ApiModel(value = "销售ID")
public class SaleIdDTO {

    /**
     * 销售ID
     */
    @NotNull
    private String saleId;

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }
}
