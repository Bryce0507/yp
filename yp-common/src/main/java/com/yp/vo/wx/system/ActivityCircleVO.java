package com.yp.vo.wx.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 活动圈子
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动圈子消息-视图")
public class ActivityCircleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "门店名")
    private String shopName;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "用户名称")
    private String trueName;

    @ApiModelProperty(value = "业绩编号")
    private List<String> files;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "汽车牌")
    private String carNumber;


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
