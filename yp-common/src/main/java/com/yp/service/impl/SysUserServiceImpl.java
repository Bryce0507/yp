package com.yp.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.common.exception.PreBaseException;
import com.yp.dto.manage.user.AddUserDTO;
import com.yp.dto.manage.user.UserPageDTO;
import com.yp.entity.*;
import com.yp.dto.wx.WxBindDTO;
import com.yp.dao.SysUserMapper;
import com.yp.security.PreSecurityUser;
import com.yp.service.*;
import com.yp.util.JwtUtil;
import com.yp.util.PreUtil;
import com.yp.util.SecurityUtil;
import com.yp.vo.PageBean;
import com.yp.vo.manage.NoticeVO;
import com.yp.vo.manage.user.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ActivitySaleRecordService saleRecordService;

    @Override
    public PageBean<UserVO> getUsersWithRolePage(UserPageDTO userDTO) {
        Page<SysUser> page=new Page(userDTO.getCurrent(),userDTO.getSize());
        QueryWrapper<SysUser> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("user_type",userDTO.getUserType());
        queryWrapper.ne("is_system","0");
        if(userDTO.getCompanyId()!=null){
            queryWrapper.eq("company_id",userDTO.getCompanyId());
        }
        if(userDTO.getDeptId()!=null){
            queryWrapper.eq("dept_id",userDTO.getDeptId());
        }
        if(StringUtils.isNotEmpty(userDTO.getTrueName().trim())){
            queryWrapper.like("true_name","%"+userDTO.getTrueName()+"%");
        }
        page=baseMapper.selectPage(page,queryWrapper);
        PageBean<UserVO> resultPage=new PageBean<UserVO>();
        List<UserVO> list=new ArrayList<>();
        for (SysUser sysUser: page.getRecords()) {
            UserVO userVO=new UserVO();
            BeanUtils.copyProperties(sysUser,userVO);
            SysDept dept=deptService.getById(sysUser.getDeptId());
            if(dept!=null){
                userVO.setDeptName(dept.getName());
            }
            Company company=companyService.getById(sysUser.getCompanyId());
            if(company!=null){
                userVO.setCompany(company.getCompayName());
            }
            List<SysRole> roleList=userRoleService.selectUserRoleListByUserId(userVO.getUserId()).stream().map(userrole ->{
               SysRole role= roleService.getById(userrole.getRoleId());
                return role;
            }).collect(Collectors.toList());
            userVO.setRoleList(roleList);
            list.add(userVO);
        }
        resultPage.setRecords(list);
        resultPage.setCurrent(page.getCurrent());
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return resultPage;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUser(AddUserDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        // 默认密码 123456
        sysUser.setPassword(PreUtil.encode("123456"));
        //sysUser.setPhone(userDto.getUsername());
        baseMapper.insert(sysUser);
        List<SysUserRole> userRoles = userDto.getRoleList().stream().map(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(item);
            sysUserRole.setUserId(sysUser.getUserId());
            return sysUserRole;
        }).collect(Collectors.toList());
        return userRoleService.saveBatch(userRoles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(AddUserDTO userDto) {
        SysUser sysUser = baseMapper.selectById(userDto.getUserId());
        BeanUtils.copyProperties(userDto, sysUser);
        sysUser.setPhone(userDto.getPhone());
        if(!sysUser.getCompanyId().equals(userDto.getCompanyId())){
           List<ActivitySaleRecord> activitySaleRecords= saleRecordService.list(new QueryWrapper<ActivitySaleRecord>().eq("user_id",sysUser.getUserId()));
            for (ActivitySaleRecord saleRecord: activitySaleRecords) {
                saleRecord.setShopCode(userDto.getCompanyId());
            }
            saleRecordService.saveBatch(activitySaleRecords);
        }
        baseMapper.updateById(sysUser);
        userRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, sysUser.getUserId()));
        List<SysUserRole> userRoles = userDto.getRoleList().stream().map(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(item);
            sysUserRole.setUserId(sysUser.getUserId());
            return sysUserRole;
        }).collect(Collectors.toList());
        return userRoleService.saveBatch(userRoles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeUser(Integer userId) {
        baseMapper.deleteById(userId);
        return userRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
    }

    @Override
    public boolean restPass(Integer userId) {
        SysUser user=baseMapper.selectById(userId);
        user.setPassword(PreUtil.encode("123456"));
        return baseMapper.updateById(user) > 0;
    }

    @Override
    public SysUser findByUserInfoName(String username) {
        SysUser sysUser = baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUsername, username));
        // 获取部门
        Company company=companyService.getById(sysUser.getCompanyId());
        if(company!=null){
            sysUser.setCompany(company.getCompayName());
        }
       SysDept dept=deptService.getById(sysUser.getDeptId());
        if (dept!=null){
            sysUser.setDeptName(dept.getName());
        }
        return sysUser;
    }

    @Override
    public Set<String> findPermsByUserId(Integer userId) {
        return menuService.findPermsByUserId(userId).stream().filter(StringUtils::isNotEmpty).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findRoleIdByUserId(Integer userId) {
        return userRoleService
                .selectUserRoleListByUserId(userId)
                .stream()
                .map(sysUserRole -> "ROLE_" + sysUserRole.getRoleId())
                .collect(Collectors.toSet());
    }


    @Override
    public String login(String username, String password) {
        //用户验证
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        PreSecurityUser userDetail = (PreSecurityUser) authentication.getPrincipal();
        return JwtUtil.generateToken(userDetail);
    }

    @Override
    public boolean updateUserInfo(SysUser sysUser) {
        return baseMapper.updateById(sysUser) > 0;
    }

    @Override
    public SysUser findSecurityUserByUser(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> select = Wrappers.<SysUser>lambdaQuery()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPassword);
        if (StrUtil.isNotEmpty(sysUser.getUsername())) {
            if(sysUser.getUserType().equals("0")){
                select.eq(SysUser::getUsername, sysUser.getUsername()).eq(SysUser::getUserType, sysUser.getUserType());
            }else{
                select.eq(SysUser::getUserType, sysUser.getUserType()).and(i -> i.eq(SysUser::getUsername, sysUser.getUsername()).or().eq(SysUser::getPhone,sysUser.getUsername()));
            }
        } else if (StrUtil.isNotEmpty(sysUser.getPhone())) {
            select.eq(SysUser::getPhone, sysUser.getPhone());
        } else if (ObjectUtil.isNotNull(sysUser.getUserId()) && sysUser.getUserId() != 0) {
            select.eq(SysUser::getUserId, sysUser.getUserId());
        }
        return baseMapper.selectOne(select);
    }

    @Override
    public boolean bind(WxBindDTO wxBindDTO) {
        // 进行账号校验
        SysUser sysUser =baseMapper.selectById(SecurityUtil.getUser().getUserId());
        if (ObjectUtil.isNull(sysUser)) {
            throw new PreBaseException("账号不存在");
        }
        if(wxBindDTO.getOpenId().equals(sysUser.getOpenId())||sysUser.getOpenId()==null||sysUser.getOpenId().isEmpty()){
            sysUser.setNickName(wxBindDTO.getNickName());
            sysUser.setHeadimgurl(wxBindDTO.getHeadimgurl());
            sysUser.setOpenId(wxBindDTO.getOpenId());
            sysUser.setSex(wxBindDTO.getSex());
            sysUser.setAddress(wxBindDTO.getAddress());
            //将业务系统的用户与社交用户绑定
            baseMapper.updateById(sysUser);
        }
        return true;
    }
}
