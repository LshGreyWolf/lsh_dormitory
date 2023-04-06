package com.lsh.controller;

import com.github.pagehelper.PageInfo;
import com.lsh.domain.SelectionDormitory;
import com.lsh.service.SelectionDormitoryService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/04/02
 */
@RestController
@RequestMapping("/SelectionDormitory")
public class SelectionDormitoryController {
    @Autowired
    private SelectionDormitoryService selectionDormitoryService;


    @PostMapping("/saveSelectionDormitory")
    public Result saveSelectionDormitory(@RequestBody Map<String, String> map) {
        //clazzId,dormitoryIds
        String clazzId = map.get("clazzId");
        String dormitoryIds = map.get("dormitoryIds");
        int flag = selectionDormitoryService.saveSelectionDormitory(clazzId, dormitoryIds);
        if (flag > 0) {
            return Result.ok("保存成功！");
        } else {
            return Result.fail("保存失败！");
        }
    }

    @PostMapping("updateSelectionDormitory")
    public Result updateSelectionDormitory(@RequestBody SelectionDormitory selectionDormitory) {
        int flag = selectionDormitoryService.updateSelectionDormitory(selectionDormitory);
        if (flag > 0) {
            return Result.ok("更新成功！");
        } else {
            return Result.fail("更新失败！");
        }
    }

    @PostMapping("/querySelectionDormitory")
    public Map<String, Object> querySelectionDormitory(@RequestBody SelectionDormitory selectionDormitory) {
        PageInfo<SelectionDormitory> pageInfo = selectionDormitoryService.querySelectionDormitory(selectionDormitory);
        return Result.ok(pageInfo);
    }
}
