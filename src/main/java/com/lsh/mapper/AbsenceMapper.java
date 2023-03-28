package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Absence;
import org.apache.ibatis.annotations.Mapper;


/**
 * (Absence)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-28 17:26:15
 */
@Mapper
public interface AbsenceMapper extends BaseMapper<Absence> {

}
