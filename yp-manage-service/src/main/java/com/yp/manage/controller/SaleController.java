package com.yp.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yp.common.constant.SaleOrderStatus;
import com.yp.common.utils.EntityConverterUtil;
import com.yp.common.utils.R;
import com.yp.dto.manage.sale.*;
import com.yp.dto.wx.activity.ActivityIdDTO;
import com.yp.dto.wx.sale.SaleIdDTO;
import com.yp.entity.*;
import com.yp.manage.util.wx.MessageSendUtil;
import com.yp.manage.util.wx.dto.WxApprovalRequDTO;
import com.yp.service.*;
import com.yp.vo.PageBean;
import com.yp.vo.manage.sale.*;
import com.yp.vo.wx.activity.ActivityVO;
import com.yp.vo.wx.sale.MySaleCountVO;
import com.yp.vo.wx.shop.ShopItemCount;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname IndexController
 * @Description 业绩模块
 * @Author HCS lihaodongmail@163.com
 * @Date 2019-05-07 12:38
 * @Version 1.0
 */
@Api(tags = "业绩模块")
@RestController
@RequestMapping("/sale")
public class SaleController {

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
    @Autowired
    private ActivityCircleService circleService;
    @Autowired
    private ActivitySaleOrderService activitySaleOrderService;


