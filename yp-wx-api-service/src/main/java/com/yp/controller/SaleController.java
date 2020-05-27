package com.yp.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yp.common.constant.ActivityStatusConstant;
import com.yp.common.constant.SaleOrderStatus;
import com.yp.common.utils.FileUtil;
import com.yp.common.utils.R;
import com.yp.dto.UploadFileDTO;
import com.yp.dto.wx.activity.ActivityIdDTO;
import com.yp.dto.wx.sale.*;
import com.yp.entity.*;
import com.yp.service.*;
import com.yp.util.SecurityUtil;
import com.yp.util.wx.MessageSendUtil;
import com.yp.util.wx.dto.WxActivityApprovalDTO;
import com.yp.vo.wx.sale.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 销售业绩
 * </p>
 * @author lihaodong
 * @since 2019-04-21
 */
@Api(tags = "销售业绩")
@RestController
@RequestMapping("/sale")
public class SaleController {

    @Value("${file.address}")
    private String address;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ActivitySaleRecordService saleRecordService;
    @Autowired
    private ActivityReserveService reserveService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ShopTargetService shopTargetService;
    @Autowired
    private UserTargetService userTargetService;
    @Autowired
    private ActivityReserveService activityReserveService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ExampleSaleFileService exampleSaleFileService;
    @Autowired
    private ActivitySaleOrderService activitySaleOrderService;


    @ApiOperation(value = "上传销售业绩")
    @PostMapping("/uploadSale")
    public R uploadSale(@RequestBody UploadSaleDTO uploadSaleDTO) {
        Activity activity= activityService.getBaseMapper().selectById(uploadSaleDTO.getActivityId());
        if(!activity.getActivityStatus().equals(ActivityStatusConstant.ActivityStatus.ONGOIND.getValue())){
            return  R.error("活动还未开始或已结束");
        }
//        ActivityReserve activityReserve= reserveService.getById(uploadSaleDTO.getProductId());
        List<Integer> productIdList = uploadSaleDTO.getProductInfoList().stream().map(ProductInfo::getProductId).collect(Collectors.toList());

        List<ActivityReserve> activityReserveList = reserveService.list(new QueryWrapper<ActivityReserve>().in("pkid", productIdList));
        if (CollectionUtils.isEmpty(activityReserveList)) {
            return R.error("所选产品已下架");
        }
        for (ActivityReserve activityReserve : activityReserveList) {
            if (activityReserve.getUsableNumber() <= 0) {
                return R.error("所选" + activityReserve.getProductName() + "已无库存");
            }
            if(activityReserve.getProductPrice().intValue()>Integer.parseInt(uploadSaleDTO.getProductInfoList().stream().filter(productInfo -> productInfo.getProductId().equals(activityReserve.getPkid())).map(ProductInfo::getProductPrice).collect(Collectors.toList()).get(0))){
                return R.error("所选" + activityReserve.getProductName() + "产品金额低于标准价");
            }
            List<ActivitySaleRecord> saleRecords = saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("activity_id", uploadSaleDTO.getActivityId()).eq("car_number", uploadSaleDTO.getCarNumber()).in("approve_status", "0", "1").eq("product_id", activityReserve.getPkid()));
            if (saleRecords.size() > 0) {
                return R.error("该车牌号已参加该活动的" + activityReserve.getProductName());
            }
        }

/*        if(activityReserve==null){
            return R.error("该产品已下架");
        }
        if(activityReserve.getUsableNumber()<=0){
            return R.error("该产品已无库存");
        }

        if(activityReserve.getProductPrice().intValue()>Integer.parseInt(uploadSaleDTO.getProductPrice())){
            return R.error("产品金额低于标准价");
        }*/
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        Company company = companyService.getById(user.getCompanyId());
        ShopTarget shopTarget=shopTargetService.getOne(new QueryWrapper<ShopTarget>().eq("activity_id",uploadSaleDTO.getActivityId()).eq("shop_code",user.getCompanyId()));
        if(shopTarget==null){
            return  R.error("您所在门店未参与该活动");
        }

