package com.yp.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yp.common.utils.R;
import com.yp.dto.manage.activity.ActivityDTO;
import com.yp.dto.manage.activity.ActivityPageDTO;
import com.yp.dto.manage.activity.ActivityReserveItem;
import com.yp.dto.manage.activity.ShopTargetItem;
import com.yp.dto.wx.activity.ActivityIdDTO;
import com.yp.entity.*;
import com.yp.manage.util.wx.MessageSendUtil;
import com.yp.manage.util.wx.dto.WxActivityNoticeDTO;
import com.yp.service.*;
import com.yp.vo.PageBean;
import com.yp.vo.manage.activity.ActivityDetailVO;
import com.yp.vo.manage.activity.ActivityPageVO;
import com.yp.vo.manage.activity.PartakeCompanyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname IndexController
 * @Description 活动模块
 * @Author HCS lihaodongmail@163.com
 * @Date 2019-05-07 12:38
 * @Version 1.0
 */
@Api(tags = "活动模块")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ShopTargetService shopTargetService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ActivitySaleRecordService saleRecordService;

    @Autowired
    private ActivityReserveService reserveService;


    @Autowired
    private ISysUserService userService;

    /**
     * 添加活动
     * @param activityDTO
     * @return
     */
    @ApiOperation(value = "添加活动")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:activity:add')")
    public R insert(@RequestBody ActivityDTO activityDTO) {
        //商品价格校验
        for (ActivityReserveItem reserve: activityDTO.getReserves()) {
            if(reserve.getProductPrice().compareTo(new BigDecimal(0))!=1){
                return R.error("库存价格不能小于0");
            }
        }
        //库存校验
        if(activityDTO.getIsSetReserve().equals("1")){
            for (ActivityReserveItem reserve: activityDTO.getReserves()) {
                if(reserve.getTotalNumber()==null||!(reserve.getTotalNumber()>0)){
                    return R.error("设置库存是:商品数量不能小于0");
                }
            }
        }
        //参与店铺校验
        if(activityDTO.getShopTargets()==null||!(activityDTO.getShopTargets().size()>0)){
            return R.error("未设置参与门店");
        }
        //判断门店是否存在
        for (ShopTargetItem shopTarget: activityDTO.getShopTargets()) {
            Company company= companyService.getById(shopTarget.getShopCode());
            if (company==null){
                return R.error("门店不存在");
            }
        }
        if(activityDTO.getIsSetShopTarget().equals("1")){
            for (ShopTargetItem shopTarget: activityDTO.getShopTargets()) {
                if(shopTarget.getTarget()==null||!(shopTarget.getTarget()>0)){
                    return R.error("设置参与门店指标时:门店指标不能小于0");
                }
            }
        }
        //奖励规则校验
        if(activityDTO.getRewardPlanType().equals("1")){
            if(!(activityDTO.getAwardRules().size()>0)){
                return R.error("奖励方式为自动时:奖励规则不能为空");
            }
        }
       boolean flag= activityService.insertActivity(activityDTO);
        if (!flag){
            return R.error("新增活动失败");
        }
        return R.ok();
    }

    @ApiOperation(value = "获取活动列表")
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('sys:activity:view')")
    public R<PageBean<ActivityPageVO>> getActivityList(@RequestBody ActivityPageDTO activityPageDTO) {
        Page<Activity> page=new Page(activityPageDTO.getCurrent(),activityPageDTO.getSize());
        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(activityPageDTO.getActivityName())){
            queryWrapper.like("activity_name","%"+activityPageDTO.getActivityName()+"%");
        }
        if(StringUtils.isNotEmpty(activityPageDTO.getActivityStatus())){
            queryWrapper.eq("activity_status",activityPageDTO.getActivityStatus());
        }
        if(StringUtils.isNotEmpty(activityPageDTO.getRewardStatus())){
            queryWrapper.eq("reward_status",activityPageDTO.getRewardStatus());
        }
        if(activityPageDTO.getStartTime()!=null&&activityPageDTO.getEndTime()!=null){
            queryWrapper.and(wrapper->(wrapper.between("start_time",activityPageDTO.getStartTime(),activityPageDTO.getEndTime()).or().between("end_time",activityPageDTO.getStartTime(),activityPageDTO.getEndTime())).getCustomSqlSegment());
//            queryWrapper.between("start_time",activityPageDTO.getStartTime(),activityPageDTO.getEndTime());
//            queryWrapper.or();
//            queryWrapper.between("end_time",activityPageDTO.getStartTime(),activityPageDTO.getEndTime());
        }
        if(activityPageDTO.getStartTime()!=null&&activityPageDTO.getEndTime()==null){
            queryWrapper.ge("end_time",activityPageDTO.getStartTime());
        }
        if(activityPageDTO.getStartTime()==null&&activityPageDTO.getEndTime()!=null){
            queryWrapper.le("start_time",activityPageDTO.getEndTime());
        }
        page= activityService.page(page,queryWrapper);
        PageBean<ActivityPageVO> resultPage=new PageBean<>();
        List<ActivityPageVO> list=new ArrayList<>();
        for (Activity activity: page.getRecords()) {
            ActivityPageVO activityVO=new ActivityPageVO();
            BeanUtils.copyProperties(activity,activityVO);
            activityVO.setCurrentTime(new Date());
            list.add(activityVO);
        }
        resultPage.setRecords(list);
        resultPage.setCurrent(page.getCurrent());
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return R.ok(resultPage);
    }

    @ApiOperation(value = "获取活动详情")
    @PostMapping("/detail")
    @PreAuthorize("hasAuthority('sys:activity:detail')")
    public R<ActivityDetailVO> detail(@RequestBody ActivityIdDTO activityIdDTO) {
        return R.ok(activityService.activityDetail(activityIdDTO));
    }

    @ApiOperation(value = "发布活动")
    @PostMapping("/publish")
    @PreAuthorize("hasAuthority('sys:activity:publish')")
    public R<ActivityDetailVO> publish(@RequestBody ActivityIdDTO activityIdDTO) {
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
        activity.setActivityStatus("1");
        boolean flag=activityService.updateById(activity);
        if (flag){
            //发送消息
            List<ShopTarget> shopTargets=shopTargetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for (ShopTarget shopTarget: shopTargets) {
                //获取门店员工
               List<SysUser> users= userService.list(new QueryWrapper<SysUser>().eq("companyId",shopTarget.getShopCode()).eq("user_type","0"));
                for (SysUser sysUser: users) {
                    try {
                        WxActivityNoticeDTO wxActivityNoticeDTO=new WxActivityNoticeDTO();
                        wxActivityNoticeDTO.setActivityName(activity.getActivityName());
                        wxActivityNoticeDTO.setOpenId(sysUser.getOpenId());
                        wxActivityNoticeDTO.setStartTime(sdf.format(activity.getStartTime()));
                        wxActivityNoticeDTO.setEndTime(sdf.format(activity.getEndTime()));
                        wxActivityNoticeDTO.setReminder("名额有限，速速来抢");
                        MessageSendUtil.sendActivityMessage(wxActivityNoticeDTO);
                    }catch (Exception e){
                    }
                }
            }
            return R.ok();
        }else {
            return R.error("发布失败");
        }

    }

    @ApiOperation(value = "提前结束")
    @PostMapping("/end")
    @PreAuthorize("hasAuthority('sys:activity:end')")
    public R end(@RequestBody ActivityIdDTO activityIdDTO) {
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
        if(!activity.getActivityStatus().equals("1")){
            return R.error("不是进行中的活动不能提前结束");
        }
        activity.setActivityStatus("2");
        boolean flag=activityService.updateById(activity);
        if (flag){
            //发送消息
            List<ShopTarget> shopTargets=shopTargetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()));
            for (ShopTarget shopTarget: shopTargets) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                //获取门店员工
                List<SysUser> users = userService.list(new QueryWrapper<SysUser>().eq("companyId", shopTarget.getShopCode()).eq("user_type", "0"));
                for (SysUser sysUser : users) {
                    try {
                        WxActivityNoticeDTO wxActivityNoticeDTO = new WxActivityNoticeDTO();
                        wxActivityNoticeDTO.setActivityName(activity.getActivityName());
                        wxActivityNoticeDTO.setOpenId(sysUser.getOpenId());
                        wxActivityNoticeDTO.setStartTime(sdf.format(activity.getStartTime()));
                        wxActivityNoticeDTO.setEndTime(sdf.format(activity.getEndTime()));
                        wxActivityNoticeDTO.setReminder("提前结束");
                        MessageSendUtil.sendActivityEndMessage(wxActivityNoticeDTO);
                    } catch (Exception e) {
                    }
                }
            }
            return R.ok();
        }else {
            return R.error("结束失败");
        }

    }

    /**
     * 添加活动
     * @param activityDTO
     * @return
     */
    @ApiOperation(value = "修改活动")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:activity:update')")
    public R update(@RequestBody ActivityDTO activityDTO) {
        //商品校验
        if (activityDTO.getReserves()==null||!(activityDTO.getReserves().size()>0)){
            return R.error("未添加商品");
        }
        //商品价格校验
        for (ActivityReserveItem reserve: activityDTO.getReserves()) {
            if(reserve.getProductPrice().compareTo(new BigDecimal(0))!=1){
                return R.error("库存价格不能小于0");
            }
        }
        //库存校验
        if(activityDTO.getIsSetReserve().equals("1")){
            for (ActivityReserveItem reserve: activityDTO.getReserves()) {
                if(!(reserve.getTotalNumber()>0)){
                    return R.error("设置库存是:商品数量不能小于0");
                }
            }
        }
        //参与店铺校验
        if(activityDTO.getShopTargets()==null||!(activityDTO.getShopTargets().size()>0)){
            return R.error("未设置参与门店");
        }
        for (ShopTargetItem shopTarget: activityDTO.getShopTargets()) {
            Company company= companyService.getById(shopTarget.getShopCode());
            if (company==null){
                return R.error("门店不存在");
            }
        }
        if(activityDTO.getIsSetShopTarget().equals("1")){
            for (ShopTargetItem shopTarget: activityDTO.getShopTargets()) {
                Company company= companyService.getById(shopTarget.getShopCode());
                if (company==null){
                    return R.error("门店不存在");
                }
                if(!(shopTarget.getTarget()>0)){
                    return R.error("设置参与门店指标时:门店指标不能小于0");
                }
            }
        }
        //奖励规则校验
        if(activityDTO.getRewardPlanType().equals("1")){
            if(activityDTO.getAwardRules()==null||!(activityDTO.getAwardRules().size()>0)){
                return R.error("奖励方式为自动时:奖励规则不能为空");
            }
        }
        Activity activity=activityService.getById(activityDTO.getPkid());
        if(activity.getActivityStatus().equals("1")){
            //活动中库存校验
            for (ActivityReserveItem reserveDTO: activityDTO.getReserves()) {
                ActivityReserve reserve=reserveService.getById(reserveDTO.getPkid());
                if(reserve.getTotalNumber()>reserveDTO.getTotalNumber()){
                    return R.error("活动中的库存数量不能比初始数量少");
                }
            }
        }
        return R.ok(activityService.updateActivity(activityDTO));
    }
    /**
     * 删除活动
     * @param activityIdDTO
     * @return
     */
    @ApiOperation(value = "删除活动")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:activity:delete')")
    public R update(@RequestBody ActivityIdDTO activityIdDTO) {
       Activity activity=activityService.getById(activityIdDTO.getActivityId());
       if(!activity.getActivityStatus().equals("0")){
           return R.ok("活动已开始或者已结束不能删除");
       }
       Boolean flag=activityService.removeById(activityIdDTO.getActivityId());
        return R.ok(flag);
    }

    /**
     * 参与店铺
     * @param activityIdDTO
     * @return
     */
    @ApiOperation(value = "参与店铺")
    @PostMapping("/partake")
    @PreAuthorize("hasAuthority('sys:activity:partake')")
    public R<List<PartakeCompanyVO>> partake(@RequestBody ActivityIdDTO activityIdDTO) {
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
        if(activity==null){
            return R.error("该活动不存在");
        }
        List<ShopTarget> targets=shopTargetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activityIdDTO.getActivityId()).orderByDesc("target"));
        List<PartakeCompanyVO> partakeCompanyVOS =new ArrayList<>();
        for (ShopTarget shopTarget: targets) {
            PartakeCompanyVO partakeCompanyVO=new PartakeCompanyVO();
            Company company = companyService.getById(shopTarget.getShopCode());
            BeanUtils.copyProperties(company,partakeCompanyVO);
            partakeCompanyVO.setTaget(shopTarget.getTarget());
            partakeCompanyVO.setUnit(shopTarget.getUnit());
            partakeCompanyVOS.add(partakeCompanyVO);
        }
        return R.ok(partakeCompanyVOS);
    }
}