    @ApiOperation(value = "参与店铺业绩")
    @PostMapping("/partake")
    @PreAuthorize("hasAuthority('sys:sale:partake')")
    public R<List<PartakeSaleVO>> partake(@RequestBody ActivityShopNameDTO activityShopNameDTO) {
        List<PartakeSaleVO> shopCountVO=new ArrayList<>();
        Activity activity=activityService.getById(activityShopNameDTO.getActivityId());
        //获取参与店铺
        List<ShopTarget> shopTargets=shopTargetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()));
        //已有业绩门店IDs
        List<String> shopCodes=new ArrayList<>();
        //店铺排名
        List<ShopItemCount> shopItemCounts=saleRecordService.queryShopCount(activityShopNameDTO.getActivityId(),activity.getTargetUntil());
        for (ShopItemCount shopItemCount: shopItemCounts) {
            //添加有业绩的门店
            shopCodes.add(shopItemCount.getShopCode()+"");
            PartakeSaleVO saleVO=new PartakeSaleVO();
            Company company=companyService.getById(shopItemCount.getShopCode());
            BeanUtils.copyProperties(company,saleVO);
            saleVO.setUnit(activity.getTargetUntil());
            ShopTarget target= shopTargetService.getOne(new QueryWrapper<ShopTarget>().eq("shop_code",shopItemCount.getShopCode()).eq("activity_id",activity.getPkid()));
            saleVO.setTarget(target.getTarget());
            if(activity.getIsSetShopTarget().equals("1")){
                if(activity.getTargetUntil().equals("0")){
                    saleVO.setSaleTaget(shopItemCount.getQuantity());

                    double percentage =((float)shopItemCount.getQuantity())/(float)target.getTarget();
                    saleVO.setPercentage((int) ( Math.round(percentage*100)));
                }else{
                    saleVO.setSaleTaget(shopItemCount.getSaleMoney().intValue());
                    double percentage =((float)shopItemCount.getSaleMoney().intValue())/(float)target.getTarget();
                    saleVO.setPercentage((int) ( Math.round(percentage*100)));
                }
            }else{
                saleVO.setSaleTaget(shopItemCount.getQuantity());
                saleVO.setPercentage(null);
            }
            shopCountVO.add(saleVO);
        }
        //添加未在排名中的店铺
        for (ShopTarget shopTarget: shopTargets) {
            if (!shopCodes.contains(shopTarget.getShopCode())){
                PartakeSaleVO saleVO=new PartakeSaleVO();
                Company company=companyService.getById(shopTarget.getShopCode());
                BeanUtils.copyProperties(company,saleVO);
                saleVO.setUnit(activity.getTargetUntil());
                saleVO.setTarget(shopTarget.getTarget());
                if(activity.getIsSetShopTarget().equals("1")){
                    saleVO.setPercentage(0);
                }else{
                    saleVO.setPercentage(null);
                }
                saleVO.setSaleTaget(0);
                shopCountVO.add(saleVO);
            }
        }
        if(activityShopNameDTO.getShopName()!=null&&StringUtils.isNotEmpty(activityShopNameDTO.getShopName())){
            List<PartakeSaleVO> newshopCountVO= shopCountVO.stream().filter(shopCount -> shopCount.getCompayName().contains(activityShopNameDTO.getShopName())).collect(Collectors.toList());
            return R.ok(newshopCountVO);
        }
        return R.ok(shopCountVO);
    }

    @ApiOperation(value = "参与店铺日业绩")
    @PostMapping("/partakeDay")
    @PreAuthorize("hasAuthority('sys:sale:partakeDay')")
    public R<List<PartakeDaySaleVO>> partakeDay(@RequestBody ActivityShopDTO activityShopDTO) {
        List<PartakeDaySaleVO> daySaleVOS=saleRecordService.queryPartakeDaySale(activityShopDTO.getActivityId(),activityShopDTO.getCompanyId());
        Activity activity=activityService.getById(activityShopDTO.getActivityId());
        for (PartakeDaySaleVO partakeDaySaleVO: daySaleVOS) {
            partakeDaySaleVO.setUnit(activity.getTargetUntil());
        }
        return R.ok(daySaleVOS);
    }

    @ApiOperation(value = "商品销售业绩统计")
    @PostMapping("/product")
    @PreAuthorize("hasAuthority('sys:sale:product')")
    public R<List<ProductSaleVO>> product(@RequestBody ActivityProductNameDTO activityIdDTO) {
        List<ProductSaleVO> daySaleVOS=saleRecordService.queryProductSale(activityIdDTO.getActivityId());
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
        for (ProductSaleVO productSaleVO: daySaleVOS) {
            ActivityReserve reserve=reserveService.getById(productSaleVO.getProductId());
             productSaleVO.setUnit(activity.getTargetUntil());
             productSaleVO.setProductName(reserve.getProductName());
        }
        if(activityIdDTO.getProductName()!=null&&StringUtils.isNotEmpty(activityIdDTO.getProductName())){
            List<ProductSaleVO> newshopCountVO= daySaleVOS.stream().filter(shopCount -> shopCount.getProductName().contains(activityIdDTO.getProductName())).collect(Collectors.toList());
            return R.ok(newshopCountVO);
        }
        return R.ok(daySaleVOS);
    }

    @ApiOperation(value = "商品销售日业绩统计")
    @PostMapping("/productDay")
    @PreAuthorize("hasAuthority('sys:sale:productDay')")
    public R<List<ProductDaySaleVO>> productDay(@RequestBody ActivityProductDTO activityProductDTO) {
        List<ProductDaySaleVO> daySaleVOS=saleRecordService.queryProductDaySale(activityProductDTO.getActivityId(),activityProductDTO.getProductId());
        Activity activity=activityService.getById(activityProductDTO.getActivityId());
        for (ProductDaySaleVO productDaySaleVO: daySaleVOS) {
            productDaySaleVO.setUnit(activity.getTargetUntil());
        }
        return R.ok(daySaleVOS);
    }

    @ApiOperation(value = "员工业绩统计")
    @PostMapping("/staff")
    @PreAuthorize("hasAuthority('sys:sale:staff')")
    public R<List<StaffSaleVO>> staff(@RequestBody ActivityUserNameDTO activityIdDTO) {
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
        List<StaffSaleVO> staffSaleVOS=saleRecordService.queryStaffSale(activityIdDTO.getActivityId(),activity.getTargetUntil());
        for (StaffSaleVO staffSaleVO: staffSaleVOS) {
            SysUser sysUser=userService.getById(staffSaleVO.getUserId());
            if(sysUser!=null){
                staffSaleVO.setTrueName(sysUser.getTrueName());
                staffSaleVO.setPhone(sysUser.getPhone());
                Company company=companyService.getById(sysUser.getCompanyId());
                if(company!=null){
                    staffSaleVO.setCompanyName(company.getCompayName());
                }
                staffSaleVO.setUnit(activity.getTargetUntil());
            }
        }
        if(activityIdDTO.getTrueName()!=null&&StringUtils.isNotEmpty(activityIdDTO.getTrueName())){
            List<StaffSaleVO> newshopCountVO= staffSaleVOS.stream().filter(shopCount -> shopCount.getTrueName().contains(activityIdDTO.getTrueName())).collect(Collectors.toList());
            return R.ok(newshopCountVO);
        }
        return R.ok(staffSaleVOS);
    }


    @ApiOperation(value = "员工业绩统计(新)")
    @PostMapping("/staffCount")
