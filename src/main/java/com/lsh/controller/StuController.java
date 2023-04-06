package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.*;
import com.lsh.service.*;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/04/06
 */
@RestController
@RequestMapping("/stu")
@Slf4j
public class StuController {


    @Autowired
    private StudentService studentService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private SelectionDormitoryService selectionDormitoryService;
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private DormitoryStudentService dormitoryStudentService;
    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private RepairService repairService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;
    @Autowired
    private SelectionService selectionService;
    @Autowired
    private BuildingService buildingService;

    @PostMapping("/info")
    public Result info(HttpServletRequest request) {
        Student param = (Student) request.getAttribute("student");
        Student student = studentService.getStudent(param.getId());
        Org org = orgService.detail(param.getClazzId());
        Grade grade = gradeService.detail(param.getGradeId());
        student.setOrg(org);
        student.setGrade(grade);
        return Result.ok(student);
    }

    @PostMapping("/absence")
    public Map<String, Object> query(@RequestBody Absence absence, HttpServletRequest request) {
        Student param = (Student) request.getAttribute("student");
        absence.setStudentId(param.getId());
        PageInfo<Absence> pageInfo = absenceService.queryByPage(absence);
        pageInfo.getList().forEach(entity -> {
            Student detail = studentService.getStudent(entity.getStudentId());
            entity.setStudent(detail);
            LambdaQueryWrapper<Dormitory> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Dormitory::getId,entity.getDormitory());
            Dormitory dormitory = dormitoryService.getOne(queryWrapper);
            entity.setDormitory(dormitory);
        });
        return Result.ok(pageInfo);
    }




}
