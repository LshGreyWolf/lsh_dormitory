package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.NoticeReceive;
import org.apache.ibatis.annotations.Mapper;


/**
 * (NoticeReceive)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-28 17:31:08
 */
@Mapper
public interface NoticeReceiveMapper extends BaseMapper<NoticeReceive> {

    int saveNoticeReceive(NoticeReceive noticeReceive);
}
