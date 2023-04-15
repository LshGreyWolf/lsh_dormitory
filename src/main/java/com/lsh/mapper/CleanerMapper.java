package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Absence;
import com.lsh.domain.Cleaner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Cleaner)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-15 10:04:44
 */
@Mapper
public interface CleanerMapper extends BaseMapper<Cleaner> {

    List<Cleaner> queryByPage(Cleaner cleaner);
}

