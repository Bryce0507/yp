package com.yp.manage.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yp.common.utils.R;
import com.yp.dto.DeptDTO;
import com.yp.dto.manage.company.CompanyDTO;
import com.yp.entity.Company;
import com.yp.entity.ShopTarget;
import com.yp.entity.SysDept;
import com.yp.entity.SysUser;
import com.yp.service.CompanyService;
import com.yp.service.ISysDeptService;
import com.yp.service.ISysUserService;
import com.yp.service.ShopTargetService;
import com.yp.util.PreUtil;
import com.yp.vo.manage.org.CompanyVO;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 机构 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Api(tags = "机构")
@RestController
@RequestMapping("/org")
public class OrganizationController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ShopTargetService targetService;

    /**
     * 添加公司
     * @param companyDTO
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('sys:org:add')")
    public R save(@RequestBody CompanyDTO companyDTO) {
        Company company=new Company();
        BeanUtils.copyProperties(companyDTO,company);
        return R.ok(companyService.save(company));
    }

    /**
     * 获取机构树
     * @return
     */
    @GetMapping("/orgtree")
    public R<List<CompanyVO>> getOrgTree() {
        //获取所有部门
        List<SysDept> depts = deptService.list(new QueryWrapper<>());
        //获取所有公司
        List<Company> companyList = companyService.list(new QueryWrapper<>());
        List<CompanyVO> collect = companyList.stream().filter(company -> company.getParentId() == 0 || ObjectUtil.isNull(company.getParentId()))
                .map(company -> {
                    CompanyVO companyVO = new CompanyVO();
                    companyVO.setPkid(company.getPkid());
                    companyVO.setCompayName(company.getCompayName());
                    companyVO.setCity(company.getCity());
                    companyVO.setArea(company.getArea());
                    companyVO.setLeader(company.getLeader());
                    companyVO.setMobile(company.getMobile());
                    companyVO.setParentId(company.getParentId());
                    return companyVO;
                }).collect(Collectors.toList());

        PreUtil.findOrgChildren(collect,companyList,depts);
        return R.ok(collect);
    }


    /**
     * 更新机构信息
     *
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('sys:org:update')")
    public R update(@RequestBody CompanyDTO companyDTO) {
        Company company=new Company();
        BeanUtils.copyProperties(companyDTO,company);
        return R.ok(companyService.updateById(company));
    }

    /**
     * 根据id删除公司信息
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:org:delete')")
    public R delete(@PathVariable("id") Integer id) {
        //删除公司及下属公司参与活动的名单
        List<Company> companyList = companyService.list(new QueryWrapper<Company>().eq("parent_id",id));
        if(companyList.size()>0){
            return R.error("公司存有子公司不能删除");
        }
        List deptList=deptService.list(new QueryWrapper<SysDept>().eq("company_id",id));
        if(deptList.size()>0){
            return R.error("公司存有部门不能删除");
        }
        List userList=userService.list(new QueryWrapper<SysUser>().eq("company_id",id));
        if(userList.size()>0){
            return R.error("公司存有员工不能删除");
        }
        List shopTargetList=targetService.list(new QueryWrapper<ShopTarget>().eq("shop_code",id));
        if(shopTargetList.size()>0){
            return R.error("公司正在参与活动或者已参与活动不能删除");
        }
        return R.ok(companyService.removeById(id));
    }
}

