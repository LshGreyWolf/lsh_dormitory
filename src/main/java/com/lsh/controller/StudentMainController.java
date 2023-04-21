package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lsh.domain.*;
import com.lsh.service.*;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/04/21
 */
@RestController
@RequestMapping("/studentMain")
public class StudentMainController {

    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private DormitoryStudentService dormitoryStudentService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BedService bedService;
    @Autowired
    private StoreyService storeyService;
    @Autowired
    private StudentService studentService;

    /**
     * 学生登录后的首页的宿舍概览
     *
     * @return
     */
    @GetMapping("/selectStudentDormitory")
    public Result selectStudentDormitory() {
        Student student = UserHolder.getStudent();
        HashMap<String, Object> map = new HashMap<>();
        //根据学生id，查询出该学生对应的宿舍id和床位id还有选择时间
        DormitoryStudent dormitoryStudent =
                dormitoryStudentService.getOne(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getStudentId, student.getId()));
        if (dormitoryStudent == null) {
            return Result.ok("暂无");
        }
        Integer dormitoryId = dormitoryStudent.getDormitoryId();
        //根据宿舍id查出宿舍号
        Dormitory dormitory = dormitoryService.getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId, dormitoryId));
        String dormitoryNo = dormitory.getNo();
        map.put("dormitoryNo", dormitoryNo);
        //根据宿舍id查出楼宇号
        Integer buildingId = dormitory.getBuildingId();
        Building building = buildingService.getById(buildingId);
        String buildingName = building.getName();
        map.put("buildingName", buildingName);
        Integer storeyId = dormitory.getStoreyId();
        Storey storey = storeyService.getById(storeyId);
        String storeyName = storey.getName();
        map.put("storeyName", storeyName);
        Date checkin = dormitoryStudent.getCheckin();
        map.put("checkin", checkin);
        return Result.ok(map);
    }

    @PostMapping("/selectRoommate")
    public Result selectRoommate() {
        Student student = UserHolder.getStudent();
        Integer studentId = student.getId();
        //根据该学生id查询该学生所在的宿舍
        DormitoryStudent dormitoryStudent =
                dormitoryStudentService.getOne(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getStudentId, studentId));
        if (dormitoryStudent == null) {
            return Result.ok("暂无");
        }
        Integer dormitoryId = dormitoryStudent.getDormitoryId();

        //根据宿舍id查出该宿舍的所有学生
        List<DormitoryStudent> studentList =
                dormitoryStudentService.list(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getDormitoryId, dormitoryId));
        studentList.forEach(item -> {
            Student student1 = studentService.getOne(new LambdaQueryWrapper<Student>().eq(Student::getId, item.getStudentId()));
            item.setStudent(student1);

            Dormitory dormitory = dormitoryService.getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId, item.getDormitoryId()));
            //根据buildingId查出楼宇名称
            Building building = buildingService.getOne(new LambdaQueryWrapper<Building>().eq(Building::getId, dormitory.getBuildingId()));
            dormitory.setBuilding(building);
            Storey storey = storeyService.getById(dormitory.getStoreyId());
            dormitory.setStorey(storey);

            item.setDormitory(dormitory);
        });
        return Result.ok(studentList);
    }
}
