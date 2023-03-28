package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Record)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-28 16:57:52
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    List<Record> queryByPage(Record record);
}
