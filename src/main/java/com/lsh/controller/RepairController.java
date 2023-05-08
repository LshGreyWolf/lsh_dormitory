package com.lsh.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.*;
import com.lsh.service.*;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.lsh.constants.SystemConstants.USER_TYPE_ADMINISTRATORS;

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
    @Autowired
    private UserService userService;

    /**
     * 报修分页
     *
     * @param repair
     * @return
     */
    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Repair repair) {
        PageInfo<Repair> repairPageInfo = null;
        User entity = UserHolder.getUser();
        User param = null;
        if (entity != null) {
            param = userService.getById(entity.getId());
        }
        //如果登录的用户是宿管员，则只显示该宿管员管理的楼宇
        if (param.getType() == USER_TYPE_ADMINISTRATORS) {
            Building one = buildingService.getOne(new LambdaQueryWrapper<Building>().eq(Building::getUserId, param.getId()));
            repair.setBuildingId(one.getId());
            repairPageInfo = repairService.queryByPage(repair);
        } else {
            repairPageInfo = repairService.queryByPage(repair);
        }
        repairPageInfo.getList().forEach(item -> {
            //根据id取出宿舍数据
            Dormitory dormitory = dormitoryService
                    .getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId, item.getDormitoryId()));
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

    /**
     * 报修分页
     *
     * @param repair
     * @return
     */
    @PostMapping("/queryByPageStudent")
    public Map<String, Object> queryByPageStudent(@RequestBody Repair repair) {
        PageInfo<Repair> repairPageInfo = repairService.queryByPage(repair);
        repairPageInfo.getList().forEach(item -> {
            //根据id取出宿舍数据
            Dormitory dormitory = dormitoryService
                    .getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId, item.getDormitoryId()));
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

    /**
     * 报修审核
     *
     * @param repair
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Repair repair) {
        Repair repair1 = new Repair();
        repair1.setStatus(repair.getStatus());
        repair1.setProcessIdea(repair.getProcessIdea());
        User user = UserHolder.getUser();
        Integer userId = user.getId();
        User user1 = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        repair1.setProcessor(user1.getName());
        repair1.setId(repair.getId());
        repair1.setDescription(repair.getDescription());
        int flag = repairService.updateRepair(repair1);
        if (flag > 0) {
            return Result.ok("修改成功！");
        } else {
            return Result.fail("修改失败");
        }
    }

    /**
     * 更新报修描述
     *
     * @param repair
     * @return
     */
    @PostMapping("/updateDescription")
    public Result updateDescription(@RequestBody Repair repair) {

        int flag = repairService.updateRepair(repair);
        if (flag > 0) {
            return Result.ok("修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @GetMapping("/deleteRepair")
    public Result deleteRepair(String ids) {
        //只能删除已经审核完毕的报修信息
        int flag = repairService.deleteRepair(ids);

        if (flag > 0) {
            return Result.ok("删除成功");
        } else {
            return Result.fail("该条报修信息还未审核");
        }
    }

}

