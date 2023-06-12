package com.lsh.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lsh.domain.Building;
import com.lsh.domain.Storey;
import com.lsh.service.BuildingService;
import com.lsh.service.StoreyService;
import com.lsh.utils.RedisCache;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lsh.constants.RedisConstants.USER_STOREY;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/3/26 13:31
 */
@RestController
@RequestMapping("/storey")
public class StoreyController {
    @Autowired
    private StoreyService storeyService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private BuildingService buildingService;

    /**
     * 根据楼宇id查询该楼宇的层数
     * @param buildingId
     * @return
     */
    @PostMapping("/list/{buildingId}")
    public Result listStory(@PathVariable("buildingId") Integer buildingId) {
        LambdaQueryWrapper<Storey> queryWrapper = new LambdaQueryWrapper<Storey>()
                .eq(Storey::getBuildingId, buildingId);
        List<Storey> list = storeyService.list(queryWrapper);

        Building building = buildingService.getOne(new LambdaQueryWrapper<Building>().eq(Building::getId, buildingId));

        String buildingName= building.getName();

        String key = USER_STOREY + buildingName;
        redisCache.setCacheObject(key, JSONUtil.toJsonStr(list));
        return Result.ok(list);
    }


}
