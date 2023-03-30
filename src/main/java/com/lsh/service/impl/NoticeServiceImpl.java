package com.lsh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;
import com.lsh.domain.Notice;
import com.lsh.domain.NoticeReceive;
import com.lsh.mapper.NoticeMapper;
import com.lsh.mapper.NoticeReceiveMapper;
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
    @Autowired
    private NoticeReceiveMapper noticeReceiveMapper;

    @Override
    public PageInfo<Notice> queryByPage(Notice notice) {

        if (notice != null && notice.getPage() != null) {
            PageHelper.startPage(notice.getPage(), notice.getLimit());
        }
        return new PageInfo<Notice>(noticeMapper.queryByPage(notice));
    }

    @Override
    public int insertNotice(Notice notice) {
        noticeMapper.insertNotice(notice);


        return 1;
    }

    @Override
    public int delete(String ids) {
        String[] idsArr = ids.split(",");
        int row = 0;
        for (String id : idsArr) {
            noticeMapper.deleteById(id);
            row++;
        }
        return row;
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public Notice getNotice(Notice notice) {
        return noticeMapper.getNotice(notice);
    }




}
