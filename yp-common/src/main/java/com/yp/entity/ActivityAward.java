package com.yp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 活动奖励
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动奖励")
@TableName("activity_award")
public class ActivityAward implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "奖励ID")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "活动ID")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "门店编号")
    @TableField("shop_code")
    private Integer shopCode;

    @ApiModelProperty(value = "奖项名称")
    @TableField("award_name")
    private String awardName;

    @ApiModelProperty(value = "奖励")
    @TableField("award")
    private String award;

    @ApiModelProperty(value = "活动业绩")
    @TableField("sale")
    private Integer sale;

    @ApiModelProperty(value = "业绩单位 0：台数 1：金额")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "排名")
    @TableField("top")
    private Integer top;



    public Integer getPkid() {
        return pkid;
    }

    public ActivityAward setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public ActivityAward setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public Integer getShopCode() {
        return shopCode;
    }

    public ActivityAward setShopCode(Integer shopCode) {
        this.shopCode = shopCode;
        return this;
    }

    public String getAwardName() {
        return awardName;
    }

    public ActivityAward setAwardName(String awardName) {
        this.awardName = awardName;
        return this;
    }

    public String getAward() {
        return award;
    }

    public ActivityAward setAward(String award) {
        this.award = award;
        return this;
    }

    public Integer getSale() {
        return sale;
    }

    public ActivityAward setSale(Integer sale) {
        this.sale = sale;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ActivityAward setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public Integer getTop() {
        return top;
    }

    public ActivityAward setTop(Integer top) {
        this.top = top;
        return this;
    }

    @Override
    public String toString() {
        return "ActivityAward{" +
			", pkid=" + pkid +
			", activityId=" + activityId +
			", shopCode=" + shopCode +
			", awardName=" + awardName +
			", award=" + award +
			", sale=" + sale +
			", unit=" + unit +
			", top=" + top +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,activityId
			,shopCode
			,awardName
			,award
			,sale
			,unit
			,top
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityAward that = (ActivityAward) o;
		return  Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityId, that.activityId)
			&& Objects.equals(shopCode, that.shopCode)
			&& Objects.equals(awardName, that.awardName)
			&& Objects.equals(award, that.award)
			&& Objects.equals(sale, that.sale)
			&& Objects.equals(unit, that.unit)
			&& Objects.equals(top, that.top)
        ;
    }
	
}
