package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lsh.domain.Storey;
import com.lsh.service.StoreyService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/3/26 13:31
 */
@RestController
@RequestMapping("/storey")
public class StoryController {
    @Autowired
    private StoreyService storeyService;

    @PostMapping("/list/{buildingId}")
    public Result listStory(@PathVariable("buildingId") Integer buildingId) {
        LambdaQueryWrapper<Storey> queryWrapper = new LambdaQueryWrapper<Storey>()
                .eq(Storey::getBuildingId, buildingId);
        List<Storey> list = storeyService.list(queryWrapper);
        return Result.ok(list);
    }

}
