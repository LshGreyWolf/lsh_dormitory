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
import com.sun.webkit.graphics.WCRenderQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.NoticeService;
import org.springframework.transaction.annotation.Transactional;
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
    public int deleteNotice(String ids) {
        String[] idsArr = ids.split(",");
        int row = 0;
        for (String id : idsArr) {
            noticeMapper.deleteById(id);
            //删除关联表数据
            noticeReceiveMapper.deleteByNoticeId(Integer.valueOf(id));
            row++;
        }
        return row;
    }

    @Override
    @Transactional
    public int updateNotice(Notice notice) {
        noticeMapper.updateNotice(notice);
        noticeReceiveMapper.deleteByNoticeId(notice.getId());
        List<Integer> buildingIds = notice.getBuildingIds();
        for (Integer buildingId : buildingIds) {
            NoticeReceive  noticeReceive = new NoticeReceive();
            noticeReceive.setBuildingId(buildingId);
            noticeReceive.setNoticeId(notice.getId());
            noticeReceiveMapper.saveNoticeReceive(noticeReceive);
        }
        return 1;
    }

    @Override
    public Notice getNotice(Notice notice) {
        return noticeMapper.getNotice(notice);
    }

    @Override
    public PageInfo<Notice> queryByBuildingId(Notice notice){
        if(notice != null && notice.getPage() != null){
            PageHelper.startPage(notice.getPage(),notice.getLimit());
        }
        return new PageInfo<Notice>(noticeMapper.queryByBuildingId(notice));
    }

}
