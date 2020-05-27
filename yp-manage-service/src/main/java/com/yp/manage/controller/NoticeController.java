package com.yp.manage.controller;

import com.yp.common.utils.R;
import com.yp.dto.manage.notice.DisplayDTO;
import com.yp.dto.manage.notice.NoticeDTO;
import com.yp.dto.manage.notice.NoticePageDTO;
import com.yp.entity.Notice;
import com.yp.service.NoticeService;
import com.yp.vo.PageBean;
import com.yp.vo.manage.NoticeVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname IndexController
 * @Description 公告模块
 * @Author HCS lihaodongmail@163.com
 * @Date 2019-05-07 12:38
 * @Version 1.0
 */
@Api(tags = "公告模块")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 添加公告
     *
     * @param noticeDTO
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('sys:notice:add')")
    public R insert(@RequestBody NoticeDTO noticeDTO) {
        return R.ok(noticeService.insertNotice(noticeDTO));
    }

    /**
     * 获取公告列表集合
     *
     * @param noticePageDTO
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('sys:user:view')")
    public R<PageBean<NoticeVO>> getList(NoticePageDTO noticePageDTO) {
        return R.ok(noticeService.getNoticePage(noticePageDTO));
    }

    /**
     * 设置隐藏
     *
     * @param displayDTO
     * @return
     */
    @PostMapping("/setDisplay")
    @PreAuthorize("hasAuthority('sys:user:view')")
    public R<Boolean> setDisplay(@RequestBody DisplayDTO displayDTO) {
        Notice notice=noticeService.getById(displayDTO.getPkid());
        notice.setIsDisplay(displayDTO.getDisplay());
        return R.ok(noticeService.updateById(notice));
    }

}
