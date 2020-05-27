package com.yp.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yp.common.utils.R;
import com.yp.dto.manage.reward.ActivityAwardDTO;
import com.yp.dto.manage.reward.ActivityAwardItem;
import com.yp.dto.wx.activity.ActivityIdDTO;
import com.yp.entity.*;
import com.yp.manage.util.wx.MessageSendUtil;
import com.yp.manage.util.wx.dto.WxActivityRewardDTO;
import com.yp.service.*;
import com.yp.vo.manage.sale.ActivityAwardItemVO;
import com.yp.vo.wx.shop.ShopItemCount;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname IndexController
 * @Description 业绩模块
 * @Author HCS lihaodongmail@163.com
 * @Date 2019-05-07 12:38
 * @Version 1.0
 */
@Api(tags = "奖励模块")
@RestController
@RequestMapping("/reward")
public class RewardController {
    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityAwardService awardService;
    @Autowired
    ActivityAwardRuleService ruleService;
    @Autowired
    CompanyService companyService;
    @Autowired
    private ShopTargetService shopTargetService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ActivitySaleRecordService saleRecordService;


    @ApiOperation(value = "自动结算")
    @PostMapping("/auto")
    @PreAuthorize("hasAuthority('sys:reward:auto')")
    public R auto(@RequestBody ActivityIdDTO activityIdDTO) {
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
        if(!activity.getActivityStatus().equals("2")) {
            return R.error("该活动还未不能公布奖励");
        }
        if(!activity.getRewardPlanType().equals("1")) {
            return R.error("该活动不支持自动结算");
        }
       List<ActivitySaleRecord> records= saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("activity_id",activityIdDTO.getActivityId()).eq("approve_status","0"));
        if(records.size()>0){
            return R.error("该业绩尚有业绩未审核不能结算");
        }
        try {
            //获取店铺排名
            List<ShopItemCount> shopItemCounts;
            if(activity.getIsSetTarget().equals("1")){
                shopItemCounts=saleRecordService.queryShopCount(activity.getPkid()+"",activity.getTargetUntil());
            }else{
                shopItemCounts=saleRecordService.queryShopCount(activity.getPkid()+"","0");
            }
            //自动结算奖励
            for (ShopItemCount shopItemCount: shopItemCounts) {
                ActivityAward award=new ActivityAward();
                award.setActivityId(activity.getPkid());
                award.setShopCode(shopItemCount.getShopCode());
                if(activity.getIsSetTarget().equals("1")){
                    award.setUnit(activity.getTargetUntil());
                    if (activity.getTargetUntil().equals("1")){
                        award.setSale(shopItemCount.getSaleMoney().intValue());
                    }else{
                        award.setSale(shopItemCount.getQuantity());
                    }
                }else{
                    award.setUnit("0");
                    award.setSale(shopItemCount.getQuantity());
                }
                ActivityAwardRule rule= ruleService.getOne(new QueryWrapper<ActivityAwardRule>().eq("activity_id",activity.getPkid()).eq("top",shopItemCounts.indexOf(shopItemCount)+1));
                award.setAward(rule.getAward());
                award.setAwardName(rule.getAwardName());
                award.setTop(rule.getTop());
                awardService.save(award);
            }
            activity.setRewardStatus("1");
            activityService.updateById(activity);
            //发送消息
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            List<ShopTarget> shopTargets=shopTargetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()));
            for (ShopTarget shopTarget: shopTargets) {
                //获取门店员工
                List<SysUser> users= userService.list(new QueryWrapper<SysUser>().eq("companyId",shopTarget.getShopCode()).eq("user_type","0"));
                for (SysUser sysUser: users) {
                    try {
                        WxActivityRewardDTO wxActivityNoticeDTO=new WxActivityRewardDTO();
                        wxActivityNoticeDTO.setActivityName(activity.getActivityName());
                        wxActivityNoticeDTO.setOpenId(sysUser.getOpenId());
                        wxActivityNoticeDTO.setStartTime(sdf.format(activity.getStartTime()));
                        wxActivityNoticeDTO.setEndTime(sdf.format(activity.getEndTime()));
                        wxActivityNoticeDTO.setReminder("已公布");
                        MessageSendUtil.sendRewardActivity(wxActivityNoticeDTO);
                    }catch (Exception e){
                    }
                }
            }
        }catch (Exception e){
            return R.error("自动结算失败");
        }
      return R.ok();
    }

    @ApiOperation(value = "人工结算")
    @PostMapping("/artificial")
    @PreAuthorize("hasAuthority('sys:reward:artificial')")
    public R artificial(@RequestBody ActivityAwardDTO activityAwardDTO) {
        try {
            Activity activity = activityService.getById(activityAwardDTO.getActivityId());
            if (!activity.getActivityStatus().equals("2")) {
                return R.error("该活动还未结束不能公布奖励");
            }
            List<ActivitySaleRecord> records = saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("activity_id", activityAwardDTO.getActivityId()).eq("approve_status", "0"));
            if (records.size() > 0) {
                return R.error("该业绩尚有业绩未审核不能结算");
            }
            List<Integer> awards = awardService.list(new QueryWrapper<ActivityAward>().eq("activity_id", activityAwardDTO.getActivityId()))
                    .stream().map(item -> {
                        return item.getPkid();
                    }).collect(Collectors.toList());
            awardService.removeByIds(awards);
            for (ActivityAwardItem awardItem : activityAwardDTO.getAwards()) {
                ActivityAward award = new ActivityAward();
                award.setActivityId(activity.getPkid());
                award.setShopCode(awardItem.getShopCode());
                award.setAward(awardItem.getAward());
                award.setAwardName(awardItem.getAwardName());
                award.setSale(awardItem.getSale());
                award.setUnit(awardItem.getUnit());
                award.setTop(awardItem.getTop());
                awardService.save(award);
            }
            activity.setRewardStatus("1");
            activityService.updateById(activity);
            //发送消息
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            List<ShopTarget> shopTargets=shopTargetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()));
            for (ShopTarget shopTarget: shopTargets) {
                //获取门店员工
                List<SysUser> users= userService.list(new QueryWrapper<SysUser>().eq("companyId",shopTarget.getShopCode()).eq("user_type","0"));
                for (SysUser sysUser: users) {
                    try {
                        WxActivityRewardDTO wxActivityNoticeDTO=new WxActivityRewardDTO();
                        wxActivityNoticeDTO.setOpenId(sysUser.getOpenId());
                        wxActivityNoticeDTO.setActivityName(activity.getActivityName());
                        wxActivityNoticeDTO.setStartTime(sdf.format(activity.getStartTime()));
                        wxActivityNoticeDTO.setEndTime(sdf.format(activity.getEndTime()));
                        wxActivityNoticeDTO.setReminder("已公布");
                        MessageSendUtil.sendRewardActivity(wxActivityNoticeDTO);
                    }catch (Exception e){
                    }
                }
            }
            return R.ok();
        }catch (Exception e){
            return R.error("添加奖励失败");
        }
    }

    @ApiOperation(value = "奖励列表")
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('sys:reward:list')")
    public R<List<ActivityAwardItemVO>> artificial(@RequestBody ActivityIdDTO activityIdDTO) {
       List<ActivityAward> awards= awardService.list(new QueryWrapper<ActivityAward>().eq("activity_id",activityIdDTO.getActivityId()));
        List<ActivityAwardItemVO> awardItems=new ArrayList<>();
        for (ActivityAward award: awards) {
            Company company=companyService.getById(award.getShopCode());
            ActivityAwardItemVO awardItem=new ActivityAwardItemVO();
            BeanUtils.copyProperties(award,awardItem);
            awardItem.setShopName(company.getCompayName());
            awardItems.add(awardItem);
        }
       return R.ok(awardItems);
    }

}
