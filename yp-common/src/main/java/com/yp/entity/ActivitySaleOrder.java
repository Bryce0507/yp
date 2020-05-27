package com.yp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 活动销售记录
 * </p>
 *
 * @author bryce0507
 */
@ApiModel(value = "活动销售订单记录")
@TableName("activity_sale_order")
public class ActivitySaleOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "销售单编号")
    @TableId(value = "sale_order_code")
    private String saleOrderCode;

    @ApiModelProperty(value = "活动编号")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "门店编号")
    @TableField("shop_code")
    private Integer shopCode;

    @ApiModelProperty(value = "销售单价格")
    @TableField("sale_order_price")
    private BigDecimal saleOrderPrice;

    @ApiModelProperty(value = "门店名称")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "用户名称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "车牌号")
    @TableField("car_number")
    private String carNumber;

    @ApiModelProperty(value = "销售单状态 0:审核中 1：通过 2：部分通过 3：已驳回")
    @TableField("sale_order_status")
    private Integer saleOrderStatus;

    @ApiModelProperty(value = "销售凭证")
    @TableField("files")
    private String files;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "销售时间")
    @TableField("sale_time")
    private Date saleTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getSaleOrderCode() {
        return saleOrderCode;
    }

    public void setSaleOrderCode(String saleOrderCode) {
        this.saleOrderCode = saleOrderCode;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getShopCode() {
        return shopCode;
    }

    public void setShopCode(Integer shopCode) {
        this.shopCode = shopCode;
    }

    public BigDecimal getSaleOrderPrice() {
        return saleOrderPrice;
    }

    public void setSaleOrderPrice(BigDecimal saleOrderPrice) {
        this.saleOrderPrice = saleOrderPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getSaleOrderStatus() {
        return saleOrderStatus;
    }

    public void setSaleOrderStatus(Integer saleOrderStatus) {
        this.saleOrderStatus = saleOrderStatus;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

}
