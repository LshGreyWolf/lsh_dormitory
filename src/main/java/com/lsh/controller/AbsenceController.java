package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Absence;
import com.lsh.domain.Building;
import com.lsh.domain.Dormitory;
import com.lsh.domain.Student;
import com.lsh.service.AbsenceService;
import com.lsh.service.BuildingService;
import com.lsh.service.DormitoryService;
import com.lsh.service.StudentService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/29
 */
@RestController
@RequestMapping("/absence")
public class AbsenceController {
    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private BuildingService buildingService;


    @PostMapping("/queryByPage")
    public Map<String,Object> queryByPage(@RequestBody  Absence absence){
        PageInfo<Absence> pageInfo = absenceService.queryByPage(absence);
        pageInfo.getList().forEach(item->{
            Student detail = studentService.getStudent(item.getStudentId());
            item.setStudent(detail);
            Dormitory dormitory = dormitoryService.getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId,item.getDormitoryId()));
            Building building = new Building();
            building.setId(item.getBuildingId());
            Building building1 = buildingService.getBuilding(building);
            item.setBuilding(building1);

            item.setDormitory(dormitory);
                //todo
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date endTime = item.getEndTime();
//            Date currentTime=new Date();
//            String format = sdf.format(endTime);
//            String format1 = sdf.format(currentTime);
//
//            System.out.println("currentTime = " + currentTime);
//            int flag = endTime.compareTo(currentTime);
//            if (flag > 0){
//                //结束时间大于当前时间，则缺勤
//                item.setStatus(2);
//                absenceService.updateById(item);
//            }
        });


        return Result.ok(pageInfo);
    }

    @PostMapping("/insertAbsence")
    public Result insertAbsence(@RequestBody Absence absence){
        //新增时，默认离宿中
        absence.setStatus(0);
        int flag = absenceService.insertAbsence(absence);
        if(flag>0){
            return Result.ok("新增成功！");
        }else{
            return Result.fail("新增失败！");
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = absenceService.delete(ids);
        if(flag>0){
            return Result.ok("删除成功！");
        }else{
            return Result.fail("删除失败！");
        }
    }

    @PostMapping("/updateAbsence")
    public Result updateAbsence(@RequestBody Absence absence){
        boolean flag = absenceService.updateById(absence);
        if(flag){
            return Result.ok("更新成功！");
        }else{
            return Result.fail("更新失败！");
        }
    }




}
