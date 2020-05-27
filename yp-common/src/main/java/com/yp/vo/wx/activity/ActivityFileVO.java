package com.yp.vo.wx.activity;

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
@ApiModel(value = "活动附件-视图")
@TableName("activity_file")
public class ActivityFileVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer pkid;

    @ApiModelProperty(value = "活动名称")
    @TableField("file_name")
    private String fileName;

    @ApiModelProperty(value = "文件类型 0:文档  1:视频")
    private String fileType;

    @ApiModelProperty(value = "文件后缀")
    private String filePostfix;

    @ApiModelProperty(value = "文件地址")
    private String fileAddress;




    public Integer getPkid() {
        return pkid;
    }

    public ActivityFileVO setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ActivityFileVO setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public ActivityFileVO setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public String getFilePostfix() {
        return filePostfix;
    }

    public ActivityFileVO setFilePostfix(String filePostfix) {
        this.filePostfix = filePostfix;
        return this;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public ActivityFileVO setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
        return this;
    }
}
