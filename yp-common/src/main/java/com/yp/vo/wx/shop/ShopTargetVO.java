package com.yp.vo.wx.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 门店指标
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "门店指标")
public class ShopTargetVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "目标")
    private Integer target;

    @ApiModelProperty(value = "0：台  1：元")
    private String unit;

    public Integer getTarget() {
        return target;
    }

    public ShopTargetVO setTarget(Integer target) {
        this.target = target;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ShopTargetVO setUnit(String unit) {
        this.unit = unit;
        return this;
    }
}
