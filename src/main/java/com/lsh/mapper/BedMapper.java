package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Bed;
import org.apache.ibatis.annotations.Mapper;


/**
 * (Bed)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-26 15:18:56
 */
@Mapper
public interface BedMapper extends BaseMapper<Bed> {

}

