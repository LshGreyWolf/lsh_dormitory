package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.DormitoryStudent;
import com.lsh.domain.Student;
import com.lsh.service.DormitoryStudentService;
import com.lsh.service.StudentService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lsh
 * @version 1.0
 * @description TODO
 * @date 2023/4/11 19:07
 */
@RestController
@RequestMapping("/dormitoryStudent")
public class DormitoryStudentController {
    @Autowired
    private DormitoryStudentService dormitoryStudentService;
    @Autowired
    private StudentService studentService;

    /**
     * 查询已经选择宿舍的学生列表
     * @param dormitoryStudent
     * @return
     */
    @PostMapping("/selectDormitoryStudent")
    public Result selectDormitoryStudent(@RequestBody DormitoryStudent dormitoryStudent){
        List<DormitoryStudent> dormitoryStudentList =
                dormitoryStudentService.list(new LambdaQueryWrapper<DormitoryStudent>()
                        .eq(DormitoryStudent::getDormitoryId, dormitoryStudent.getDormitoryId()));
        dormitoryStudentList.forEach(item->{
            Student detail = studentService.getStudent(item.getStudentId());
            item.setStudent(detail);
        });
        return Result.ok(dormitoryStudentList);
    }
}
