package com.lsh.controller;

import com.github.pagehelper.PageInfo;
import com.lsh.domain.Grade;
import com.lsh.domain.User;
import com.lsh.service.GradeService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lenovo
 * @version 1.0
 * @description
 * @date 2023/3/18 13:02
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping("/gradeQueryByPage")
    public Map<String, Object> gradeQueryByPage(@RequestBody Grade grade) {
        PageInfo<Grade> pageInfo = gradeService.gradeQueryByPage(grade);
        return Result.ok(pageInfo);
    }

    /**
     * 新增年级
     *
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Grade grade) {
        boolean flag = gradeService.add(grade);
        if (flag) {
            return Result.ok("新增年级成功！");
        }
        return Result.fail("新增年级失败！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Grade grade) {

        boolean flag = gradeService.updateGradeById(grade);
        if (flag){
            return Result.ok("修改成功！");
        }
        return Result.ok("修改失败！");
    }

    @GetMapping("/delete")
    public Result deleteUser(String ids) {
        int row = gradeService.delete(ids);
        if (row > 0) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");

    }


}
