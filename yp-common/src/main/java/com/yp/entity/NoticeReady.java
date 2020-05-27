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
 * 公告已读
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "公告已读")
@TableName("notice_ready")
public class NoticeReady implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "公告ID")
    @TableField("notice_id")
    private Integer noticeId;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;



    public Integer getPkid() {
        return pkid;
    }

    public NoticeReady setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public NoticeReady setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public NoticeReady setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public NoticeReady setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "NoticeReady{" +
			", pkid=" + pkid +
			", noticeId=" + noticeId +
			", userId=" + userId +
			", createTime=" + createTime +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,noticeId
			,userId
			,createTime
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeReady that = (NoticeReady) o;
		return Objects.equals(pkid, that.pkid)
			&& Objects.equals(noticeId, that.noticeId)
			&& Objects.equals(userId, that.userId)
			&& Objects.equals(createTime, that.createTime)
        ;
    }
	
}
