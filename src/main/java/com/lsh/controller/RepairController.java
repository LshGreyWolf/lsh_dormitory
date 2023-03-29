package com.lsh.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;
import com.lsh.domain.Dormitory;
import com.lsh.domain.Repair;
import com.lsh.domain.Student;
import com.lsh.service.BuildingService;
import com.lsh.service.DormitoryService;
import com.lsh.service.StudentService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lsh.service.RepairService;

import java.util.List;
import java.util.Map;

/**
 * (Repair)表控制层
 *
 * @author lsh
 * @since 2023-03-25 17:23:28
 */
@RestController
@RequestMapping("repair")
public class RepairController {

    @Autowired
    private RepairService repairService;
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Repair repair) {
        PageInfo<Repair> repairPageInfo = repairService.queryByPage(repair);
        repairPageInfo.getList().forEach(item -> {
            //根据id取出宿舍数据
            Dormitory dormitory = dormitoryService.getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId, item.getDormitoryId()));

            Building building = new Building();
            building.setId(item.getBuildingId());

            Student student = new Student();
            student.setId(item.getStudentId());
             studentService.queryByPage(student);

            item.setBuilding(buildingService.getBuilding(building));
            item.setDormitory(dormitory);

            item.setStudent(studentService.getStudent(item.getStudentId()));
        });


        return Result.ok(repairPageInfo);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Repair repair){
        int flag = repairService.updateRepair(repair);
        if(flag>0){
            return Result.ok("修改成功！");
        }else{
            return Result.fail("修改失败");
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = repairService.delete(ids);
        if(flag>0){
            return Result.ok("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }

}

