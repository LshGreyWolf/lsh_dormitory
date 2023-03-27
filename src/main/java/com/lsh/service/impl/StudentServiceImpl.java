package com.lsh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Student;
import com.lsh.domain.User;
import com.lsh.mapper.StudentMapper;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.StudentService;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Override
    public PageInfo<Student> queryByPage(Student student) {

        if (student != null && student.getPage() != null) {
            PageHelper.startPage(student.getPage(), student.getLimit());
        }
        return new PageInfo<Student>(studentMapper.queryByPage(student));


    }

    @Override
    public int deleteStudent(String ids) {
        String[] idArr = ids.split(",");
        int row = 0;
        for (String id : idArr) {
            LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Student::getId, id);
            studentMapper.delete(queryWrapper);
            row++;
        }
        return row;

    }

    @Override
    public boolean saveStudent(Student student) {

        boolean flag = studentMapper.saveStudent(student);
        return flag;
    }

    @Override
    public boolean updateStudent( Student student) {

        boolean flag = studentMapper.updateStudent(student);
      return flag;
    }

    @Override
    public Student getStudent(Integer id) {

       return studentMapper.getStudent(id);
    }
}
