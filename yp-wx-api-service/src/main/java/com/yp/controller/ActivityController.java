package com.yp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yp.common.utils.R;
import com.yp.dto.wx.activity.ActivityIdDTO;
import com.yp.dto.wx.activity.QueryActivityListDTO;
import com.yp.entity.*;
import com.yp.service.*;
import com.yp.util.SecurityUtil;
import com.yp.vo.PageBean;
import com.yp.vo.wx.activity.ActivityAwardVO;
import com.yp.vo.wx.activity.ActivityDetailsVO;
import com.yp.vo.wx.activity.ActivityFileVO;
import com.yp.vo.wx.activity.ActivityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动表 模块
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Api(tags = "活动")
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityReserveService reserveService;

    @Autowired
    private ShopTargetService shopTargetService;

    @Autowired
    private ActivityFileService activityFileService;

    @Autowired
    private ActivityAwardService awardService;

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "获取活动列表")
    @PostMapping("/list")
    public R<PageBean<ActivityVO>> getActivityList(@RequestBody QueryActivityListDTO queryActivityListDTO) {
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        Page<Activity> page=new Page(queryActivityListDTO.getCurrent(),queryActivityListDTO.getSize());
        page= activityService.page(page,new QueryWrapper<Activity>().eq("activity_status",queryActivityListDTO.getActivityStatus()));
        PageBean<ActivityVO> resultPage=new PageBean<ActivityVO>();
        List<ActivityVO> list=new ArrayList<>();
        for (Activity activity: page.getRecords()) {
            if(activity.getIsPublic().equals("0")){
                //公开的
                ActivityVO activityVO=new ActivityVO();
                BeanUtils.copyProperties(activity,activityVO);
                list.add(activityVO);
            }else{
                //未公开
                ShopTarget shopTarget= shopTargetService.getOne(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()).eq("shop_code",user.getCompanyId()));
                if(shopTarget!=null){
                    ActivityVO activityVO=new ActivityVO();
                    BeanUtils.copyProperties(activity,activityVO);
                    list.add(activityVO);
                }
            }
        }
        resultPage.setRecords(list);
        resultPage.setCurrent(page.getCurrent());
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return R.ok(resultPage);
    }


    @ApiOperation(value = "获取活动详情")
    @PostMapping("/details")
    public R<ActivityDetailsVO> detail(@RequestBody ActivityIdDTO activityIdDTO) {
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
        ActivityDetailsVO activityVO=new ActivityDetailsVO();
        BeanUtils.copyProperties(activity,activityVO);
        //获取附件
        List<ActivityFile>  activityFiles= activityFileService.list(new QueryWrapper<ActivityFile>().eq("activity_id",activityIdDTO.getActivityId()).eq("status","0"));
        List<ActivityFileVO> activityFileVOS=new ArrayList<>();
        for (ActivityFile activityFile: activityFiles) {
            ActivityFileVO fileVO=new ActivityFileVO();
            BeanUtils.copyProperties(activityFile,fileVO);
            activityFileVOS.add(fileVO);
        }
        activityVO.setFiles(activityFileVOS);
        //设置是否参与活动
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        ShopTarget shopTarget=shopTargetService.getOne(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()).eq("shop_code",user.getCompanyId()));
        if(shopTarget==null){
            activityVO.setIsPartake("1");
        }else{
            activityVO.setIsPartake("0");
        }
        activityVO.setCrrentTime(new Date());
        return R.ok(activityVO);
    }


    @ApiOperation(value = "获取活动库存")
    @PostMapping("/productReserve")
    public R< List<ActivityReserve>> productReserve(@RequestBody ActivityIdDTO activityIdDTO) {
        List<ActivityReserve> reserves=reserveService.list(new QueryWrapper<ActivityReserve>().eq("activity_id",activityIdDTO.getActivityId()));
        return R.ok(reserves);
    }


    @ApiOperation(value = "获取活动奖励")
    @PostMapping("/queryAward")
    public R<List<ActivityAwardVO>> queryAward(@RequestBody ActivityIdDTO activityIdDTO) {
        List<ActivityAward> awards=awardService.list(new QueryWrapper<ActivityAward>().eq("activity_id",activityIdDTO.getActivityId()).orderByAsc("top"));
        List<ActivityAwardVO> awardVOS=new ArrayList<>();
        for (ActivityAward award:awards) {
            ActivityAwardVO activityAwardVO=new ActivityAwardVO();
            BeanUtils.copyProperties(award,activityAwardVO);
            Company company=companyService.getById(award.getShopCode());
            if(company!=null){
                activityAwardVO.setShopName(company.getCompayName());
            }
            awardVOS.add(activityAwardVO);
        }
        return R.ok(awardVOS);
    }
}

