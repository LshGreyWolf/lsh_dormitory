package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsh.domain.Student;


/**
 * (Student)表服务接口
 *
 * @author lsh
 * @since 2023-03-06 09:21:37
 */
public interface StudentService extends IService<Student> {

    Student login(String userName, String password);
}
