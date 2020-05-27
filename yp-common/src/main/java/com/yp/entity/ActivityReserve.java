package com.yp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * 活动库存
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动库存")
@TableName("activity_reserve")
public class ActivityReserve implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "活动编号")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "商品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty(value = "商品名称")
    @TableField("product_price")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "总数量")
    @TableField("total_number")
    private Integer totalNumber;

    @ApiModelProperty(value = "可用数量")
    @TableField("usable_number")
    private Integer usableNumber;



    public Integer getPkid() {
        return pkid;
    }

    public ActivityReserve setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public ActivityReserve setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ActivityReserve setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public ActivityReserve setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
        return this;
    }

    public Integer getUsableNumber() {
        return usableNumber;
    }

    public ActivityReserve setUsableNumber(Integer usableNumber) {
        this.usableNumber = usableNumber;
        return this;
    }

    @Override
    public String toString() {
        return "ActivityReserve{" +
			", pkid=" + pkid +
			", activityId=" + activityId +
			", productName=" + productName +
			", totalNumber=" + totalNumber +
			", usableNumber=" + usableNumber +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,activityId
			,productName
			,totalNumber
			,usableNumber
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityReserve that = (ActivityReserve) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityId, that.activityId)
			&& Objects.equals(productName, that.productName)
			&& Objects.equals(totalNumber, that.totalNumber)
			&& Objects.equals(usableNumber, that.usableNumber)
        ;
    }
	
}
