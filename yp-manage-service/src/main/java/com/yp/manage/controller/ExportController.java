package com.yp.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yp.entity.Activity;
import com.yp.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Classname IndexController
 * @Description 活动模块
 * @Author HCS lihaodongmail@163.com
 * @Date 2019-05-07 12:38
 * @Version 1.0
 */
@Api(tags = "导出模块")
@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private ActivityService activityService;

    @ApiOperation(value = "获取活动列表")
    @GetMapping("/activitys")
    @PreAuthorize("hasAuthority('sys:export:activitys')")
    public void  getActivityList(String activityName,String activityStatus, String rewardStatus,String startTime,String endTime,HttpServletResponse response) throws IOException {
        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(activityName)){
            queryWrapper.like("activity_name","%"+activityName+"%");
        }
        if(StringUtils.isNotEmpty(activityStatus)){
            queryWrapper.eq("activity_status",activityStatus);
        }
        if(StringUtils.isNotEmpty(rewardStatus)){
            queryWrapper.eq("reward_status",rewardStatus);
        }
        if(StringUtils.isNotEmpty(startTime)&&StringUtils.isNotEmpty(endTime)){
            queryWrapper.and(wrapper->(wrapper.between("start_time",startTime,endTime).or().between("end_time",startTime,endTime)).getCustomSqlSegment());
        }
        if(StringUtils.isNotEmpty(startTime)){
            queryWrapper.ge("end_time",startTime);
        }
        if(StringUtils.isNotEmpty(endTime)){
            queryWrapper.le("start_time",endTime);
        }
        List<Activity> activities= activityService.list(queryWrapper);
        //表头数据
        String[] header = {"活动编号", "活动名称", "活动开始时间", "活动结束时间", "剩余时间", "创建人","活动状态","奖励情况"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"活动列表"
        HSSFSheet sheet = workbook.createSheet("活动列表");
        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < activities.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);
            Activity activity=activities.get(i);

            HSSFCell cell1 = row.createCell(0);
            HSSFRichTextString text = new HSSFRichTextString(activity.getPkid()+"");
            cell1.setCellValue(text);

            HSSFCell cell2 = row.createCell(1);
            HSSFRichTextString activityName1 = new HSSFRichTextString(activity.getActivityName());
            cell2.setCellValue(activityName1);

            HSSFCell cell3 = row.createCell(2);
            HSSFRichTextString startTime1 = new HSSFRichTextString(sf.format(activity.getStartTime()));
            cell3.setCellValue(startTime1);

            HSSFCell cell4 = row.createCell(3);
            HSSFRichTextString endTime1 = new HSSFRichTextString(sf.format(activity.getEndTime()));
            cell4.setCellValue(endTime1);


            HSSFCell cell5 = row.createCell(4);
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
            HSSFRichTextString time5 = new HSSFRichTextString(day + "天" + hour + "小时" + min + "分钟");
            cell5.setCellValue(time5);

            HSSFCell cell6 = row.createCell(5);
            HSSFRichTextString createUser = new HSSFRichTextString(activity.getCreateUser());
            cell6.setCellValue(createUser);

            HSSFCell cell7 = row.createCell(6);
            HSSFRichTextString activityStatus1 ;
            if( activity.getActivityStatus().equals("0")){
                activityStatus1 = new HSSFRichTextString("待发布");

            }else if( activity.getActivityStatus().equals("01")){
                activityStatus1 = new HSSFRichTextString("进行中");
            }else{
                activityStatus1 = new HSSFRichTextString("已结束");
            }
            cell7.setCellValue(activityStatus1);

            HSSFCell cell8 = row.createCell(7);
            HSSFRichTextString rewardStatus1;
            if( activity.getRewardStatus().equals("0")){
                rewardStatus1 = new HSSFRichTextString("未公布");

            }else if( activity.getRewardStatus().equals("1")){
                rewardStatus1 = new HSSFRichTextString("已公布");
            }else{
                rewardStatus1 = new HSSFRichTextString("-");
            }
            cell8.setCellValue(rewardStatus1);
        }
        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/vnd.ms-excel");
        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=活动列表"+sf.format(new Date())+".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }
}
