package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.NoticeReceive;
import com.lsh.mapper.NoticeReceiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.NoticeReceiveService;

/**
 * (NoticeReceive)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:31:08
 */
@Service("noticeReceiveService")
public class NoticeReceiveServiceImpl extends ServiceImpl<NoticeReceiveMapper, NoticeReceive> implements NoticeReceiveService {
    @Autowired
    private NoticeReceiveMapper noticeReceiveMapper;
    @Override
    public int saveNoticeReceive(NoticeReceive noticeReceive) {
       return  noticeReceiveMapper.saveNoticeReceive(noticeReceive);

    }
}
