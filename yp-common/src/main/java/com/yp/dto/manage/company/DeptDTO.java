package com.yp.dto.manage.company;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@ApiModel(value = "部门DTO")
@Setter
@Getter
public class DeptDTO extends Model<DeptDTO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer deptId;

    @ApiModelProperty(value = "公司id")
    @NotNull
    private Integer companyId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "上级部门")
    @NotNull
    private Integer parentId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
