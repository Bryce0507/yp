package com.yp.vo.wx.activity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动表-视图")
public class ActivityDetailsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer pkid;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "目标描述")
    private String activityDesc;

    @ApiModelProperty(value = "活动内容")
    private String activityContent;

    @ApiModelProperty(value = "活动banner")
    private String activityBanner;

    @ApiModelProperty(value = "分工安排")
    private String divisionPlan;

    @ApiModelProperty(value = "相关支持")
    private String relatedSupport;

    @ApiModelProperty(value = "附件")
    private List<ActivityFileVO> files;

    @ApiModelProperty(value = "是否参与活动：0：参与 1：未参与")
    private String isPartake;

    @ApiModelProperty(value = "活动状态 0:待发布 1:进行中  2：已结束")
    private String activityStatus;

    @ApiModelProperty(value = "奖励状态 0：未公布 1：已公布")
    private String rewardStatus;

    @ApiModelProperty(value = "当前时间")
    private Date crrentTime;

    @ApiModelProperty(value = "开始日期")
    private Date startTime;

    @ApiModelProperty(value = "结束日期")
    private Date endTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getActivityBanner() {
        return activityBanner;
    }

    public void setActivityBanner(String activityBanner) {
        this.activityBanner = activityBanner;
    }

    public String getDivisionPlan() {
        return divisionPlan;
    }

    public void setDivisionPlan(String divisionPlan) {
        this.divisionPlan = divisionPlan;
    }

    public String getRelatedSupport() {
        return relatedSupport;
    }

    public void setRelatedSupport(String relatedSupport) {
        this.relatedSupport = relatedSupport;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<ActivityFileVO> getFiles() {
        return files;
    }

    public void setFiles(List<ActivityFileVO> files) {
        this.files = files;
    }

    public String getIsPartake() {
        return isPartake;
    }

    public void setIsPartake(String isPartake) {
        this.isPartake = isPartake;
    }

    public Date getCrrentTime() {
        return crrentTime;
    }

    public void setCrrentTime(Date crrentTime) {
        this.crrentTime = crrentTime;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(String rewardStatus) {
        this.rewardStatus = rewardStatus;
    }
}
