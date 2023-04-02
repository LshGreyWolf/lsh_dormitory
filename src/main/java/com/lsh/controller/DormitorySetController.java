package com.lsh.controller;

import com.github.pagehelper.PageInfo;
import com.lsh.domain.DormitorySet;
import com.lsh.service.DormitorySetService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/04/02
 */
@RestController
@RequestMapping("/dormitorySet")
public class DormitorySetController {
    @Autowired
    private DormitorySetService dormitorySetService;
    @PostMapping("/saveDormitorySet")
    public Result saveDormitorySet(@RequestBody DormitorySet dormitorySet){

        boolean flag = dormitorySetService.saveDormitorySet(dormitorySet);
        return Result.ok("保存成功！");
    }


    @PostMapping("queryDormitorySet")
    public Map<String,Object> queryDormitorySet(@RequestBody  DormitorySet dormitorySet){
        PageInfo<DormitorySet> pageInfo = dormitorySetService.queryDormitorySet(dormitorySet);
        return Result.ok(pageInfo);
    }

    @GetMapping("/deleteDormitorySet")
    public Result deleteDormitorySet(String ids){
        int flag = dormitorySetService.deleteDormitorySet(ids);
        if(flag>0){
            return Result.ok("删除成功！");
        }else{
            return Result.fail("删除失败");
        }
    }


    @PostMapping("update")
    public Result updateDormitorySet(@RequestBody DormitorySet dormitorySet){
        int flag = dormitorySetService.updateDormitorySet(dormitorySet);
        if(flag>0){
            return Result.ok("修改成功！");
        }else{
            return Result.fail("修改失败！");
        }
    }
}
