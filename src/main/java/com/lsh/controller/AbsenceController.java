package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.*;
import com.lsh.service.*;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.lsh.constants.SystemConstants.USER_TYPE_ADMINISTRATORS;

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
    @Autowired
    private UserService userService;

    /**
     * 离宿分页
     *
     * @param absence
     * @return
     */
    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Absence absence) {
        User entity = UserHolder.getUser();
        User param = userService.getById(entity.getId());
        PageInfo<Absence> pageInfo = null;
        if (param.getType() == USER_TYPE_ADMINISTRATORS) {
            Building one = buildingService.getOne(new LambdaQueryWrapper<Building>().eq(Building::getUserId, param.getId()));
            absence.setBuildingId(one.getId());
            pageInfo = absenceService.queryByPage(absence);
        } else {
            pageInfo = absenceService.queryByPage(absence);
        }

        pageInfo.getList().forEach(item -> {
            Student detail = studentService.getStudent(item.getStudentId());
            item.setStudent(detail);
            Dormitory dormitory = dormitoryService.getOne(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getId, item.getDormitoryId()));
            Building building = new Building();
            building.setId(item.getBuildingId());
            Building building1 = buildingService.getBuilding(building);
            item.setBuilding(building1);
            item.setDormitory(dormitory);

            Date endTimeTemp = item.getEndTime();
            Date currentTimeTemp = new Date();

            if (currentTimeTemp.after(endTimeTemp)) {
                //当前时间大于结束时间，则缺勤
                item.setStatus(2);
                absenceService.updateAbsence(item);
            }
        });
        return Result.ok(pageInfo);
    }

    @PostMapping("/insertAbsence")
    public Result insertAbsence(@RequestBody Absence absence) {
        //新增时，默认离宿中
        absence.setStatus(0);
        int flag = absenceService.insertAbsence(absence);
        if (flag > 0) {
            return Result.ok("新增成功！");
        } else {
            return Result.fail("新增失败！");
        }
    }

    @GetMapping("delete")
    public Result delete(String ids) {
        int flag = absenceService.delete(ids);
        if (flag > 0) {
            return Result.ok("删除成功！");
        } else {
            return Result.fail("删除失败！");
        }
    }

    @PostMapping("/updateAbsence")
    public Result updateAbsence(@RequestBody Absence absence) {
        boolean flag = absenceService.updateById(absence);
        if (flag) {
            return Result.ok("更新成功！");
        } else {
            return Result.fail("更新失败！");
        }
    }


}
