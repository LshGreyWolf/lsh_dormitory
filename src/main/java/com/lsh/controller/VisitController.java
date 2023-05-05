package com.lsh.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.*;
import com.lsh.service.BuildingService;
import com.lsh.service.StudentService;
import com.lsh.service.UserService;
import com.lsh.service.VisitService;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.lsh.constants.SystemConstants.USER_TYPE_ADMINISTRATORS;

/**
 * (Visit)表控制层
 *
 * @author lsh
 * @since 2023-03-27 20:26:13
 */
@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;

    @PostMapping("/queryByPage")
    private Map<String, Object> queryByPage(@RequestBody Visit visit) {
        PageInfo<Visit> visitPageInfo = null;
        User entity = UserHolder.getUser();
        User param = userService.getById(entity.getId());
        if (param.getType() == USER_TYPE_ADMINISTRATORS) {
            Building one = buildingService.getOne(new LambdaQueryWrapper<Building>().eq(Building::getUserId, param.getId()));
            visit.setBuildingId(one.getId());
            visitPageInfo = visitService.queryByPage(visit);
        } else {
            visitPageInfo = visitService.queryByPage(visit);
        }
        visitPageInfo.getList().forEach(item -> {
            Student student = studentService.getStudent(item.getStudentId());
            item.setStudent(student);
        });
        return Result.ok(visitPageInfo);
    }

    @PostMapping("/saveVisitors")
    public Result saveVisitors(@RequestBody Visit visit) {
        int flag = visitService.saveVisitors(visit);
        if (flag > 0) {
            return Result.ok("新增成功！");
        } else {
            return Result.fail("新增失败！");
        }
    }

    @PostMapping("/updateVisitor")
    public Result updateVisitor(@RequestBody Visit visit) {
        boolean flag = visitService.updateVisitor(visit);
        if (flag){
            return  Result.ok("更新成功！");
        }
        return Result.fail("更新失败");
    }

    @PostMapping("/deleteVisitor")
    public Result deleteVisitor(String ids){
        int row =  visitService.deleteVisitor(ids);
        if (row>0){
            return Result.ok("删除成功！");
        }
        return Result.fail("删除失败");
    }
}

