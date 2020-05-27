package com.yp.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yp.common.utils.R;
import com.yp.dto.wx.activity.ActivityIdDTO;
import com.yp.dto.wx.activity.QueryActivityListDTO;
import com.yp.dto.wx.system.QueryActivityPageDTO;
import com.yp.dto.wx.system.QueryPageDTO;
import com.yp.entity.*;
import com.yp.service.*;
import com.yp.util.SecurityUtil;
import com.yp.vo.PageBean;
import com.yp.vo.wx.activity.ActivityAwardVO;
import com.yp.vo.wx.activity.ActivityDetailsVO;
import com.yp.vo.wx.activity.ActivityFileVO;
import com.yp.vo.wx.activity.ActivityVO;
import com.yp.vo.wx.system.ActivityCircleVO;
import com.yp.vo.wx.system.CircleCountVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统 模块
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Api(tags = "系统")
@RestController
@RequestMapping("/activity")
public class SystemController {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeReadyService noticeReadyService;

    @Autowired
    private ActivityCircleService circleService;

    @Autowired
    private ActivityCircleReadyService circleReadyService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ActivitySaleRecordService saleRecordService;

    @Value("${circle.start.message}")
    private String startMessage;

    @Value("${circle.end.message}")
    private String endMessage;

    @ApiOperation(value = "获取前三公告")
    @PostMapping("/queryThirdNotice")
    public R<List<Notice>> queryThirdNotice() {
        Page<Notice> page=new Page(1,3);
        page= noticeService.page(page,new QueryWrapper<Notice>().eq("is_display","1").orderByDesc("create_time"));
        return R.ok(page.getRecords());
    }

    @ApiOperation(value = "获取公告")
    @PostMapping("/queryNotice")
    public R<PageBean<Notice>> queryNotice(@RequestBody QueryPageDTO queryPageDTO) {
        Page<Notice> page=new Page(queryPageDTO.getCurrent(),queryPageDTO.getSize());
        page= noticeService.page(page,new QueryWrapper<Notice>().eq("is_display","1").orderByDesc("create_time"));
        PageBean<Notice> resultPage=new PageBean<Notice>();
        resultPage.setRecords(page.getRecords());
        resultPage.setCurrent(page.getCurrent());
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());

        //将未读消息设置为已读
        List<NoticeReady> noticeReadies= noticeReadyService.list(new QueryWrapper<NoticeReady>().eq("user_id",SecurityUtil.getUser().getUserId()));
        List<Integer> readyIds=new ArrayList<>();
        for (NoticeReady ready: noticeReadies) {
            //获取已读圈子ID
            readyIds.add(ready.getNoticeId());
        }
        List<Notice> notices=noticeService.list(new QueryWrapper<Notice>().notIn(readyIds.size()>0,"pkid",readyIds));
        for (Notice notice: notices) {
            NoticeReady noticeReady=new NoticeReady();
            noticeReady.setNoticeId(notice.getPkid());
            noticeReady.setUserId(SecurityUtil.getUser().getUserId());
            noticeReady.setCreateTime(new Date());
            noticeReadyService.save(noticeReady);
        }
        return R.ok(resultPage);
    }

    @ApiOperation(value = "获取活动圈子")
    @PostMapping("/queryCircleActivityCount")
    public R<List<CircleCountVO>> queryCircleActivityCount() {
        List<CircleCountVO> circleCountVOS =circleService.queryCircleCount();

        for (CircleCountVO circleCountVO:circleCountVOS) {
            Activity activity= activityService.getBaseMapper().selectById(circleCountVO.getActivityId());
            if(activity.getActivityStatus().equals("1")){
                circleCountVO.setDesc(startMessage);
            }else{
                circleCountVO.setDesc(endMessage);
            }
            circleCountVO.setActivityName(activity.getActivityName());
            circleCountVO.setActivityStatus(activity.getActivityStatus());
        }
        circleCountVOS.sort(Comparator.comparing(CircleCountVO::getActivityStatus));
        return R.ok(circleCountVOS);
    }

    @ApiOperation(value = "获取活动圈子消息")
    @PostMapping("/queryActivityCircle")
    public R<PageBean<ActivityCircleVO>> queryActivityCircle(@RequestBody QueryActivityPageDTO queryPageDTO) {
        Page<ActivityCircle> page=new Page(queryPageDTO.getCurrent(),queryPageDTO.getSize());
        page=circleService.page(page,new QueryWrapper<ActivityCircle>().eq("activity_id",queryPageDTO.getActivityId()).orderByDesc("create_time"));
        PageBean<ActivityCircleVO> resultPage=new PageBean<ActivityCircleVO>();
        List<ActivityCircleVO> list=new ArrayList<>();
        for (ActivityCircle activityCircle: page.getRecords()) {
            ActivityCircleVO circleVO=new ActivityCircleVO();
            SysUser user=userService.getById(activityCircle.getUserId()) ;
            circleVO.setTrueName(user.getTrueName());
            circleVO.setHeadImg(user.getHeadimgurl());
            Activity activity= activityService.getBaseMapper().selectById(queryPageDTO.getActivityId());
            circleVO.setActivityName(activity.getActivityName());
            ActivitySaleRecord record= saleRecordService.getById(activityCircle.getSaleId());
            circleVO.setCarNumber(record.getCarNumber());
            circleVO.setFiles(JSONArray.parseArray(record.getFiles(),String.class));
            circleVO.setCreateTime(activityCircle.getCreateTime());
            Company company=companyService.getById(user.getCompanyId());
            circleVO.setShopName(company.getCompayName());
            list.add(circleVO);
        }
        resultPage.setCurrent(page.getCurrent());
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        resultPage.setRecords(list);
        //将未读消息设置为已读
        List<ActivityCircleReady> circleReadies= circleReadyService.list(new QueryWrapper<ActivityCircleReady>().eq("user_id",SecurityUtil.getUser().getUserId()));
        List<Integer> readyIds=new ArrayList<>();
        for (ActivityCircleReady ready: circleReadies) {
            //获取已读圈子ID
            readyIds.add(ready.getCircleId());
        }
        List<ActivityCircle> circles=circleService.list(new QueryWrapper<ActivityCircle>().notIn(readyIds.size()>0,"pkid",readyIds));
        for (ActivityCircle activityCircle: circles) {
            ActivityCircleReady activityCircleReady=new ActivityCircleReady();
            activityCircleReady.setCircleId(activityCircle.getPkid());
            activityCircleReady.setUserId(SecurityUtil.getUser().getUserId());
            activityCircleReady.setCreateTime(new Date());
            circleReadyService.save(activityCircleReady);
        }
        return R.ok(resultPage);
    }

    @ApiOperation(value = "获取圈子未读数量")
    @PostMapping("/queryCircleCount")
    public R queryCircleCount() {
        int count=circleService.count();
        int readyCount =circleReadyService.count(new QueryWrapper<ActivityCircleReady>().eq("user_id",SecurityUtil.getUser().getUserId()));
        int notReadyCount=count-readyCount;
        return R.ok(notReadyCount);
    }

    @ApiOperation(value = "获取公告未读数量")
    @PostMapping("/queryNoticeCount")
    public R queryNoticeCount() {
      int readyCount =noticeReadyService.count(new QueryWrapper<NoticeReady>().eq("user_id",SecurityUtil.getUser().getUserId()));
      QueryWrapper queryWrapper= new QueryWrapper<NoticeReady>().eq("is_display","1");
      int count =noticeService.count(queryWrapper);
      int notReadyCount=count-readyCount;
      return R.ok(notReadyCount);
    }
}

