package com.yp.dto.manage.company;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator
 */
@ApiModel(value = "公司DTO")
@Setter
@Getter
public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer pkid;

    @ApiModelProperty(value = "公司名称")
    private String compayName;

    @ApiModelProperty(value = "上级ID")
    @NotNull
    private Integer parentId;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty("地址")
    private String area;

    @ApiModelProperty(value = "负责人")
    private String leader;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "状态：0 生效 1：失效")
    private String status;

}
