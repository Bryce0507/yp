package com.yp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yp.common.utils.BeanUtil;
import com.yp.dao.NoticeMapper;
import com.yp.dto.manage.notice.NoticeDTO;
import com.yp.dto.manage.notice.NoticePageDTO;
import com.yp.entity.Activity;
import com.yp.entity.Notice;
import com.yp.service.NoticeService;
import com.yp.vo.PageBean;
import com.yp.vo.manage.NoticeVO;
import com.yp.vo.wx.activity.ActivityVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 公告 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-02-23
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertNotice(NoticeDTO noticeDTO) {
        Notice notice=new Notice();
        notice.setTitle(noticeDTO.getTitle());
        notice.setContext(noticeDTO.getContext());
        notice.setType(noticeDTO.getType());
        notice.setCreateTime(new Date());
        int row=baseMapper.insert(notice);
        return row>0;
    }

    @Override
    public PageBean<NoticeVO> getNoticePage(NoticePageDTO noticePageDTO) {
        Page<Notice> page=new Page(noticePageDTO.getCurrent(),noticePageDTO.getSize());
        QueryWrapper<Notice> queryWrapper= new QueryWrapper<>();
        if(StringUtils.isNotEmpty(noticePageDTO.getTitle().trim())){
            queryWrapper.like("title","%"+noticePageDTO.getTitle()+"%");
        }
        page=baseMapper.selectPage(page,queryWrapper);
        PageBean<NoticeVO> resultPage=new PageBean<NoticeVO>();
        List<NoticeVO> list=new ArrayList<>();
        for (Notice notice: page.getRecords()) {
            NoticeVO noticeVO=new NoticeVO();
            BeanUtils.copyProperties(notice,noticeVO);
            list.add(noticeVO);
        }
        resultPage.setRecords(list);
        resultPage.setCurrent(page.getCurrent());
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return resultPage;
    }
}
