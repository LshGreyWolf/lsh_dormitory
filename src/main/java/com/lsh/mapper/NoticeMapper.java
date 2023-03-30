package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Notice)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-28 17:21:34
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    List<Notice> queryByPage(Notice notice);

    int insertNotice(Notice notice);

    int updateNotice(Notice notice);

    Notice  getNotice(Notice notice);


}
