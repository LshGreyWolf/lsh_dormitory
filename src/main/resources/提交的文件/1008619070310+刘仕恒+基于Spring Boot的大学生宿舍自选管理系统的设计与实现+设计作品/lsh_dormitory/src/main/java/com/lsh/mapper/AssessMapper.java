package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Assess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Assess)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-15 11:46:05
 */
@Mapper
public interface AssessMapper extends BaseMapper<Assess> {

    List<Assess> queryByPage(Assess assess);
}

