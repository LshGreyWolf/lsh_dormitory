package com.lsh.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.*;
import com.lsh.service.*;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * (Assess)表控制层
 *
 * @author makejava
 * @since 2023-04-15 11:46:05
 */
@RestController
@RequestMapping("assess")
public class AssessController {
    @Autowired
    private AssessService assessService;
    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private UserService userService;
    @Autowired
    private StoreyService storeyService;

    @Autowired
    private BuildingService buildingService;

    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Assess assess) {
        PageInfo<Assess> pageInfo = assessService.queryByPage(assess);
        pageInfo.getList().forEach(item -> {
            Dormitory dormitory =
                    dormitoryService.getById(item.getDormitoryId());
            Storey storey = storeyService.getById(dormitory.getStoreyId());
            Building building = buildingService.getById(dormitory.getBuildingId());
            dormitory.setStorey(storey);
            dormitory.setBuilding(building);
            User user = userService.getById(item.getUserId());

            item.setDormitory(dormitory);
            item.setUser(user);
        });
        return Result.ok(pageInfo);
    }

    @PostMapping("/saveAssess")
    public Result saveAssess(@RequestBody Assess assess) {
        assess.setCreateTime(new Date());
        assess.setUserId(UserHolder.getUser().getId());

        assessService.save(assess);
        return Result.ok("新增成功！");
    }
    @GetMapping("/deleteAssess")
    public Result deleteAssess(String ids) {
        int flag = assessService.deleteAssess(ids);
        if (flag > 0) {
            return Result.ok("删除成功！");
        } else {
            return Result.fail("删除失败！");
        }
    }

    @PostMapping("/updateAssess")
    public Result updateAssess(@RequestBody Assess assess) {
        //更新评价时间
        assess.setCreateTime(new Date());
        assessService.updateById(assess);
        return Result.ok("更新成功");
    }
}
