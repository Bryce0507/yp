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
 * 活动圈子已读
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动圈子已读")
@TableName("activity_circle_ready")
public class ActivityCircleReady implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "圈子消息ID")
    @TableField("circle_id")
    private Integer circleId;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;



    public Integer getPkid() {
        return pkid;
    }

    public ActivityCircleReady setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getCircleId() {
        return circleId;
    }

    public ActivityCircleReady setCircleId(Integer circleId) {
        this.circleId = circleId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public ActivityCircleReady setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ActivityCircleReady setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "ActivityCircleReady{" +
			", pkid=" + pkid +
			", circleId=" + circleId +
			", userId=" + userId +
			", createTime=" + createTime +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,circleId
			,userId
			,createTime
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityCircleReady that = (ActivityCircleReady) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(circleId, that.circleId)
			&& Objects.equals(userId, that.userId)
			&& Objects.equals(createTime, that.createTime)
        ;
    }
	
}