        ActivitySaleOrder activitySaleOrder = new ActivitySaleOrder();
        activitySaleOrder.setActivityId(uploadSaleDTO.getActivityId());
        activitySaleOrder.setShopCode(company.getPkid());
        activitySaleOrder.setShopName(company.getCompayName());
        activitySaleOrder.setUserId(user.getUserId());
        activitySaleOrder.setUserName(user.getTrueName());
        List<String> productPriceList = uploadSaleDTO.getProductInfoList().stream().map(ProductInfo::getProductPrice).collect(Collectors.toList());
        BigDecimal saleOrderPrice = BigDecimal.ZERO;
        for (String productPrice : productPriceList) {
            saleOrderPrice = saleOrderPrice.add(new BigDecimal(productPrice));
        }
        activitySaleOrder.setSaleOrderPrice(saleOrderPrice);
        activitySaleOrder.setCarNumber(uploadSaleDTO.getCarNumber());
        activitySaleOrder.setSaleOrderStatus(SaleOrderStatus.AUDIT.getValue());
        StringBuilder builder = new StringBuilder();
        builder.append("YP");
        builder.append("-");
        builder.append(FastDateFormat.getInstance("yyMMdd").format(Calendar.getInstance().getTime()));
        builder.append(RandomStringUtils.randomNumeric(5));
        activitySaleOrder.setSaleOrderCode(builder.toString());
        activitySaleOrder.setFiles(JSON.toJSONString(uploadSaleDTO.getFiles()));
        activitySaleOrder.setSaleTime(new Date());
        activitySaleOrderService.save(activitySaleOrder);


        for (ActivityReserve activityReserve : activityReserveList) {
            ActivitySaleRecord record=new ActivitySaleRecord();
            ProductInfo productInfo = uploadSaleDTO.getProductInfoList().stream().filter(a -> a.getProductId().equals(activityReserve.getPkid())).collect(Collectors.toList()).get(0);
            record.setActivityId(uploadSaleDTO.getActivityId());
            record.setShopCode(user.getCompanyId());
            record.setProductId(productInfo.getProductId());
            record.setProductName(productInfo.getProductName());
            record.setProductPrice(new BigDecimal(productInfo.getProductPrice()));
            record.setCarNumber(uploadSaleDTO.getCarNumber());
            record.setUserId(user.getUserId());
            record.setUserName(user.getTrueName());
            record.setApproveStatus("0");
            record.setFiles(JSON.toJSONString(uploadSaleDTO.getFiles()));
            record.setSaleTime(new Date());
            record.setSaleOrderId(activitySaleOrder.getPkid());
            saleRecordService.save(record);

            //修改库存
            activityReserve.setUsableNumber(activityReserve.getUsableNumber() - 1);
            activityReserveService.updateById(activityReserve);
        }

