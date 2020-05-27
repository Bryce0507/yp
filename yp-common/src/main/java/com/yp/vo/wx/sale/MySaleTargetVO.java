package com.yp.vo.wx.sale;

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
@ApiModel(value = "我的业绩指标-视图")
public class MySaleTargetVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位：0：台数 1：金额")
    private String unit;

    @ApiModelProperty(value = "指标")
    private Integer target;


    @ApiModelProperty(value = "百分比")
    private int  percentage;


    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
