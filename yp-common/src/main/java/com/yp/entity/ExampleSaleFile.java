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
@ApiModel(value = "活动示例图")
@TableName("example_sale_file")
public class ExampleSaleFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "活动编号")
    @TableField("activity_id")
    private Integer activityId;

    @ApiModelProperty(value = "示例描述")
    @TableField("example_des")
    private String exampleDes;

    @ApiModelProperty(value = "文件地址")
    @TableField("file_address")
    private String fileAddress;

    @ApiModelProperty(value = "文件状态 0：有效  1：无效")
    @TableField("status")
    private String status;



    public Integer getPkid() {
        return pkid;
    }

    public ExampleSaleFile setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public ExampleSaleFile setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getExampleDes() {
        return exampleDes;
    }

    public ExampleSaleFile setExampleDes(String exampleDes) {
        this.exampleDes = exampleDes;
        return this;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public ExampleSaleFile setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ExampleSaleFile setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "ExampleSaleFile{" +
			", pkid=" + pkid +
			", activityId=" + activityId +
			", exampleDes=" + exampleDes +
			", fileAddress=" + fileAddress +
			", status=" + status +
        "}";
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(
			pkid
			,activityId
			,exampleDes
			,fileAddress
			,status
        );
    }
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExampleSaleFile that = (ExampleSaleFile) o;
		return  Objects.equals(pkid, that.pkid)
			&& Objects.equals(activityId, that.activityId)
			&& Objects.equals(exampleDes, that.exampleDes)
			&& Objects.equals(fileAddress, that.fileAddress)
			&& Objects.equals(status, that.status)
        ;
    }
	
}
