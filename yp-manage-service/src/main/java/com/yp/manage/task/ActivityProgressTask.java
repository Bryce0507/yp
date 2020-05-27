package com.yp.manage.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yp.entity.Activity;
import com.yp.entity.ShopTarget;
import com.yp.entity.SysUser;
import com.yp.manage.util.wx.MessageSendUtil;
import com.yp.manage.util.wx.dto.WxActivityProgressDTO;
import com.yp.service.*;
import com.yp.vo.wx.shop.ShopItemCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@EnableScheduling
public class ActivityProgressTask {
    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityAwardService awardService;
    @Autowired
    ActivityAwardRuleService ruleService;
    @Autowired
    private ShopTargetService shopTargetService;

    @Autowired
    private ActivitySaleRecordService saleRecordService;

    @Autowired
    private ISysUserService userService;

    @Scheduled(cron = "0 0 9 * * ? ")
    private void configureTasks() {

        List<Activity> activityList=activityService.list(new QueryWrapper<Activity>().eq("activity_status","1").eq("is_set_shop_target",1));
        for (Activity activity: activityList) {
            //发送消息
            List<ShopTarget> shopTargets=shopTargetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for (ShopTarget shopTarget: shopTargets) {
                //获取门店业绩
                ShopItemCount shopItemCount=saleRecordService.getShopCount(activity.getPkid()+"",shopTarget.getShopCode());
                //获取门店员工
                List<SysUser> users= userService.list(new QueryWrapper<SysUser>().eq("companyId",shopTarget.getShopCode()).eq("user_type","0"));
                for (SysUser sysUser: users) {
                    try {
                        WxActivityProgressDTO wxActivityNoticeDTO=new WxActivityProgressDTO();
                        wxActivityNoticeDTO.setActivityName(activity.getActivityName());
                        wxActivityNoticeDTO.setOpenId(sysUser.getOpenId());
                        wxActivityNoticeDTO.setStartTime(sdf.format(activity.getStartTime()));
                        wxActivityNoticeDTO.setEndTime(sdf.format(activity.getEndTime()));
                        long nd = 1000 * 24 * 60 * 60;
                        long nh = 1000 * 60 * 60;
                        long nm = 1000 * 60;
                        long diff = activity.getEndTime().getTime() - activity.getStartTime().getTime();
                        // 计算差多少天
                        long day = diff / nd;
                        // 计算差多少小时
                        long hour = diff % nd / nh;
                        // 计算差多少分钟
                        long min = diff % nd % nh / nm;
                        wxActivityNoticeDTO.setSurplusTime(day + "天" + hour + "小时" + min + "分钟");
                        if(activity.getTargetUntil().equals("0")){
                            wxActivityNoticeDTO.setTarget(shopTarget.getTarget()+"台");
                            if(shopItemCount!=null){
                                wxActivityNoticeDTO.setProgress(shopItemCount.getQuantity()+"台");
                            }else{
                                wxActivityNoticeDTO.setProgress("0台");
                            }
                        }else{
                            wxActivityNoticeDTO.setTarget(shopTarget.getTarget()+"元");
                            if(shopItemCount!=null){
                                wxActivityNoticeDTO.setProgress(shopItemCount.getSaleMoney()+"元");
                            }else{
                                wxActivityNoticeDTO.setProgress("0元");
                            }
                        }
                        MessageSendUtil.sendActivityProgress(wxActivityNoticeDTO);
                    }catch (Exception e){
                    }
                }
            }
        }
    }
}
