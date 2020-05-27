package com.yp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.dao.ActivityMapper;
import com.yp.dto.manage.activity.*;
import com.yp.dto.wx.activity.ActivityIdDTO;
import com.yp.entity.*;
import com.yp.service.*;
import com.yp.util.SecurityUtil;
import com.yp.vo.manage.activity.ActivityDetailVO;
import com.yp.vo.manage.activity.ShopTargetItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 活动表 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-02-16
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Autowired
    private ActivityFileService fileService;
    @Autowired
    private ExampleSaleFileService exampleService;
    @Autowired
    private ActivityAwardRuleService ruleService;
    @Autowired
    private ActivityReserveService reserveService;
    @Autowired
    private ShopTargetService targetService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ActivitySaleRecordService saleRecordService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertActivity(ActivityDTO activityDTO) {
        Activity activity=new Activity();
        BeanUtils.copyProperties(activityDTO,activity);
        activity.setCreateTime(new Date());
        activity.setRewardStatus("0");
        activity.setActivityStatus("0");
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId());
        activity.setCreateUser(user.getTrueName());
        int row=this.baseMapper.insert(activity);
        if(row>0){
            //新增示例图片
            for (ActivityExampleFileItem exampleFileDTO: activityDTO.getExampleFiles()) {
                ExampleSaleFile exampleSaleFile=new ExampleSaleFile();
                BeanUtils.copyProperties(exampleFileDTO,exampleSaleFile);
                exampleSaleFile.setActivityId(activity.getPkid());
                exampleSaleFile.setStatus("0");
                exampleService.save(exampleSaleFile);
            }
            //新增附件
            for (ActivityFileItem fileDTO: activityDTO.getFiles()) {
                ActivityFile file=new ActivityFile();
                BeanUtils.copyProperties(fileDTO,file);
                file.setActivityId(activity.getPkid());
                file.setStatus("0");
                fileService.save(file);
            }

            //新增库存
            for (ActivityReserveItem reserveDTO: activityDTO.getReserves()) {
                ActivityReserve reserve=new ActivityReserve();
                BeanUtils.copyProperties(reserveDTO,reserve);
                reserve.setActivityId(activity.getPkid());
                reserve.setUsableNumber(reserveDTO.getTotalNumber());
                reserveService.save(reserve);
            }
            //新增店铺校验
            for (ShopTargetItem shopTarget: activityDTO.getShopTargets()) {
                ShopTarget target=new ShopTarget();
                BeanUtils.copyProperties(shopTarget,target);
                target.setActivityId(activity.getPkid());
                target.setUnit(activity.getTargetUntil());
                targetService.save(target);
            }
            //奖励规则校验
            if(activityDTO.getRewardPlanType().equals("1")){
                for (ActivityAwardRuleItem awardRuleDTO: activityDTO.getAwardRules()) {
                    ActivityAwardRule rule=new ActivityAwardRule();
                    BeanUtils.copyProperties(awardRuleDTO,rule);
                    rule.setActivityId(activity.getPkid());
                    ruleService.save(rule);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public ActivityDetailVO activityDetail(ActivityIdDTO activityIdDTO) {
        Activity activity= baseMapper.selectById(activityIdDTO.getActivityId());
        ActivityDetailVO activityDetailVO=new ActivityDetailVO();
        BeanUtils.copyProperties(activity,activityDetailVO);
        //示例图片
        List<ActivityExampleFileItem> exampleFileItems=exampleService.list(new QueryWrapper<ExampleSaleFile>().eq("activity_id",activityIdDTO.getActivityId()))
                .stream().map(exampleSaleFile -> {
                    ActivityExampleFileItem activityExampleFileItem = new ActivityExampleFileItem();
           BeanUtils.copyProperties(exampleSaleFile,activityExampleFileItem);
            return activityExampleFileItem;
        }).collect(Collectors.toList());
        activityDetailVO.setExampleFiles(exampleFileItems);
        //附件
        List<ActivityFileItem> activityFileItems=fileService.list(new QueryWrapper<ActivityFile>().eq("activity_id",activityIdDTO.getActivityId()))
                .stream().map(activityFile -> {
                    ActivityFileItem activityFileItem = new ActivityFileItem();
                    BeanUtils.copyProperties(activityFile,activityFileItem);
                    return activityFileItem;
                }).collect(Collectors.toList());
        activityDetailVO.setFiles(activityFileItems);

        //库存
        List<ActivityReserveItem> activityReserveItems=reserveService.list(new QueryWrapper<ActivityReserve>().eq("activity_id",activityIdDTO.getActivityId()))
                .stream().map(activityReserve -> {
                    ActivityReserveItem activityReserveItem = new ActivityReserveItem();
                    BeanUtils.copyProperties(activityReserve,activityReserveItem);
                    return activityReserveItem;
                }).collect(Collectors.toList());
        activityDetailVO.setReserves(activityReserveItems);
        //店铺参与
        List<ShopTargetItemVO> targetItems=targetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activityIdDTO.getActivityId()))
                .stream().map(target-> {
                    ShopTargetItemVO shopTargetItem = new ShopTargetItemVO();
                    Company company= companyService.getById(target.getShopCode());
                    if(company!=null){
                        BeanUtils.copyProperties(company,shopTargetItem);
                    }
                    shopTargetItem.setPkid(target.getPkid());
                    shopTargetItem.setShopCode(company.getPkid());
                    shopTargetItem.setTarget(target.getTarget());
                    shopTargetItem.setUnit(target.getUnit());
                    return shopTargetItem;
                }).collect(Collectors.toList());
        activityDetailVO.setShopTargets(targetItems);
        //奖励规则校验
        if(activity.getRewardPlanType().equals("1")){
            List<ActivityAwardRuleItem> ruleItems=ruleService.list(new QueryWrapper<ActivityAwardRule>().eq("activity_id",activityIdDTO.getActivityId()))
                    .stream().map(rule-> {
                        ActivityAwardRuleItem ruleItem = new ActivityAwardRuleItem();
                        BeanUtils.copyProperties(rule,ruleItem);
                        return ruleItem;
                    }).collect(Collectors.toList());
            activityDetailVO.setAwardRules(ruleItems);
        }
        //判断活动是否已经在进行或者结束
        if(!activity.getActivityStatus().equals("0")){
           List<ActivitySaleRecord> saleRecordList= saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("activity_id",activity.getPkid()).eq("approve_status","1"));
            Integer saleTarget=0;
            for (ActivitySaleRecord saleRecord: saleRecordList) {
                if(activity.getTargetUntil().equals("0")){
                    //台数
                    saleTarget++;
                }else{
                    //金额
                    saleTarget=saleTarget+saleRecord.getProductPrice().intValue();
                }
            }
            activityDetailVO.setSaleTarget(saleTarget);
            //设置活动销售进度
            if(activity.getIsSetTarget().equals("1")){
                double percentage =((float)saleTarget)/(float)activity.getTargetNumber();
                activityDetailVO.setPercentage((int) Math.round(percentage*100));
            }
        }
        activityDetailVO.setCurrentTime(new Date());
        return activityDetailVO;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateActivity(ActivityDTO activityDTO) {
        Activity activity=this.baseMapper.selectById(activityDTO.getPkid());
        BeanUtils.copyProperties(activityDTO,activity);
        int row=this.baseMapper.updateById(activity);
        if(row>0){
            //新增示例图片
          List<Integer> ids= exampleService.list(new QueryWrapper<ExampleSaleFile>().eq("activity_id",activity.getPkid()))
                  .stream().map(item-> {
                      return item.getPkid();
                  }).collect(Collectors.toList());
          exampleService.removeByIds(ids);
          for (ActivityExampleFileItem exampleFileDTO: activityDTO.getExampleFiles()) {
                ExampleSaleFile exampleSaleFile=new ExampleSaleFile();
                BeanUtils.copyProperties(exampleFileDTO,exampleSaleFile);
                exampleSaleFile.setActivityId(activity.getPkid());
                exampleSaleFile.setStatus("0");
                exampleService.save(exampleSaleFile);
            }
            //新增附件
            List<Integer> fileIds= fileService.list(new QueryWrapper<ActivityFile>().eq("activity_id",activity.getPkid()))
                    .stream().map(item-> {
                        return item.getPkid();
                    }).collect(Collectors.toList());
            fileService.removeByIds(fileIds);
            for (ActivityFileItem fileDTO: activityDTO.getFiles()) {
                ActivityFile file=new ActivityFile();
                BeanUtils.copyProperties(fileDTO,file);
                file.setActivityId(activity.getPkid());
                file.setStatus("0");
                fileService.save(file);
            }
            //参与店铺
            if(activity.getActivityStatus().equals("0")){
                List<Integer> tagetIds= targetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()))
                        .stream().map(item-> {
                            return item.getPkid();
                        }).collect(Collectors.toList());
                targetService.removeByIds(tagetIds);
                for (ShopTargetItem shopTarget: activityDTO.getShopTargets()) {
                    ShopTarget target=new ShopTarget();
                    BeanUtils.copyProperties(shopTarget,target);
                    target.setActivityId(activity.getPkid());
                    target.setUnit(activity.getTargetUntil());
                    targetService.save(target);
                }
            }else{
                for (ShopTargetItem shopTarget: activityDTO.getShopTargets()) {
                    ShopTarget target= targetService.getOne(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()).eq("shop_code",shopTarget.getShopCode()));
                  if(target!=null){
                      BeanUtils.copyProperties(shopTarget,target);
                      target.setActivityId(activity.getPkid());
                      target.setUnit(activity.getTargetUntil());
                      targetService.updateById(target);
                  }else{
                      ShopTarget newTarget=new ShopTarget();
                      BeanUtils.copyProperties(shopTarget,newTarget);
                      newTarget.setActivityId(activity.getPkid());
                      newTarget.setUnit(activity.getTargetUntil());
                      targetService.save(newTarget);
                  }
                }
            }
            //奖励规则校验
            List<Integer> ruleIds= ruleService.list(new QueryWrapper<ActivityAwardRule>().eq("activity_id",activity.getPkid()))
                    .stream().map(item-> {
                        return item.getPkid();
                    }).collect(Collectors.toList());
            ruleService.removeByIds(ruleIds);
            if(activityDTO.getRewardPlanType().equals("1")){
                for (ActivityAwardRuleItem awardRuleDTO: activityDTO.getAwardRules()) {
                    ActivityAwardRule rule=new ActivityAwardRule();
                    BeanUtils.copyProperties(awardRuleDTO,rule);
                    rule.setActivityId(activity.getPkid());
                    ruleService.save(rule);
                }
            }
            //新增库存
            if(activity.getActivityStatus().equals("0")){
                List<Integer> reserveIds= reserveService.list(new QueryWrapper<ActivityReserve>().eq("activity_id",activity.getPkid()))
                        .stream().map(item-> {
                            return item.getPkid();
                        }).collect(Collectors.toList());
                reserveService.removeByIds(reserveIds);
                for (ActivityReserveItem reserveDTO: activityDTO.getReserves()) {
                        ActivityReserve reserve=new ActivityReserve();
                        BeanUtils.copyProperties(reserveDTO,reserve);
                        reserve.setActivityId(activity.getPkid());
                        reserve.setUsableNumber(reserveDTO.getTotalNumber());
                        reserveService.save(reserve);
                }
            }else if(activity.getActivityStatus().equals("1")){
                for (ActivityReserveItem reserveDTO: activityDTO.getReserves()) {
                    ActivityReserve reserve=reserveService.getById(reserveDTO.getPkid());
                    reserve.setProductName(reserveDTO.getProductName());
                    reserve.setProductPrice(reserveDTO.getProductPrice());
                    if(reserve.getTotalNumber()==null){
                        reserve.setTotalNumber(reserveDTO.getTotalNumber());
                        reserve.setUsableNumber(reserve.getUsableNumber());
                    }else{
                        Integer add=reserveDTO.getTotalNumber()-reserve.getTotalNumber();
                        reserve.setUsableNumber(add+reserve.getUsableNumber());
                    }
                    reserveService.updateById(reserve);
                }
            }
            return true;
        }
        return false;
    }
}
