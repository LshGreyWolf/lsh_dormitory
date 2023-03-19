package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Grade;
import com.lsh.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Grade)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-18 13:00:25
 */
@Mapper
public interface GradeMapper extends BaseMapper<Grade> {

    List<Grade> queryByPage(Grade grade);

    boolean add(Grade grade);

    boolean updateGradeById(Grade grade);


}

