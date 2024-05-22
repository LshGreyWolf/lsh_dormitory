package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Absence;
import com.lsh.domain.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/05/22
 */
@Mapper
public interface LogServiceMapper extends BaseMapper<LogEntity> {
}
