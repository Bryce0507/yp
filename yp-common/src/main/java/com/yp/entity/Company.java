package com.yp.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "公司")
@TableName("company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pkid", type = IdType.AUTO)
    private Integer pkid;

    @ApiModelProperty(value = "公司名称")
    @TableField("compay_name")
    private String compayName;

    @ApiModelProperty(value = "上级ID")
    @TableField("parent_id")
    private Integer parentId;

    @TableField("city")
    private String city;

    @TableField("area")
    private String area;

    @TableField("leader")
    private String leader;

    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "状态：0 生效 1：失效")
    @TableField("status")
    private String status;



    public Integer getPkid() {
        return pkid;
    }

    public Company setPkid(Integer pkid) {
        this.pkid = pkid;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Company setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getCompayName() {
        return compayName;
    }

    public Company setCompayName(String compayName) {
        this.compayName = compayName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Company setCity(String city) {
        this.city = city;
        return this;
    }

    public String getArea() {
        return area;
    }

    public Company setArea(String area) {
        this.area = area;
        return this;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getMobile() {
        return mobile;
    }

    public Company setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Company setStatus(String status) {
        this.status = status;
        return this;
    }
	
}
