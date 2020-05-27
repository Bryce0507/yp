package com.yp.vo.wx.activity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动表-视图")
public class ActivityVO implements Serializable {

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

}
