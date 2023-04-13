package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lsh.domain.Bed;
import com.lsh.service.BedService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/3/26 15:19
 */
@RestController
@RequestMapping("/bed")
public class BedController {
    @Autowired
    private BedService bedService;

    @PostMapping("/list/{dormitoryId}")
    public Result list(@PathVariable("dormitoryId") Integer dormitoryId) {

        LambdaQueryWrapper<Bed> queryWrapper = new LambdaQueryWrapper<Bed>()
                .eq(Bed::getDormitoryId, dormitoryId);
        List<Bed> bedList = bedService.list(queryWrapper);
        return Result.ok(bedList);
    }

    @PostMapping("/save")
    public Result insert(@RequestBody Bed bed) {
        //先查询该宿舍的所有床位号
        List<Bed> bedList = bedService.list(new LambdaQueryWrapper<Bed>().eq(Bed::getDormitoryId, bed.getDormitoryId()));
        for (Bed item : bedList) {
            if (item.getBno().equals(bed.getBno())) {
                return Result.fail("该床位号已存在！请选择其他床位号");
            }
        }
        boolean flag = bedService.save(bed);
        if (flag) {
            return Result.ok("新增成功");
        }
        return Result.ok("新增失败");
    }

    @GetMapping("/deleteBed")
    public Result delete(Integer bedId) {
        boolean flag = bedService.removeById(bedId);
        if (flag) {
            return Result.ok("删除成功");
        }
        return Result.ok("删除失败");
    }
}
