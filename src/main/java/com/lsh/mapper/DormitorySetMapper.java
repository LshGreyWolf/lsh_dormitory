package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.DormitorySet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (DormitorySet)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-28 17:27:15
 */
@Mapper
public interface DormitorySetMapper extends BaseMapper<DormitorySet> {

    boolean saveDormitorySet(DormitorySet dormitorySet);

    List<DormitorySet> queryDormitorySet(DormitorySet dormitorySet);

    int updateDormitorySet(DormitorySet dormitorySet);
}
