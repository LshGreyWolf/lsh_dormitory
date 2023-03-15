package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Student;
import com.lsh.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.StudentService;

/**
 * (Student)表服务实现类
 *
 * @author lsh
 * @since 2023-03-06 09:21:37
 */
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student login(String userName, String password) {


        return studentMapper.login(userName, password);
    }
}