//    @PreAuthorize("hasAuthority('sys:sale:staff')")
    public R<List<StaffSaleVO>> staffCount(@RequestBody ActivityUserNameDTO activityIdDTO) {
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
//        List<StaffSaleVO> staffSaleVOS=saleRecordService.queryStaffSale(activityIdDTO.getActivityId(),activity.getTargetUntil());
        List<StaffSaleVO> staffSaleVOS = activitySaleOrderService.queryStaffSale(activityIdDTO.getActivityId(), activity.getTargetUntil());
        for (StaffSaleVO staffSaleVO: staffSaleVOS) {
            SysUser sysUser=userService.getById(staffSaleVO.getUserId());
            if(sysUser!=null){
                staffSaleVO.setTrueName(sysUser.getTrueName());
                staffSaleVO.setPhone(sysUser.getPhone());
                Company company=companyService.getById(sysUser.getCompanyId());
                if(company!=null){
                    staffSaleVO.setCompanyName(company.getCompayName());
                }
                staffSaleVO.setUnit(activity.getTargetUntil());
            }
        }
        if(activityIdDTO.getTrueName()!=null&&StringUtils.isNotEmpty(activityIdDTO.getTrueName())){
            List<StaffSaleVO> newshopCountVO= staffSaleVOS.stream().filter(shopCount -> shopCount.getTrueName().contains(activityIdDTO.getTrueName())).collect(Collectors.toList());
            return R.ok(newshopCountVO);
        }
        return R.ok(staffSaleVOS);
    }

    @ApiOperation(value = "员工业绩")
    @PostMapping("/staffSale")
    @PreAuthorize("hasAuthority('sys:sale:staffSale')")
    public R<PageBean<SaleRecordVO>> staffSale(@RequestBody StaffPageDTO staffPageDTO) {
        Page<ActivitySaleRecord> page=new Page(staffPageDTO.getCurrent(),staffPageDTO.getSize());
//        QueryWrapper queryWrapper= new QueryWrapper<ActivitySaleRecord>().eq("activity_id",staffPageDTO.getActivityId());
//        if(staffPageDTO.getUserId()!=null && StringUtils.isNotEmpty(staffPageDTO.getUserId())){
//            queryWrapper.eq("user_id",staffPageDTO.getUserId());
//        }
//        if(staffPageDTO.getApproveStatus()!=null && StringUtils.isNotEmpty(staffPageDTO.getApproveStatus())){
//            queryWrapper.eq("approve_status",staffPageDTO.getApproveStatus());
//        }
//        if(staffPageDTO.getSaleTime()!=null && StringUtils.isNotEmpty(staffPageDTO.getSaleTime())){
//            queryWrapper.like("sale_time","%"+staffPageDTO.getSaleTime()+"%");
//        }
//        if(staffPageDTO.getProductId()!=null){
//            queryWrapper.eq("product_id",staffPageDTO.getProductId());
//        }
//        queryWrapper.orderByDesc("sale_time");
        page= saleRecordService.staffSale(page,staffPageDTO);

        PageBean<SaleRecordVO> resultPage=new PageBean<SaleRecordVO>();
        List<SaleRecordVO> list=new ArrayList<>();
        for (ActivitySaleRecord saleRecord: page.getRecords()) {
            SaleRecordVO saleRecordVO=new SaleRecordVO();
            BeanUtils.copyProperties(saleRecord,saleRecordVO);
            saleRecordVO.setFiles(JSONArray.parseArray(saleRecord.getFiles(),String.class));
            Company company1= companyService.getById(saleRecord.getShopCode());
            if(company1!=null){
                saleRecordVO.setShopName(company1.getCompayName());
            }
            SysUser sysUser=userService.getById(saleRecord.getUserId());
            if(sysUser!=null){
                saleRecordVO.setUserName(sysUser.getTrueName());
            }
            list.add(saleRecordVO);
        }
        resultPage.setRecords(list);
        resultPage.setCurrent(page.getCurrent());
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return R.ok(resultPage);
    }

    @ApiOperation(value = "员工业绩(新)")
    @PostMapping("/staffSaleOrder")
