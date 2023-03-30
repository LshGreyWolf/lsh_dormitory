package com.lsh.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Student;
import com.lsh.domain.Visit;
import com.lsh.service.StudentService;
import com.lsh.service.VisitService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * (Visit)表控制层
 *
 * @author lsh
 * @since 2023-03-27 20:26:13
 */
@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/queryByPage")
    private Map<String, Object> queryByPage(@RequestBody Visit visit) {
        PageInfo<Visit> visitPageInfo = visitService.queryByPage(visit);
        List<Visit> visitList = visitPageInfo.getList();
        visitList.forEach(item -> {
            Student student = studentService.getStudent(item.getStudentId());
            item.setStudent(student);
        });
        return Result.ok(visitPageInfo);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Visit visit) {
        int flag = visitService.insert(visit);
        if (flag > 0) {
            return Result.ok("新增成功！");
        } else {
            return Result.fail("新增失败！");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Visit visit) {
        boolean flag = visitService.updateVisitor(visit);
        if (flag){
            return  Result.ok("更新成功！");
        }
        return Result.fail("更新失败");
    }

    @PostMapping("/delete")
    public Result delete(String ids){
        int row =  visitService.deleteVisitor(ids);
        if (row>0){
            return Result.ok("删除成功！");
        }
        return Result.fail("删除失败");
    }
}

