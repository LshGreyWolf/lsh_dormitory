package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<NoticeReceive> queryNoticeReceive(NoticeReceive noticeReceive) {
        if(noticeReceive != null && noticeReceive.getPage() != null){
            PageHelper.startPage(noticeReceive.getPage(),noticeReceive.getLimit());
        }
        return new PageInfo<NoticeReceive>(noticeReceiveMapper.queryNoticeReceive(noticeReceive));
    }

    @Override
    public int updateNoticeReceive(NoticeReceive noticeReceive) {
        return noticeReceiveMapper.updateNoticeReceive(noticeReceive);
    }
}
