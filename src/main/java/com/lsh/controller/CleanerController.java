package com.lsh.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.*;
import com.lsh.service.BuildingService;
import com.lsh.service.CleanerService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * (Cleaner)表控制层
 *
 * @author lsh
 * @since 2023-04-15 10:04:44
 */
@RestController
@RequestMapping("cleaner")
public class CleanerController {
    @Autowired
    private CleanerService cleanerService;
    @Autowired
    private BuildingService buildingService;

    /**
     * 保洁人员的分页
     *
     * @param cleaner
     * @return
     */
    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Cleaner cleaner) {
        PageInfo<Cleaner> pageInfo = cleanerService.queryByPage(cleaner);
        pageInfo.getList().forEach(item -> {
            Building building = new Building();
            building.setId(item.getBuildingId());
            Building building1 = buildingService.getBuilding(building);
            item.setBuilding(building1);

        });
        return Result.ok(pageInfo);
    }

    /**
     * 添加保洁员（一名保洁员只能选择一栋楼）
     *
     * @param cleaner
     * @return
     */
    @PostMapping("/addCleaner")
    public Result addCleaner(@RequestBody Cleaner cleaner) {

        cleanerService.save(cleaner);
        return Result.ok("新增成功");
    }

    @PostMapping("/updateCleaner")
    public Result updateCleaner(@RequestBody Cleaner cleaner) {

        cleanerService.updateById(cleaner);
        return Result.ok("更新成功");
    }


    @GetMapping("/deleteCleaner")
    public Result deleteCleaner(String ids) {
        int flag = cleanerService.deleteCleaner(ids);
        if (flag > 0) {
            return Result.ok("删除成功！");
        } else {
            return Result.fail("删除失败！");
        }
    }
}
