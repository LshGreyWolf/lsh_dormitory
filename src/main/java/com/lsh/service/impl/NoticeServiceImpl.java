package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Notice;
import com.lsh.domain.NoticeReceive;
import com.lsh.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.NoticeService;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * (Notice)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:21:34
 */
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public PageInfo<Notice> queryByPage(Notice notice) {

        if(notice != null && notice.getPage() != null){
            PageHelper.startPage(notice.getPage(),notice.getLimit());
        }
        return new PageInfo<Notice>(noticeMapper.queryByPage(notice));
    }

    @Override
    public int create(Notice notice) {
        return 0;
    }

    @Override
    public int delete(String ids) {
        return 0;
    }

    @Override
    public int updateSelective(Notice notice) {
        return 0;
    }


}
