package com.yp.vo.manage.activity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yp.vo.manage.org.DeptVO;
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
@ApiModel(value = "参与活的公司VO")
@Setter
@Getter
public class PartakeCompanyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer pkid;

    @ApiModelProperty(value = "公司名称")
    private String compayName;

    @ApiModelProperty(value = "上级ID")
    private Integer parentId;

    @ApiModelProperty(value = "上级名称")
    private String parentName;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "地址")
    private String area;

    @ApiModelProperty(value = "负责人")
    private String leader;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "目标")
    private Integer taget;
    @ApiModelProperty(value = "单位：0 台  1 元")
    private String unit;
}
