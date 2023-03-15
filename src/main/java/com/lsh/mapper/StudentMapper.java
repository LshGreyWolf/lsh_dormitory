package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Student;
import org.apache.ibatis.annotations.Mapper;


/**
 * (Student)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-06 09:21:37
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    Student login(String userName, String password);

}
