package com.yp.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动表")
@TableName("activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "活动名称")
    @TableField("activity_name")
    private String activityName;

    @ApiModelProperty(value = "目标描述")
    @TableField("activity_desc")
    private String activityDesc;

    @ApiModelProperty(value = "活动内容")
    @TableField("activity_content")
    private String activityContent;

    @ApiModelProperty(value = "活动banner")
    @TableField("activity_banner")
    private String activityBanner;

    @ApiModelProperty(value = "是否设置库存 0:否 1：是")
    @TableField("is_set_reserve")
    private String isSetReserve;

    @ApiModelProperty(value = "设置指标  0:否 1：是")
    @TableField("is_set_target")
    private String isSetTarget;

    @ApiModelProperty(value = "指标数量")
    @TableField("target_number")
    private Integer targetNumber;

    @ApiModelProperty(value = "指标单位 0:台 1：元")
    @TableField("target_until")
    private String targetUntil;

    @ApiModelProperty(value = "设置门店指标  0:否 1：是")
    @TableField("is_set_shop_target")
    private String isSetShopTarget;

    @ApiModelProperty(value = "是否公开")
    @TableField("is_public")
    private String isPublic;

    @ApiModelProperty(value = "分工安排")
    @TableField("division_plan")
    private String divisionPlan;

    @ApiModelProperty(value = "相关支持")
    @TableField("related_support")
    private String relatedSupport;

    @ApiModelProperty(value = "活动状态 0:待发布 1:进行中  2：已结束")
    @TableField("activity_status")
    private String activityStatus;

    @ApiModelProperty(value = "奖励状态 0：未公布 1：已公布")
    @TableField("reward_status")
    private String rewardStatus;

    @ApiModelProperty(value = "奖励方案类型： 0：人工 1：自动")
    @TableField("reward_plan_type")
    private String rewardPlanType;

    @ApiModelProperty(value = "奖励方案")
    @TableField("reward_plan")
    private String rewardPlan;

    @ApiModelProperty(value = "开始日期")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty(value = "结束日期")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private String createUser;



    public Integer getPkid() {
        return pkid;
    }

    public Activity setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public String getActivityName() {
        return activityName;
    }

    public Activity setActivityName(String activityName) {
        this.activityName = activityName;
        return this;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public Activity setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
        return this;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public Activity setActivityContent(String activityContent) {
        this.activityContent = activityContent;
        return this;
    }

    public String getActivityBanner() {
        return activityBanner;
    }

    public Activity setActivityBanner(String activityBanner) {
        this.activityBanner = activityBanner;
        return this;
    }

    public String getIsSetReserve() {
        return isSetReserve;
    }

    public Activity setIsSetReserve(String isSetReserve) {
        this.isSetReserve = isSetReserve;
        return this;
    }

    public String getIsSetTarget() {
        return isSetTarget;
    }

    public Activity setIsSetTarget(String isSetTarget) {
        this.isSetTarget = isSetTarget;
        return this;
    }

    public Integer getTargetNumber() {
        return targetNumber;
    }

    public Activity setTargetNumber(Integer targetNumber) {
        this.targetNumber = targetNumber;
        return this;
    }

    public String getTargetUntil() {
        return targetUntil;
    }

    public Activity setTargetUntil(String targetUntil) {
        this.targetUntil = targetUntil;
        return this;
    }

    public String getIsSetShopTarget() {
        return isSetShopTarget;
    }

    public Activity setIsSetShopTarget(String isSetShopTarget) {
        this.isSetShopTarget = isSetShopTarget;
        return this;
    }

    public String getDivisionPlan() {
        return divisionPlan;
    }

    public Activity setDivisionPlan(String divisionPlan) {
        this.divisionPlan = divisionPlan;
        return this;
    }

    public String getRelatedSupport() {
        return relatedSupport;
    }

    public Activity setRelatedSupport(String relatedSupport) {
        this.relatedSupport = relatedSupport;
        return this;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public Activity setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
        return this;
    }

    public String getRewardStatus() {
        return rewardStatus;
    }

    public Activity setRewardStatus(String rewardStatus) {
        this.rewardStatus = rewardStatus;
        return this;
    }

    public String getRewardPlanType() {
        return rewardPlanType;
    }

    public Activity setRewardPlanType(String rewardPlanType) {
        this.rewardPlanType = rewardPlanType;
        return this;
    }

    public String getRewardPlan() {
        return rewardPlan;
    }

    public Activity setRewardPlan(String rewardPlan) {
        this.rewardPlan = rewardPlan;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Activity setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Activity setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Activity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public Activity setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        return "Activity{" +
			", pkid=" + pkid +
			", activityName=" + activityName +
			", activityDesc=" + activityDesc +
			", activityContent=" + activityContent +
			", activityBanner=" + activityBanner +
			", isSetReserve=" + isSetReserve +
			", isSetTarget=" + isSetTarget +
			", targetNumber=" + targetNumber +
			", targetUntil=" + targetUntil +
			", isSetShopTarget=" + isSetShopTarget +
			", divisionPlan=" + divisionPlan +
			", relatedSupport=" + relatedSupport +
			", activityStatus=" + activityStatus +
			", rewardStatus=" + rewardStatus +
			", rewardPlanType=" + rewardPlanType +
			", rewardPlan=" + rewardPlan +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", createTime=" + createTime +
			", createUser=" + createUser +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,activityName
			,activityDesc
			,activityContent
			,activityBanner
			,isSetReserve
			,isSetTarget
			,targetNumber
			,targetUntil
			,isSetShopTarget
			,divisionPlan
			,relatedSupport
			,activityStatus
			,rewardStatus
			,rewardPlanType
			,rewardPlan
			,startTime
			,endTime
			,createTime
			,createUser
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity that = (Activity) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityName, that.activityName)
			&& Objects.equals(activityDesc, that.activityDesc)
			&& Objects.equals(activityContent, that.activityContent)
			&& Objects.equals(activityBanner, that.activityBanner)
			&& Objects.equals(isSetReserve, that.isSetReserve)
			&& Objects.equals(isSetTarget, that.isSetTarget)
			&& Objects.equals(targetNumber, that.targetNumber)
			&& Objects.equals(targetUntil, that.targetUntil)
			&& Objects.equals(isSetShopTarget, that.isSetShopTarget)
			&& Objects.equals(divisionPlan, that.divisionPlan)
			&& Objects.equals(relatedSupport, that.relatedSupport)
			&& Objects.equals(activityStatus, that.activityStatus)
			&& Objects.equals(rewardStatus, that.rewardStatus)
			&& Objects.equals(rewardPlanType, that.rewardPlanType)
			&& Objects.equals(rewardPlan, that.rewardPlan)
			&& Objects.equals(startTime, that.startTime)
			&& Objects.equals(endTime, that.endTime)
			&& Objects.equals(createTime, that.createTime)
			&& Objects.equals(createUser, that.createUser)
        ;
    }
	
}
