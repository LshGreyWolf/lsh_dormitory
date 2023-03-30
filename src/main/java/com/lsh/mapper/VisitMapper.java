package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Visit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Visit)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-27 20:26:13
 */
@Mapper
public interface VisitMapper extends BaseMapper<Visit> {


    List<Visit> queryByPage(Visit visit);

    int save(Visit visit);

    boolean updateVisitor(Visit visit);
}
