package com.yp.vo.manage.org;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@ApiModel(value = "部门VO")
@Setter
@Getter
public class DeptVO extends Model<DeptVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer deptId;

    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "上级部门")
    private Integer parentId;

    @ApiModelProperty(value = "上级部门名称")
    private String parentName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "子部门")
    private List<DeptVO> children;

}