//    @PreAuthorize("hasAuthority('sys:sale:staffSale')")
    public R<PageBean<SaleOrderVO>> staffSaleOrder(@RequestBody StaffPageDTO staffPageDTO) {
        Page<ActivitySaleOrder> page=new Page(staffPageDTO.getCurrent(),staffPageDTO.getSize());

        page= activitySaleOrderService.staffSale(page,staffPageDTO);

        PageBean<SaleOrderVO> resultPage=new PageBean<SaleOrderVO>();
        List<SaleOrderVO> list=new ArrayList<>();
        for (ActivitySaleOrder activitySaleOrder: page.getRecords()) {
//            SaleOrderVO saleOrderVO=new SaleOrderVO();
//            BeanUtils.copyProperties(activitySaleOrder,saleOrderVO);
            SaleOrderVO saleOrderVO = EntityConverterUtil.convert(activitySaleOrder, SaleOrderVO.class);
            saleOrderVO.setFiles(JSONArray.parseArray(activitySaleOrder.getFiles(),String.class));
            List<ActivitySaleRecord> activitySaleRecordList = saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("sale_order_id", activitySaleOrder.getPkid()));
            List<ProductDetailVO> productDetailList = EntityConverterUtil.convertList(activitySaleRecordList, ProductDetailVO.class);
            saleOrderVO.setProductDetailList(productDetailList);
            list.add(saleOrderVO);
        }
        resultPage.setRecords(list);
        resultPage.setCurrent(page.getCurrent());
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return R.ok(resultPage);
    }

    @ApiOperation(value = "单个员工业绩统计")
    @PostMapping("/staffSalecount")
    @PreAuthorize("hasAuthority('sys:sale:staffSalecount')")
    public R<List<MySaleCountVO>> staffSale(@RequestBody ActivityUserIdDTO activityUserIdDTO) {
        List<MySaleCountVO> mySaleCountVOs=saleRecordService.queryMySaleCountByActivity(activityUserIdDTO.getActivityId(),activityUserIdDTO.getUserId()+"");
        return R.ok(mySaleCountVOs);
    }


    @ApiOperation(value = "业绩审核通过")
    @PostMapping("/approve")
    @PreAuthorize("hasAuthority('sys:sale:approve')")
    public R approve(@RequestBody SaleIdDTO saleIdDTO) {
        ActivitySaleRecord saleRecord=saleRecordService.getById(saleIdDTO.getSaleId());
        if(!saleRecord.getApproveStatus().equals("0")){
            return R.error("该业绩不是待审核，不能通过");
        }
        saleRecord.setApproveStatus("1");
        boolean flag=saleRecordService.updateById(saleRecord);
        if(flag){
            //添加圈子消息
            ActivityCircle activityCircle=new ActivityCircle();
            activityCircle.setUserId(saleRecord.getUserId());
            activityCircle.setActivityId(saleRecord.getActivityId());
            activityCircle.setSaleId(saleRecord.getPkid());
            activityCircle.setCreateTime(new Date());
            circleService.save(activityCircle);
            //TODO 发送微信通知
             try {
                 WxApprovalRequDTO approvalRequDTO=new WxApprovalRequDTO();
                 SysUser user=userService.getById(saleRecord.getUserId());
                 approvalRequDTO.setApproverStatus("审核通过");
                 approvalRequDTO.setOpenId(user.getOpenId());
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                 approvalRequDTO.setDate(sdf.format(new Date()));
                 approvalRequDTO.setDesc("您的业绩已审核完毕，请注意查看");
                 approvalRequDTO.setReason("");
                 MessageSendUtil.sendApprovalMessage(approvalRequDTO);
             }catch (Exception e){

             }
            return R.ok();
        }else{
            return R.error("业绩审核失败");
        }
    }

    @ApiOperation(value = "业绩审核驳回")
    @PostMapping("/refund")
    @PreAuthorize("hasAuthority('sys:sale:refund')")
    public R refund(@RequestBody SaleRefundDTO refundDTO) {
        ActivitySaleRecord saleRecord=saleRecordService.getById(refundDTO.getSaleId());
        if(!saleRecord.getApproveStatus().equals("0")){
            return R.error("该业绩不是待审核，不能驳回");
        }
        saleRecord.setApproveStatus("2");
        saleRecord.setOverruleReason(refundDTO.getOverruleReason());
        boolean flag=saleRecordService.updateById(saleRecord);
        if(flag){
            try {
                WxApprovalRequDTO approvalRequDTO=new WxApprovalRequDTO();
                SysUser user=userService.getById(saleRecord.getUserId());
                approvalRequDTO.setApproverStatus("驳回");
                approvalRequDTO.setOpenId(user.getOpenId());
                approvalRequDTO.setDesc("您的业绩已审核完毕，请注意查看");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                approvalRequDTO.setDate(sdf.format(new Date()));
                approvalRequDTO.setReason(refundDTO.getOverruleReason());
                MessageSendUtil.sendApprovalMessage(approvalRequDTO);
            }catch (Exception e){

            }
            return R.ok();
        }else{
            return R.error("业绩审核失败");
        }
    }

    @ApiOperation(value = "销售单业绩审核")
    @PostMapping("/saleOrderAudit")
