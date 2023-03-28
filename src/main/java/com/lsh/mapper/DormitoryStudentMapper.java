package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.DormitoryStudent;
import org.apache.ibatis.annotations.Mapper;


/**
 * (DormitoryStudent)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-28 17:28:09
 */
@Mapper
public interface DormitoryStudentMapper extends BaseMapper<DormitoryStudent> {

}
