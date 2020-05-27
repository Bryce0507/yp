package com.yp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 活动圈子
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动圈子")
@TableName("activity_circle")
public class ActivityCircle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "圈子消息ID")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "活动ID")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "业绩编号")
    @TableField("sale_id")
    private Integer saleId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;



    public Integer getPkid() {
        return pkid;
    }

    public ActivityCircle setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public ActivityCircle setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public ActivityCircle setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public ActivityCircle setSaleId(Integer saleId) {
        this.saleId = saleId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ActivityCircle setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "ActivityCircle{" +
			", pkid=" + pkid +
			", activityId=" + activityId +
			", userId=" + userId +
			", saleId=" + saleId +
			", createTime=" + createTime +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,activityId
			,userId
			,saleId
			,createTime
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityCircle that = (ActivityCircle) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityId, that.activityId)
			&& Objects.equals(userId, that.userId)
			&& Objects.equals(saleId, that.saleId)
			&& Objects.equals(createTime, that.createTime)
        ;
    }
	
}
