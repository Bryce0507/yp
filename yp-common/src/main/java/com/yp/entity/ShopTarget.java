package com.yp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * <p>
 * 门店指标
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "门店指标")
@TableName("shop_target")
public class ShopTarget implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "活动编号")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "门店编号")
    @TableField("shop_code")
    private String shopCode;

    @ApiModelProperty(value = "目标")
    @TableField("target")
    private Integer target;

    @ApiModelProperty(value = "0：台  1：元")
    @TableField("unit")
    private String unit;



    public Integer getPkid() {
        return pkid;
    }

    public ShopTarget setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public ShopTarget setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getShopCode() {
        return shopCode;
    }

    public ShopTarget setShopCode(String shopCode) {
        this.shopCode = shopCode;
        return this;
    }

    public Integer getTarget() {
        return target;
    }

    public ShopTarget setTarget(Integer target) {
        this.target = target;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ShopTarget setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    @Override
    public String toString() {
        return "ShopTarget{" +
			", pkid=" + pkid +
			", activityId=" + activityId +
			", shopCode=" + shopCode +
			", target=" + target +
			", unit=" + unit +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,activityId
			,shopCode
			,target
			,unit
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopTarget that = (ShopTarget) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityId, that.activityId)
			&& Objects.equals(shopCode, that.shopCode)
			&& Objects.equals(target, that.target)
			&& Objects.equals(unit, that.unit)
        ;
    }
	
}
