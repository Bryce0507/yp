package com.yp.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 活动销售记录
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动销售记录")
@TableName("activity_sale_record")
public class ActivitySaleRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "活动编号")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "门店编号")
    @TableField("shop_code")
    private Integer shopCode;

    @ApiModelProperty(value = "门店名称")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "用户名称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "商品编号")
    @TableField("product_id")
    private Integer productId;

    @ApiModelProperty(value = "商品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    @TableField("product_price")
    private BigDecimal productPrice;

    @TableField("car_number")
    private String carNumber;

    @TableField("files")
    private String files;

    @ApiModelProperty(value = "0:未审核 1：通过 2：驳回")
    @TableField("approve_status")
    private String approveStatus;

    @TableField("overrule_reason")
    private String overruleReason;

    @ApiModelProperty(value = "销售时间")
    @TableField("sale_time")
    private Date saleTime;

    @ApiModelProperty(value = "销售单Id")
    @TableField("sale_order_id")
    private Integer saleOrderId;

    public Integer getPkid() {
        return pkid;
    }

    public ActivitySaleRecord setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public ActivitySaleRecord setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public Integer getShopCode() {
        return shopCode;
    }

    public ActivitySaleRecord setShopCode(Integer shopCode) {
        this.shopCode = shopCode;
        return this;
    }

    public String getShopName() {
        return shopName;
    }

    public ActivitySaleRecord setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public ActivitySaleRecord setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ActivitySaleRecord setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Integer getProductId() {
        return productId;
    }

    public ActivitySaleRecord setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ActivitySaleRecord setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public ActivitySaleRecord setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public ActivitySaleRecord setCarNumber(String carNumber) {
        this.carNumber = carNumber;
        return this;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public ActivitySaleRecord setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
        return this;
    }

    public String getOverruleReason() {
        return overruleReason;
    }

    public ActivitySaleRecord setOverruleReason(String overruleReason) {
        this.overruleReason = overruleReason;
        return this;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public ActivitySaleRecord setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
        return this;
    }

    public Integer getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Integer saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    @Override
    public String toString() {
        return "ActivitySaleRecord{" +
			", pkid=" + pkid +
			", activityId=" + activityId +
			", shopCode=" + shopCode +
			", shopName=" + shopName +
			", userId=" + userId +
			", userName=" + userName +
			", productId=" + productId +
			", productName=" + productName +
			", productPrice=" + productPrice +
			", carNumber=" + carNumber +
			", approveStatus=" + approveStatus +
			", overruleReason=" + overruleReason +
			", saleTime=" + saleTime +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(pkid
			,activityId
			,shopCode
			,shopName
			,userId
			,userName
			,productId
			,productName
			,productPrice
			,carNumber
			,approveStatus
			,overruleReason
			,saleTime
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitySaleRecord that = (ActivitySaleRecord) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityId, that.activityId)
			&& Objects.equals(shopCode, that.shopCode)
			&& Objects.equals(shopName, that.shopName)
			&& Objects.equals(userId, that.userId)
			&& Objects.equals(userName, that.userName)
			&& Objects.equals(productId, that.productId)
			&& Objects.equals(productName, that.productName)
			&& Objects.equals(productPrice, that.productPrice)
			&& Objects.equals(carNumber, that.carNumber)
			&& Objects.equals(approveStatus, that.approveStatus)
			&& Objects.equals(overruleReason, that.overruleReason)
			&& Objects.equals(saleTime, that.saleTime)
        ;
    }
	
}
