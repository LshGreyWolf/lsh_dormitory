package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;
import com.lsh.domain.User;
import com.lsh.service.BuildingService;
import com.lsh.service.UserService;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 楼宇(Building)表控制层
 *
 * @author lsh
 * @since 2023-03-24 18:04:11
 */
@RestController
@RequestMapping("/building")
@Slf4j
public class BuildingController {
    @Resource
    private BuildingService buildingService;
    @Resource
    private UserService userService;

    @PostMapping("/BuildingQueryByPage")
    public Map<String, Object> BuildingQueryByPage(@RequestBody Building building) {
        PageInfo<Building> buildingPageInfo = buildingService.BuildingQueryByPage(building);
        List<Building> buildingList = buildingPageInfo.getList();
        for (Building build : buildingList) {
            Integer userId = build.getUserId();

//            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getId, userId);
//            User user = userService.getById(queryWrapper);
//
//
            User user = new User();
            user.setId(userId);

            build.setUser(userService.getUser(user));
        }
        return Result.ok(buildingPageInfo);
    }

    @PostMapping("/save")
    public Result saveBuilding(@RequestBody Building building) {
        boolean flag = buildingService.saveBuilding(building);
        if (flag) {
            return Result.ok("新增成功！");
        }
        return Result.fail("新增失败");
    }

    @GetMapping("/deleteBuilding")
    public Result deleteBuilding(String ids) {
        int row= buildingService.deleteBuilding(ids);
        if (row>0){
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }


}

