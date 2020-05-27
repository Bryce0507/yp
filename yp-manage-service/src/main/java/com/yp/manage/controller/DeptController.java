package com.yp.manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yp.common.utils.R;
import com.yp.dto.manage.company.DeptDTO;
import com.yp.entity.Company;
import com.yp.entity.SysDept;
import com.yp.entity.SysUser;
import com.yp.service.CompanyService;
import com.yp.service.ISysDeptService;
import com.yp.service.ISysUserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysUserService userService;
    @Autowired
    private CompanyService companyService;


    /**
     * 保存部门信息
     *
     * @param deptDTO
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('sys:dept:add')")
    public R save(@RequestBody DeptDTO deptDTO) {
        SysDept sysDept=new SysDept();
        BeanUtils.copyProperties(deptDTO,sysDept);
        return R.ok(deptService.save(sysDept));
    }

    /**
     * 更新部门信息
     *
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('sys:dept:update')")
    public R update(@RequestBody DeptDTO deptDto) {
        if(deptDto.getCompanyId()==null){
            return R.error("归属公司不能为空");
        }
        Company company=companyService.getById(deptDto.getCompanyId());
        if(company==null){
            return R.error("归属公司不存在");
        }
        SysDept sysDept=new SysDept();
        BeanUtils.copyProperties(deptDto,sysDept);
        sysDept.setUpdateTime(LocalDateTime.now());
        return R.ok(deptService.updateById(sysDept));
    }

    /**
     * 根据id删除部门信息
     *
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    public R delete(@PathVariable("id") Integer id) {
        List<SysDept> sysDeptList=deptService.list(new QueryWrapper<SysDept>().eq("parent_id",id));
        if(sysDeptList.size()>0){
            return R.error("部门存有子部门不能删除");
        }
        List userList=userService.list(new QueryWrapper<SysUser>().eq("dept_id",id));
        if(userList.size()>0){
            return R.error("部门存有员工不能删除");
        }
        return R.ok(deptService.removeById(id));
    }


}

