package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lsh.domain.Dormitory;
import com.lsh.service.DormitoryService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/3/26 14:37
 */
@RestController
@RequestMapping("/dormitory")
public class DormitoryController {

    @Autowired
    private DormitoryService dormitoryService;

    @PostMapping("/list/{storeyId}")
    public Result list(@PathVariable("storeyId") Integer storeyId) {

        LambdaQueryWrapper<Dormitory> queryWrapper = new LambdaQueryWrapper<Dormitory>()
                .eq(Dormitory::getStoreyId, storeyId);
        List<Dormitory> dormitoryList = dormitoryService.list(queryWrapper);
        return Result.ok(dormitoryList);
    }

    @PostMapping("save")
    public Result save(@RequestBody Dormitory dormitory) {
        //先查询该栋楼现存在的所有宿舍
        List<Dormitory> dormitoryList = dormitoryService.listDormitory(dormitory);
        for (Dormitory item : dormitoryList) {
            if (item.getNo().equals(dormitory.getNo())) {
                return Result.fail("该栋楼的该宿舍已经存在，请选择其他宿舍号");
            }
        }
        boolean flag = dormitoryService.save(dormitory);
        if (flag) {
            return Result.ok("新增成功");
        }
        return Result.ok("新增失败");
    }


    @GetMapping("/delete")
    public Result delete(Integer dormitoryId) {
        boolean flag = dormitoryService.removeById(dormitoryId);
        if (flag) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/list")
    public Result list(@RequestBody Dormitory dormitory) {
        List<Dormitory> dormitories = dormitoryService.listDormitory(dormitory);
        return Result.ok(dormitories);
    }

    @PostMapping("/initDormitory")
    public Result initDormitory(@RequestBody Dormitory dormitory) {
        dormitoryService.initDormitory(dormitory);
        return Result.ok("初始化成功！");
    }

}
