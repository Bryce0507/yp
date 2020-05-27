package com.yp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.yp.common.utils.R;
import com.yp.dto.wx.activity.ActivityIdDTO;
import com.yp.dto.wx.activity.QueryActivityListDTO;
import com.yp.dto.wx.shop.SetUserTargetDTO;
import com.yp.dto.wx.shop.UserTargetItem;
import com.yp.entity.*;
import com.yp.service.*;
import com.yp.util.SecurityUtil;
import com.yp.vo.PageBean;
import com.yp.vo.wx.activity.ActivityVO;
import com.yp.vo.wx.shop.ShopCountVO;
import com.yp.vo.wx.shop.ShopItemCount;
import com.yp.vo.wx.shop.ShopTargetVO;
import com.yp.vo.wx.shop.ShopUserTargetVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 门店模块
 * </p>
 *
 * @author hcs
 * @since 2020-02-21
 */
@Api(tags = "门店")
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ActivitySaleRecordService saleRecordService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ShopTargetService shopTargetService;
    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserTargetService userTargetService;

    @ApiOperation(value = "获取门店业绩")
    @PostMapping("/shopSaleCount")
    public R<ShopCountVO> shopSaleCount(@RequestBody ActivityIdDTO activityIdDTO) {
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
        //获取当前用户信息
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        //获取参与店铺
        List<ShopTarget> shopTargets=shopTargetService.list(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()));

        ShopCountVO shopCountVO=new ShopCountVO();
        List<ShopItemCount> shopItemCounts=new ArrayList<>();
        //参与排名店铺
        List<String> shopCodes=new ArrayList<>();
        //店铺排名
        if(activity.getIsSetTarget().equals("1")){
            shopCountVO.setOrderType(activity.getTargetUntil());
            shopItemCounts=saleRecordService.queryShopCount(activityIdDTO.getActivityId(),activity.getTargetUntil());
            for (ShopItemCount shopItemCount: shopItemCounts) {
                shopCodes.add(shopItemCount.getShopCode()+"");
                Company company=companyService.getById(shopItemCount.getShopCode());
                shopItemCount.setShopName(company.getCompayName());
                //判断是否为当前门店
                if(shopItemCount.getShopCode().equals(user.getCompanyId())){
                    shopCountVO.setTop(shopItemCounts.indexOf(shopItemCount)+1+"");
                    shopCountVO.setQuantity(shopItemCount.getQuantity());
                    shopCountVO.setSaleMoney(shopItemCount.getSaleMoney());
                    ShopTarget shopTarget=shopTargetService.getOne(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()).eq("shop_code",user.getCompanyId()));
                    shopCountVO.setTarget(shopTarget.getTarget());
                }
            }
            //添加未在排名中的店铺
            for (ShopTarget shopTarget: shopTargets) {
                if (!shopCodes.contains(shopTarget.getShopCode())){
                    Company company=companyService.getById(shopTarget.getShopCode());
                    ShopItemCount shopItemCount=new ShopItemCount();
                    shopItemCount.setShopCode(company.getPkid());
                    shopItemCount.setShopName(company.getCompayName());
                    shopItemCount.setQuantity(0);
                    shopItemCount.setSaleMoney(new BigDecimal(0));
                    //判断是否为当前门店
                    if(user.getCompanyId().equals(Integer.parseInt(shopTarget.getShopCode()))){
                        shopCountVO.setTop("0");
                        shopCountVO.setQuantity(0);
                        shopCountVO.setSaleMoney(new BigDecimal(0));
                        ShopTarget myshopTarget=shopTargetService.getOne(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()).eq("shop_code",user.getCompanyId()));
                        shopCountVO.setTarget(myshopTarget.getTarget());
                    }
                    shopItemCounts.add(shopItemCount);
                }
            }
            shopCountVO.setShopItemCounts(shopItemCounts);
        }else{
            shopCountVO.setOrderType("0");
            shopItemCounts=saleRecordService.queryShopCount(activityIdDTO.getActivityId(),"0");
            shopCountVO.setShopItemCounts(shopItemCounts);
            for (ShopItemCount shopItemCount: shopItemCounts) {
                Company company=companyService.getById(shopItemCount.getShopCode());
                shopItemCount.setShopName(company.getCompayName());
                shopCodes.add(shopItemCount.getShopCode()+"");
                //判断是否为当前门店
                if(shopItemCount.getShopCode().equals(user.getCompanyId())){
                    shopCountVO.setTop(shopItemCounts.indexOf(shopItemCount)+1+"");
                    shopCountVO.setQuantity(shopItemCount.getQuantity());
                    shopCountVO.setSaleMoney(shopItemCount.getSaleMoney());
                }
            }
            //添加未在排名中的店铺
            for (ShopTarget shopTarget: shopTargets) {
                if (!shopCodes.contains(shopTarget.getShopCode())){
                    Company company=companyService.getById(shopTarget.getShopCode());
                    ShopItemCount shopItemCount=new ShopItemCount();
                    shopItemCount.setShopCode(company.getPkid());
                    shopItemCount.setShopName(company.getCompayName());
                    shopItemCount.setQuantity(0);
                    shopItemCount.setSaleMoney(new BigDecimal(0));
                    //判断是否为当前门店
                    if(user.getCompanyId().equals(Integer.parseInt(shopTarget.getShopCode()))){
                        shopCountVO.setTop("0");
                        shopCountVO.setQuantity(0);
                        shopCountVO.setSaleMoney(new BigDecimal(0.00));
                        ShopTarget myshopTarget=shopTargetService.getOne(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()).eq("shop_code",user.getCompanyId()));
                        shopCountVO.setTarget(myshopTarget.getTarget());
                    }
                    shopItemCounts.add(shopItemCount);
                }
            }
            shopCountVO.setShopItemCounts(shopItemCounts);
        }
        //判断该门店是否有参与活动
        ShopTarget shopTarget=shopTargetService.getOne(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()).eq("shop_code",user.getCompanyId()));
        if(shopTarget!=null){
            //判断是否具有店长权限
            List<SimpleGrantedAuthority> a= (List<SimpleGrantedAuthority>) SecurityUtil.getUser().getAuthorities();
            //默认不是店长
            shopCountVO.setIsDirector("1");
            for (SimpleGrantedAuthority simpleGrantedAuthority:a) {
                if(simpleGrantedAuthority.getAuthority().equals("sale:set:tagert")){
                    shopCountVO.setIsDirector("0");
                }
            }
        }else{
            //没有参与该门店
            shopCountVO.setIsDirector("1");
        }
        return R.ok(shopCountVO);
    }


    @ApiOperation(value = "门店指标")
    @PostMapping("/shopTarget")
    public R<ShopTargetVO> shopTarget(@RequestBody ActivityIdDTO activityIdDTO) {
        Activity activity=activityService.getById(activityIdDTO.getActivityId());
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        ShopTarget shopTarget=shopTargetService.getOne(new QueryWrapper<ShopTarget>().eq("activity_id",activity.getPkid()).eq("shop_code",user.getCompanyId()));
        ShopTargetVO shopTargetVO=new ShopTargetVO();
        if(shopTarget!=null){
            BeanUtils.copyProperties(shopTarget,shopTargetVO);
        }
        return R.ok(shopTargetVO);
    }

    @ApiOperation(value = "获取店员指标")
    @PostMapping("/getShopUserTarget")
    public R<List<ShopUserTargetVO>> getShopUser(@RequestBody ActivityIdDTO activityIdDTO) {
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        List<SysUser> users=userService.list(new QueryWrapper<SysUser>().eq("company_id",user.getCompanyId()).eq("user_type","0"));
        List<ShopUserTargetVO> shopUserTargetVOS=new ArrayList<>();
        for (SysUser sysUser: users) {
            if (sysUser.getUserId().equals(user.getUserId())){
                users.remove(sysUser);
                break;
            }
        }
        for (SysUser sysUser: users) {
            ShopUserTargetVO shopUserTargetVO=new ShopUserTargetVO();
            shopUserTargetVO.setTrueName(sysUser.getTrueName());
            shopUserTargetVO.setUserId(sysUser.getUserId());
            UserTarget userTarget=userTargetService.getOne(new QueryWrapper<UserTarget>().eq("user_id",sysUser.getUserId()).eq("activity_id",activityIdDTO.getActivityId()));
            if(userTarget!=null){
                shopUserTargetVO.setTarget(userTarget.getTarget());
            }else{
                shopUserTargetVO.setTarget(0);
            }
            shopUserTargetVOS.add(shopUserTargetVO);
        }
        return R.ok(shopUserTargetVOS);
    }

    @ApiOperation(value = "设置店员指标")
    @PostMapping("/setUserTarget")
    public R setUserTarget(@RequestBody SetUserTargetDTO userTargetDTO) {
        SysUser user=userService.getById(SecurityUtil.getUser().getUserId()) ;
        for (UserTargetItem targetItem: userTargetDTO.getUserTargetItems()) {
            UserTarget userTarget=userTargetService.getOne(new QueryWrapper<UserTarget>().eq("user_id",targetItem.getUserId()).eq("activity_id",userTargetDTO.getActivityId()));
           if(userTarget!=null){
               userTarget.setTarget(targetItem.getTarget());
               userTargetService.updateById(userTarget);
           }else{
               userTarget=new UserTarget();
               userTarget.setActivityId(userTargetDTO.getActivityId());
               userTarget.setTarget(targetItem.getTarget());
               userTarget.setShopCode(user.getCompanyId()+"");
               userTarget.setUserId(targetItem.getUserId());
               userTarget.setUnit(targetItem.getUnit());
               userTargetService.save(userTarget);
           }
        }
        return R.ok();
    }
}

