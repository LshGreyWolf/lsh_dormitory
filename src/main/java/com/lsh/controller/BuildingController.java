package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;
import com.lsh.domain.Storey;
import com.lsh.domain.User;
import com.lsh.service.BuildingService;
import com.lsh.service.StoreyService;
import com.lsh.service.UserService;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private StoreyService storeyService;

    @PostMapping("/BuildingQueryByPage")
    public Map<String, Object> BuildingQueryByPage(@RequestBody Building building, HttpServletRequest request) {
        PageInfo<Building> buildingPageInfo = buildingService.BuildingQueryByPage(building);
        List<Building> buildingList = buildingPageInfo.getList();
        //如果是超级管理员才能查询所有的楼宇
        //宿管员只能查询到自己的楼宇
        //todo threadLocal
//        User param = (User) request.getAttribute("user");
        User param = UserHolder.getUser();
        User loginUser = userService.getUser(param);
        if (loginUser.getType() == 1) {
            building.setUserId(loginUser.getId());
        }

        for (Building build : buildingList) {
            Integer userId = build.getUserId();
            User user = new User();
            user.setId(userId);

            build.setUser(userService.getUser(user));
        }
        return Result.ok(buildingPageInfo);
    }

    @PostMapping("/save")
    public Result saveBuilding(@RequestBody Building building) {
        //新增楼宇的时候需要注意：同时将对应楼宇的楼层插入到楼层表中
        //插入楼宇相关信息
        boolean flag = buildingService.saveBuilding(building);
        //查询刚才插入的楼宇信息，得到其id
        Building serviceBuilding = buildingService.getBuilding(building);
        //插入楼层相关信息
        Integer storeyNum = building.getStoreyNum();
        for (Integer i = 0; i < storeyNum; i++) {
            Storey storey = new Storey();
            storey.setBuildingId(serviceBuilding.getId());
            storey.setName(i+1 + "层");
            storeyService.save(storey);
        }
        if (flag) {
            return Result.ok("新增成功！");
        }
        return Result.fail("新增失败");
    }

    @GetMapping("/deleteBuilding")
    public Result deleteBuilding(String ids) {
        int row = buildingService.deleteBuilding(ids);
        if (row > 0) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/updateBuilding")
    public Result updateBuilding(@RequestBody Building building) {

        boolean flag = buildingService.updateBuilding(building);
        if (flag){
            return Result.ok("更新成功");
        }
        return Result.fail("失败");
    }


}

