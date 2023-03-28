package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Dormitory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Dormitory)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-26 14:36:18
 */
@Mapper
public interface DormitoryMapper extends BaseMapper<Dormitory> {

    List<Dormitory> listNo();

    List<Dormitory> listDormitory(Dormitory dormitory);
}

