package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.NoticeReceive;


/**
 * (NoticeReceive)表服务接口
 *
 * @author lsh
 * @since 2023-03-28 17:31:08
 */
public interface NoticeReceiveService extends IService<NoticeReceive> {

    int saveNoticeReceive(NoticeReceive noticeReceive);

    PageInfo<NoticeReceive> queryNoticeReceive(NoticeReceive noticeReceive);

    int updateNoticeReceive(NoticeReceive noticeReceive);

}
