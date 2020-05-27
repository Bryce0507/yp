package com.yp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.dto.manage.notice.NoticeDTO;
import com.yp.dto.manage.notice.NoticePageDTO;
import com.yp.entity.Notice;
import com.yp.entity.SysUser;
import com.yp.vo.PageBean;
import com.yp.vo.manage.NoticeVO;

/**
 * <p>
 * 公告 服务类
 * </p>
 *
 * @author generator
 * @since 2020-02-23
 */
public interface NoticeService extends IService<Notice> {
    /**
     * 添加公告
     * @param noticeDTO
     * @return
     */
    boolean insertNotice(NoticeDTO noticeDTO);

    /**
     * 分页查询公告
     * @param noticePageDTO
     * @return
     */
    PageBean<NoticeVO> getNoticePage(NoticePageDTO noticePageDTO);

}
