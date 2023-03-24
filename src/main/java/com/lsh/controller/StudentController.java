package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Grade;
import com.lsh.domain.Org;
import com.lsh.domain.Student;
import com.lsh.service.GradeService;
import com.lsh.service.OrgService;
import com.lsh.service.StudentService;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/23
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private GradeService gradeService;

    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Student student) {
        PageInfo<Student> studentPageInfo = studentService.queryByPage(student);

        List<Student> studentList = studentPageInfo.getList();
        studentList.forEach(item -> {
            //根据student的id查询对应的班级
            Org org = orgService.detail(item.getClazzId());
            item.setOrg(org);
            //根据student的id查询对应的年级
            Grade grade = gradeService.detail(item.getGradeId());
            item.setGrade(grade);

        });
        return Result.ok(studentPageInfo);
    }

    @GetMapping("delete")
    public Result delete(String ids) {
        int row = studentService.deleteStudent(ids);
        if (row > 0) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/save")
    public Result save(@RequestBody Student student) {
        boolean flag = studentService.saveStudent(student);
        if (flag) {
            return Result.ok("新增成功");
        }
        return Result.fail("新增失败");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Student student) {
        boolean flag = studentService.updateStudent(student);
        if (flag) {
            return Result.ok("修改成功");
        }
        return Result.fail("修改失败");
    }


}