        //查询风控权限的管理员
        List<SysUser> users=userService.list(new QueryWrapper<SysUser>().eq("user_type","1"));
        for (SysUser sysUser: users) {
            Set<String> permissions = userService.findPermsByUserId(sysUser.getUserId());
            if(permissions.contains("sys:sale:approve")||permissions.contains("sys:sale:refund")){
                SysUser manageUser=userService.getOne(new QueryWrapper<SysUser>().eq("username",sysUser.getPhone()).eq("user_type","0"));
               if(manageUser!=null&& StringUtils.isNotBlank(manageUser.getOpenId())){
                   //发送消息
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                   try {
                       WxActivityApprovalDTO wxActivityApprovalDTO=new WxActivityApprovalDTO();
                       Company company1= companyService.getById(user.getCompanyId());
                       wxActivityApprovalDTO.setOpenId(manageUser.getOpenId());
                       wxActivityApprovalDTO.setShopName(company1.getCompayName());
                       wxActivityApprovalDTO.setApplyTime(sdf.format(new Date()));
                       wxActivityApprovalDTO.setName(user.getTrueName());
                       wxActivityApprovalDTO.setReminder("请登录壹号精养后台进行审核操作");
                       MessageSendUtil.sendApprovalMessage(wxActivityApprovalDTO);
                   }catch (Exception e){
                       e.printStackTrace();
                   }
               }
            }
        }
        return R.ok();
    }

    @ApiOperation(value = "查询销售业绩")
    @PostMapping("/querySale")
    public R<SaleRecordVO> querySale(@RequestBody SaleIdDTO saleIdDTO) {
        ActivitySaleRecord saleRecord= saleRecordService.getById(saleIdDTO.getSaleId());
        SaleRecordVO saleRecordVO =new SaleRecordVO();
        BeanUtils.copyProperties(saleRecord,saleRecordVO);
        saleRecordVO.setFiles(JSONArray.parseArray(saleRecord.getFiles(),String.class));
        return R.ok(saleRecordVO);
    }

    @ApiOperation(value = "修改销售业绩")
    @PostMapping("/updateSale")
    public R updateSale(@RequestBody UpdateSaleDTO updateSaleDTO) {
      ActivityReserve reserve=reserveService.getById(updateSaleDTO.getProductId());
      if(reserve==null){
          return R.error("该产品不存在");
      }
        ActivitySaleRecord record= saleRecordService.getById(updateSaleDTO.getSaleId());
        List<ActivitySaleRecord> saleRecords= saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("activity_id",record.getActivityId()).eq("car_number",updateSaleDTO.getCarNumber()).in("approve_status","0","1"));
        if(saleRecords.size()>0){
            return  R.error("该车牌号已参与活动");
        }
        record.setProductId(updateSaleDTO.getProductId());
        record.setProductName(updateSaleDTO.getProductNmae());
        record.setProductPrice(new BigDecimal(updateSaleDTO.getProductPrice()));
        record.setCarNumber(updateSaleDTO.getCarNumber());
        record.setApproveStatus("0");
        record.setFiles(JSON.toJSONString(updateSaleDTO.getFiles()));
        saleRecordService.updateById(record);
        return R.ok();
    }


    @ApiOperation(value = "上传销售凭证")
    @PostMapping("/uploadFile")
    public R uploadFile(@Valid UploadFileDTO uploadFileDTO) {
      String filePath="/sale/"+SecurityUtil.getUser().getUserId()+"/";
        String result=FileUtil.upload(uploadFileDTO,filePath);
        if(result.equals("success")){
            return R.ok(address+filePath+uploadFileDTO.getFile().getOriginalFilename());
        }else{
            return R.error("文件上传失败");
        }
    }

    @ApiOperation(value = "我的业绩指标")
    @PostMapping("/queryMySaleTarget")
    public R<MySaleTargetVO> queryMySaleTarget(@RequestBody ActivityIdDTO activityIdDTO) {
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        UserTarget userTarget=userTargetService.getOne(new QueryWrapper<UserTarget>().eq("activity_id",activityIdDTO.getActivityId()).eq("user_id",user.getUserId()));

        MySaleTargetVO mySaleTargetVO=new MySaleTargetVO();
        if(userTarget!=null){
            mySaleTargetVO.setTarget(userTarget.getTarget());
            mySaleTargetVO.setUnit(userTarget.getUnit());

            List<MySaleCountVO> mySaleCountVOs=saleRecordService.queryMySaleCountByActivity(activityIdDTO.getActivityId(),user.getUserId()+"");
            for (MySaleCountVO mySaleCountVO: mySaleCountVOs) {
                if (mySaleCountVO.getApproveStatus().equals("1")){
                    if (userTarget.getUnit().equals("0")){
                        //台数指标
                        double percentage =(float)mySaleCountVO.getQuantity()/(float)userTarget.getTarget();
                        mySaleTargetVO.setPercentage((int)  Math.round(percentage*100));
                    }else{
                        //台数指标
                        double percentage =((float)mySaleCountVO.getSaleMoney().intValue())/(float)userTarget.getTarget();
                        mySaleTargetVO.setPercentage((int)  Math.round(percentage*100));
                    }
                }
            }
            return R.ok(mySaleTargetVO);
        }else{
            return R.ok(null);
        }

    }

    @ApiOperation(value = "查询我的业绩统计")
    @PostMapping("/queryMySaleCount")
    public R<List<MySaleCountVO>> queryMySaleCount(@RequestBody ActivityIdDTO activityIdDTO) {
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        List<MySaleCountVO> mySaleCountVOs=saleRecordService.queryMySaleCountByActivity(activityIdDTO.getActivityId(),user.getUserId()+"");
        return R.ok(mySaleCountVOs);
    }

    @ApiOperation(value = "查询我的产品业绩统计")
    @PostMapping("/queryMyProductSaleCount")
    public R< List<MySaleCountByProductVO>> queryMyProductSaleCount(@RequestBody ActivityIdDTO activityIdDTO) {
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        List<MySaleCountByProductVO> mySaleCountVOs=saleRecordService.queryMySaleCountByActivityAndStatus(activityIdDTO.getActivityId(),"1",user.getUserId()+"");

        for (MySaleCountByProductVO mySaleCountByProductVO: mySaleCountVOs) {
           ActivityReserve reserve= reserveService.getById(mySaleCountByProductVO.getProductId());
            mySaleCountByProductVO.setProductName(reserve.getProductName());
        }
        return R.ok(mySaleCountVOs);
    }
    @ApiOperation(value = "查询我的产品业绩-已通过")
    @PostMapping("/queryMyProductSale")
    public R<List<SaleRecordVO>> queryMyProductSale(@RequestBody QueryMySaleDTO mySaleDTO) {
        List<ActivitySaleRecord>  saleRecords=saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("activity_id",mySaleDTO.getActivityId())
                .eq("user_id",SecurityUtil.getUser().getUserId()).eq("approve_status","1")
                .like("sale_time","%"+mySaleDTO.getSaleTime()+"%").eq("product_id",mySaleDTO.getProductId()));
        List<SaleRecordVO> list=new ArrayList<>();
        for (ActivitySaleRecord saleRecord:saleRecords) {
            SaleRecordVO saleRecordVO=new SaleRecordVO();
            BeanUtils.copyProperties(saleRecord,saleRecordVO);
            saleRecordVO.setFiles(JSONArray.parseArray(saleRecord.getFiles(),String.class));
            Company company1= companyService.getById(saleRecord.getShopCode());
            if(company1!=null){
                saleRecordVO.setShopName(company1.getCompayName());
            }
            list.add(saleRecordVO);
        }
        return R.ok(list);
    }

    @ApiOperation(value = "查询我的业绩根据状态")
    @PostMapping("/queryMySaleByStatus")
    public R<List<SaleRecordVO>> queryMySaleByStatus(@RequestBody QueryMySaleByStatusDTO mySaleDTO) {
        List<ActivitySaleRecord>  saleRecords=saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("activity_id",mySaleDTO.getActivityId())
                .eq("user_id",SecurityUtil.getUser().getUserId()).eq("approve_status",mySaleDTO.getApproveStatus()));
        List<SaleRecordVO> list=new ArrayList<>();
        for (ActivitySaleRecord saleRecord:saleRecords) {
            SaleRecordVO saleRecordVO=new SaleRecordVO();
            BeanUtils.copyProperties(saleRecord,saleRecordVO);
            saleRecordVO.setFiles(JSONArray.parseArray(saleRecord.getFiles(),String.class));
           Company company= companyService.getById(saleRecord.getShopCode());
           if(company!=null){
               saleRecordVO.setShopName(company.getCompayName());
           }
           list.add(saleRecordVO);
        }
        return R.ok(list);
    }


    @ApiOperation(value = "查询同事工业绩统计")
    @PostMapping("/queryStaffSaleCount")
    public R<List<StaffSaleCountVO>> queryStaffSaleCount(@RequestBody ActivityIdDTO activityIdDTO) {
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
       List<SysUser> users= userService.list(new QueryWrapper<SysUser>().eq("company_id",user.getCompanyId()).eq("user_type","0"));
        List<StaffSaleCountVO> StaffSaleCountVos=saleRecordService.queryStaffSaleCountByShop(activityIdDTO.getActivityId(),user.getCompanyId()+"","1");
        //排除自己
        for (StaffSaleCountVO staffSaleCountVo: StaffSaleCountVos) {
            if(staffSaleCountVo.getUserId()==user.getUserId()){
                StaffSaleCountVos.remove(staffSaleCountVo);
                break;
            }
        }
        //同事业绩
        List<Integer> have=new ArrayList<>();//有业绩的同事
        for (StaffSaleCountVO staffSaleCountVo: StaffSaleCountVos) {
            have.add(staffSaleCountVo.getUserId());
            SysUser shopUser=userService.getById(staffSaleCountVo.getUserId()) ;
            staffSaleCountVo.setTrueName(shopUser.getTrueName());
        }
        //没有业绩的同事
        for (SysUser user1: users) {
            if(user1.getUserId()==user.getUserId()){
               continue;
            }
            if(!have.contains(user1.getUserId())){
                StaffSaleCountVO staffSaleCountVO=new StaffSaleCountVO();
                staffSaleCountVO.setUserId(user1.getUserId());
                staffSaleCountVO.setTrueName(user1.getTrueName());
                staffSaleCountVO.setQuantity(0);
                staffSaleCountVO.setSaleMoney(new BigDecimal(0));
                StaffSaleCountVos.add(staffSaleCountVO);
            }
        }
        return R.ok(StaffSaleCountVos);
    }

    @ApiOperation(value = "查询同事产品业绩统计")
    @PostMapping("/queryStaffProductSaleCount")
    public R<List<MySaleCountByProductVO>> queryStaffProductSaleCount(@RequestBody QueryStaffProductSaleCountDTO staffProductSaleCountDTO) {
        List<MySaleCountByProductVO> mySaleCountVOs=saleRecordService.queryMySaleCountByActivityAndStatus(staffProductSaleCountDTO.getActivityId(),"1",staffProductSaleCountDTO.getStaffUserId()+"");
        for (MySaleCountByProductVO mySaleCountByProductVO: mySaleCountVOs) {
            ActivityReserve reserve= reserveService.getById(mySaleCountByProductVO.getProductId());
            mySaleCountByProductVO.setProductName(reserve.getProductName());
        }
        return R.ok(mySaleCountVOs);
    }


    @ApiOperation(value = "查询同事产品业绩")
    @PostMapping("/queryStaffProductSale")
    public R<List<SaleRecordVO>> queryStaffProductSale(@RequestBody QueryStaffSaleDTO staffSaleDTO) {
        List<ActivitySaleRecord>  saleRecords=saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("activity_id",staffSaleDTO.getActivityId())
                .eq("user_id",staffSaleDTO.getStaffUserId()).eq("approve_status","1")
                .like("sale_time","%"+staffSaleDTO.getSaleTime()+"%").eq("product_id",staffSaleDTO.getProductId()));
        List<SaleRecordVO> list=new ArrayList<>();
        for (ActivitySaleRecord saleRecord:saleRecords) {
            SaleRecordVO saleRecordVO=new SaleRecordVO();
            BeanUtils.copyProperties(saleRecord,saleRecordVO);
            saleRecordVO.setFiles(JSONArray.parseArray(saleRecord.getFiles(),String.class));
            list.add(saleRecordVO);
        }
        return R.ok(list);
    }

    @ApiOperation(value = "业绩示例图")
    @PostMapping("/exampSaleFile")
    public R<List<ExampleSaleFile>> exampSaleFile(@RequestBody ActivityIdDTO activityIdDTO) {
        List<ExampleSaleFile> exampleSaleFiles=exampleSaleFileService.list(new QueryWrapper<ExampleSaleFile>().eq("activity_id",activityIdDTO.getActivityId()));
        return R.ok(exampleSaleFiles);
    }
}

