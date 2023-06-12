package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Student;
import com.lsh.domain.User;
import com.lsh.domain.Vo.StudentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Student)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-06 09:21:37
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    Student login(String userName, String password);

    List<Student> queryByPage(Student student);

    boolean saveStudent(Student student);

    boolean updateStudent(Student student);

    Student getStudent(Integer id);

    Student getStudent1(Student student);

    void register(StudentDto studentDto);

}
