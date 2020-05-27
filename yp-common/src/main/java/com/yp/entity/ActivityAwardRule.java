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
 * 活动奖励规则
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动奖励规则")
@TableName("activity_award_rule")
public class ActivityAwardRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "奖励ID")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "活动ID")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "奖项名称")
    @TableField("award_name")
    private String awardName;

    @ApiModelProperty(value = "奖励")
    @TableField("award")
    private String award;

    @ApiModelProperty(value = "排名")
    @TableField("top")
    private Integer top;



    public Integer getPkid() {
        return pkid;
    }

    public ActivityAwardRule setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public ActivityAwardRule setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getAwardName() {
        return awardName;
    }

    public ActivityAwardRule setAwardName(String awardName) {
        this.awardName = awardName;
        return this;
    }

    public String getAward() {
        return award;
    }

    public ActivityAwardRule setAward(String award) {
        this.award = award;
        return this;
    }

    public Integer getTop() {
        return top;
    }

    public ActivityAwardRule setTop(Integer top) {
        this.top = top;
        return this;
    }

    @Override
    public String toString() {
        return "ActivityAwardRule{" +
			", pkid=" + pkid +
			", activityId=" + activityId +
			", awardName=" + awardName +
			", award=" + award +
			", top=" + top +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,activityId
			,awardName
			,award
			,top
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityAwardRule that = (ActivityAwardRule) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityId, that.activityId)
			&& Objects.equals(awardName, that.awardName)
			&& Objects.equals(award, that.award)
			&& Objects.equals(top, that.top)
        ;
    }
	
}
