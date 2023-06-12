package com.lsh.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;
import com.lsh.domain.Storey;
import com.lsh.domain.User;
import com.lsh.service.BuildingService;
import com.lsh.service.StoreyService;
import com.lsh.service.UserService;
import com.lsh.utils.RedisCache;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.lsh.constants.RedisConstants.USER_BUILDING_KEY;
import static com.lsh.constants.SystemConstants.USER_TYPE_ADMINISTRATORS;

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
    @Autowired
    private RedisCache redisCache;

    /**
     * 楼宇的分页
     * @param building
     * @return
     */
    @PostMapping("/BuildingQueryByPage")
    public Map<String, Object> BuildingQueryByPage(@RequestBody Building building,HttpServletRequest request) {
        //如果是超级管理员才能查询所有的楼宇
        //宿管员只能查询到自己的楼宇
        User param = UserHolder.getUser();
        User loginUser = userService.getUser(param);
        //登录用户为宿管员的时候
        if (loginUser.getType() == USER_TYPE_ADMINISTRATORS) {
            building.setUserId(loginUser.getId());
        }
        PageInfo<Building> buildingPageInfo = buildingService.BuildingQueryByPage(building);
        List<Building> buildingList = buildingPageInfo.getList();
        //循环楼宇，将楼宇的User属性 添加
        for (Building build : buildingList) {
            Integer userId = build.getUserId();
            User user = new User();
            user.setId(userId);
            build.setUser(userService.getUser(user));
        }
        //将楼宇数据缓存到redis中,无过期时间
        redisCache.setCacheObject(USER_BUILDING_KEY, JSONUtil.toJsonStr(buildingList));
        return Result.ok(buildingPageInfo);
    }

    /**
     * 新增楼宇，同时新增该楼宇的楼层数
     * @param building
     * @return
     */
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
            storey.setName(i + 1 + "层");
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
        if (flag) {
            return Result.ok("更新成功");
        }
        return Result.fail("失败");
    }


}

