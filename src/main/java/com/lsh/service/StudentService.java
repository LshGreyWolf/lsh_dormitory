package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Student;
import com.lsh.domain.Vo.StudentDto;

import org.springframework.web.bind.annotation.RequestBody;


/**
 * (Student)表服务接口
 *
 * @author lsh
 * @since 2023-03-06 09:21:37
 */
public interface StudentService extends IService<Student> {

    Student login(String userName, String password);


    PageInfo<Student> queryByPage(Student student);

    int deleteStudent(String ids);

    boolean saveStudent(Student student);

    boolean updateStudent( Student student);
    Student getStudent(Integer id);
    Student getStudent(Student student);

    boolean register(StudentDto studentDto);
}
