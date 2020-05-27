package com.yp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.entity.SysDept;
import com.yp.dto.DeptDTO;
import com.yp.vo.DeptTreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
public interface ISysDeptService extends IService<SysDept> {


    /**
     * 根据部门id查询部门名称
     * @param deptId
     * @return
     */
    String selectDeptNameByDeptId(int deptId);

}
