package com.yp.dto.wx.shop;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yp.entity.UserTarget;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 员工指标
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "设置员工指标DTO")
public class SetUserTargetDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动编号")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "店员指标")
    List<UserTargetItem>  userTargetItems;


    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public List<UserTargetItem> getUserTargetItems() {
        return userTargetItems;
    }

    public void setUserTargetItems(List<UserTargetItem> userTargetItems) {
        this.userTargetItems = userTargetItems;
    }
}
