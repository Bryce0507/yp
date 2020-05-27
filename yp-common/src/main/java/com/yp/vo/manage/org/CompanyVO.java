package com.yp.vo.manage.org;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "公司VO")
@Setter
@Getter
public class CompanyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer pkid;

    @ApiModelProperty(value = "公司名称")
    private String compayName;

    @ApiModelProperty(value = "上级ID")
    private Integer parentId;

    @ApiModelProperty(value = "上级名称")
    private String parentName;

    @TableField("city")
    private String city;

    @TableField("area")
    private String area;

    @TableField("leader")
    private String leader;

    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "子公司")
    private List<CompanyVO> children;

    @ApiModelProperty(value = "部门")
    private List<DeptVO> depts;


}
