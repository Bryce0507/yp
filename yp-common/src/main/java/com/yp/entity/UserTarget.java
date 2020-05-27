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
 * 员工指标
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "员工指标")
@TableName("user_target")
public class UserTarget implements Serializable {

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

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "目标")
    @TableField("target")
    private Integer target;

    @ApiModelProperty(value = "0：台  1：元")
    @TableField("unit")
    private String unit;



    public Integer getPkid() {
        return pkid;
    }

    public UserTarget setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public UserTarget setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getShopCode() {
        return shopCode;
    }

    public UserTarget setShopCode(String shopCode) {
        this.shopCode = shopCode;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserTarget setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getTarget() {
        return target;
    }

    public UserTarget setTarget(Integer target) {
        this.target = target;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public UserTarget setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    @Override
    public String toString() {
        return "UserTarget{" +
			", pkid=" + pkid +
			", activityId=" + activityId +
			", shopCode=" + shopCode +
			", userId=" + userId +
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
			,userId
			,target
			,unit
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTarget that = (UserTarget) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityId, that.activityId)
			&& Objects.equals(shopCode, that.shopCode)
			&& Objects.equals(userId, that.userId)
			&& Objects.equals(target, that.target)
			&& Objects.equals(unit, that.unit)
        ;
    }
	
}
