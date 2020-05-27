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
 * 公告
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "公告")
@TableName("notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告ID")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "1：系统公告 2：业绩通过 3：业绩驳回")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "公告内容")
    @TableField("context")
    private String context;

    @ApiModelProperty(value = "是否置顶")
    @TableField("is_top")
    private String isTop;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "是否展示 ：0：不展示 1：展示")
    @TableField("is_display")
    private String isDisplay;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPkid() {
        return pkid;
    }

    public Notice setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Notice setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getContext() {
        return context;
    }

    public Notice setContext(String context) {
        this.context = context;
        return this;
    }

    public String getIsTop() {
        return isTop;
    }

    public Notice setIsTop(String isTop) {
        this.isTop = isTop;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Notice setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    @Override
    public String toString() {
        return "Notice{" +
			", pkid=" + pkid +
			", type=" + type +
			", context=" + context +
			", isTop=" + isTop +
			", createTime=" + createTime +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,type
			,context
			,isTop
			,createTime
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice that = (Notice) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(type, that.type)
			&& Objects.equals(context, that.context)
			&& Objects.equals(isTop, that.isTop)
			&& Objects.equals(createTime, that.createTime)
        ;
    }
	
}
