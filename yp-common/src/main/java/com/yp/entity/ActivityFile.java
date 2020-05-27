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
 * 活动附件
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "活动附件")
@TableName("activity_file")
public class ActivityFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "活动编号")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "活动名称")
    @TableField("file_name")
    private String fileName;

    @ApiModelProperty(value = "文件类型 0:文档  1:视频")
    @TableField("file_type")
    private String fileType;

    @ApiModelProperty(value = "文件后缀")
    @TableField("file_postfix")
    private String filePostfix;

    @ApiModelProperty(value = "文件地址")
    @TableField("file_address")
    private String fileAddress;

    @ApiModelProperty(value = "文件状态 0:无效 1：有效")
    @TableField("status")
    private String status;



    public Integer getPkid() {
        return pkid;
    }

    public ActivityFile setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public ActivityFile setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ActivityFile setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public ActivityFile setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public String getFilePostfix() {
        return filePostfix;
    }

    public ActivityFile setFilePostfix(String filePostfix) {
        this.filePostfix = filePostfix;
        return this;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public ActivityFile setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ActivityFile setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "ActivityFile{" +
			"pkid=" + pkid +
			", activityId=" + activityId +
			", fileName=" + fileName +
			", fileType=" + fileType +
			", filePostfix=" + filePostfix +
			", fileAddress=" + fileAddress +
			", status=" + status +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,activityId
			,fileName
			,fileType
			,filePostfix
			,fileAddress
			,status
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityFile that = (ActivityFile) o;
		return  Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityId, that.activityId)
			&& Objects.equals(fileName, that.fileName)
			&& Objects.equals(fileType, that.fileType)
			&& Objects.equals(filePostfix, that.filePostfix)
			&& Objects.equals(fileAddress, that.fileAddress)
			&& Objects.equals(status, that.status)
        ;
    }
	
}