//    @PreAuthorize("hasAuthority('sys:sale:saleOrderAudit')")
    public R saleOrderAudit(@RequestBody SaleOrderAuditDTO saleOrderAuditDTO) {

        ActivitySaleOrder saleOrder = activitySaleOrderService.getById(saleOrderAuditDTO.getSaleOrderId());
        if (!saleOrder.getSaleOrderStatus().equals(SaleOrderStatus.AUDIT.getValue())) {
            return R.error("该销售订单不是待审核状态，不能进行审核操作！");
        }
        //审核通过的商品销售记录
        List<ProductAuditDTO> approveProductList = saleOrderAuditDTO.getProductAuditDTOList().stream().filter(productAuditDTO -> productAuditDTO.getApproveStatus().equals(1)).collect(Collectors.toList());
        //审核驳回的商品销售记录
        List<ProductAuditDTO> rejectProductList = saleOrderAuditDTO.getProductAuditDTOList().stream().filter(productAuditDTO -> productAuditDTO.getApproveStatus().equals(2)).collect(Collectors.toList());

        //对销售单的状态进行修改
        if (approveProductList.size() == 0) {
            saleOrder.setSaleOrderStatus(SaleOrderStatus.REJECT.getValue());
        } else if (approveProductList.size() == saleOrderAuditDTO.getProductAuditDTOList().size()) {
            saleOrder.setSaleOrderStatus(SaleOrderStatus.APPROVE.getValue());
        } else {
            saleOrder.setSaleOrderStatus(SaleOrderStatus.PART_APPROVE.getValue());
        }
        activitySaleOrderService.updateById(saleOrder);


        //对审核通过的产品销售记录做修改
        if (!CollectionUtils.isEmpty(approveProductList)) {
            List<ActivitySaleRecord> saleRecordList = saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().in("pkid", approveProductList.stream().map(ProductAuditDTO::getSaleRecordId).collect(Collectors.toList())));
            saleRecordList.forEach(activitySaleRecord -> activitySaleRecord.setApproveStatus("1"));
            boolean flag = saleRecordService.updateBatchById(saleRecordList);
            if (flag) {
                //添加圈子消息
                List<ActivityCircle> activityCircleList = new ArrayList<>();
                for (ActivitySaleRecord saleRecord : saleRecordList) {
                    ActivityCircle activityCircle = new ActivityCircle();
                    activityCircle.setUserId(saleRecord.getUserId());
                    activityCircle.setActivityId(saleRecord.getActivityId());
                    activityCircle.setSaleId(saleRecord.getPkid());
                    activityCircle.setCreateTime(new Date());
                    activityCircleList.add(activityCircle);
                }
                circleService.saveBatch(activityCircleList);
            }
        }

        //对审核驳回的产品销售记录做修改
        if (!CollectionUtils.isEmpty(rejectProductList)) {
            List<ActivitySaleRecord> saleRecordList = saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().in("pkid", rejectProductList.stream().map(ProductAuditDTO::getSaleRecordId).collect(Collectors.toList())));
            saleRecordList.forEach(activitySaleRecord -> activitySaleRecord.setApproveStatus("2"));
            saleRecordService.updateBatchById(saleRecordList);
        }

        if (!CollectionUtils.isEmpty(approveProductList) && !CollectionUtils.isEmpty(rejectProductList)) {
            try {
                WxApprovalRequDTO approvalRequDTO = new WxApprovalRequDTO();
                SysUser user = userService.getById(saleOrder.getUserId());
                if (saleOrder.getSaleOrderStatus().equals(SaleOrderStatus.APPROVE.getValue())) {
                    approvalRequDTO.setApproverStatus("审核通过");
                } else if (saleOrder.getSaleOrderStatus().equals(SaleOrderStatus.PART_APPROVE.getValue())) {
                    approvalRequDTO.setApproverStatus("部分审核通过");
                } else if (saleOrder.getSaleOrderStatus().equals(SaleOrderStatus.REJECT.getValue())) {
                    approvalRequDTO.setApproverStatus("驳回");
                }
                approvalRequDTO.setOpenId(user.getOpenId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                approvalRequDTO.setDate(sdf.format(new Date()));
                approvalRequDTO.setDesc("您的业绩销售订单已审核完毕，请注意查看");
                approvalRequDTO.setReason("");
                MessageSendUtil.sendApprovalMessage(approvalRequDTO);
            } catch (Exception e) {

            }
        }

        return R.ok();
    }

}
