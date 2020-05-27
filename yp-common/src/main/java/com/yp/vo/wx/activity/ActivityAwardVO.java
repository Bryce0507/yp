package com.yp.vo.wx.activity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动奖励-视图")
public class ActivityAwardVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门店编号")
    private Integer shopCode;

    @ApiModelProperty(value = "门店名称")
    private String shopName;

    @ApiModelProperty(value = "奖项名称")
    private String awardName;

    @ApiModelProperty(value = "奖励")
    private String award;

    @ApiModelProperty(value = "活动业绩")
    private Integer sale;

    @ApiModelProperty(value = "业绩单位 0：台数 1：金额")
    private String unit;

    @ApiModelProperty(value = "排名")
    private Integer top;


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

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
}
