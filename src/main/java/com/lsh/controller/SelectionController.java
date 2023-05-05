package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Org;
import com.lsh.domain.Selection;
import com.lsh.domain.SelectionJoiner;
import com.lsh.domain.Student;
import com.lsh.service.OrgService;
import com.lsh.service.SelectionJoinerService;
import com.lsh.service.SelectionService;
import com.lsh.service.StudentService;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/04/02
 */
@RestController
@RequestMapping("/selection")
public class SelectionController {
    @Autowired
    private SelectionService selectionService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SelectionJoinerService selectionJoinerService;

    /**
     * 宿舍选择分页
     *
     * @param selection
     * @return
     */
    @PostMapping("/querySelection")
    public Map<String, Object> querySelection(@RequestBody Selection selection) {
        PageInfo<Selection> pageInfo = selectionService.querySelection(selection);
        pageInfo.getList().forEach(item -> {
            List<Org> clazzes = orgService.queryOrgBySelectionId(item.getId());
            item.setClazzes(clazzes);
        });
        return Result.ok(pageInfo);
    }

    /**
     * 新增宿舍选择
     * @param selection
     * @return
     */
    @PostMapping("/saveSelection")
    public Result saveSelection(@RequestBody Selection selection) {
        selectionService.saveSelection(selection);
        return Result.ok("新增成功！");
    }

    @PostMapping("/updateSelection")
    public Result updateSelection(@RequestBody Selection selection) {
        selectionService.updateSelection(selection);
        return Result.ok("更新成功！");
    }

    @GetMapping("/deleteSelection")
    public Result deleteSelection(String ids) {
        boolean flag = selectionService.deleteSelection(ids);
        if (flag) {
            return Result.ok("删除成功！");
        }
        return Result.fail("删除失败！");
    }

    /**
     * 根据学生查出该班级的选择宿舍的时间
     *
     * @return
     */
    @PostMapping("/selectTimeByStudent")
    public Result selectTimeByStudent() {
        //根据学生的班级id查询出选择id
        Student student = studentService.getById(UserHolder.getStudent().getId());
        Integer clazzId = student.getClazzId();
        SelectionJoiner joiner =
                selectionJoinerService
                        .getOne(new LambdaQueryWrapper<SelectionJoiner>().eq(SelectionJoiner::getClazzId, clazzId));
        Integer selectionId = joiner.getSelectionId();
        Selection selection = selectionService.getById(selectionId);
        Date startTime = selection.getStartTime();
        Date endTime = selection.getEndTime();
        HashMap<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        return Result.ok(map);
    }
}
