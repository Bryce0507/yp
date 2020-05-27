package com.yp.manage.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yp.entity.Activity;
import com.yp.entity.ShopTarget;
import com.yp.entity.SysUser;
import com.yp.manage.util.wx.MessageSendUtil;
import com.yp.manage.util.wx.dto.WxActivityNoticeDTO;
import com.yp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class ActivityTask {
    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityAwardService awardService;
    @Autowired
    ActivityAwardRuleService ruleService;
    @Autowired
    private ShopTargetService shopTargetService;

    @Autowired
    private ISysUserService userService;

    @Scheduled(cron = "0 0/1 * * * ?")
    private void configureTasks() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<Activity> activityList=activityService.list(new QueryWrapper<Activity>().eq("activity_status","1").like("end_time","%"+sdf.format(d)+"%"));
        for (Activity activity: activityList) {
            activity.setActivityStatus("2");
            activityService.updateById(activity);
            //发送消息
            List<ShopTarget> shopTargets=shopTargetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()));
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
                        wxActivityNoticeDTO.setReminder("已结束");
                        MessageSendUtil.sendActivityEndMessage(wxActivityNoticeDTO);
                    }catch (Exception e){
                    }
                }
        }
        }
    }
}
