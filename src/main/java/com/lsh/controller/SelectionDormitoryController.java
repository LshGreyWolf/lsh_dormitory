package com.lsh.controller;

import com.github.pagehelper.PageInfo;
import com.lsh.domain.Org;
import com.lsh.domain.SelectionDormitory;
import com.lsh.service.DormitoryService;
import com.lsh.service.OrgService;
import com.lsh.service.SelectionDormitoryService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private OrgService orgService;

    /**
     * 为选择宿舍的班分配宿舍
     * @param map
     * @return
     */
    @PostMapping("/saveSelectionDormitory")
    public Result saveSelectionDormitory(@RequestBody Map<String, String> map) {
        //clazzId,dormitoryIds
        String clazzId = map.get("clazzId");
        String dormitoryIds = map.get("dormitoryIds");

        //查出该班级下分配的所有宿舍 TODO 有bug
        Org org = orgService.detail(Integer.valueOf(clazzId));
        SelectionDormitory selectionDormitory = new SelectionDormitory();
        selectionDormitory.setClazzId(org.getId());
        PageInfo<SelectionDormitory> selectionDormitoryPageInfo = selectionDormitoryService.querySelectionDormitory(selectionDormitory);

        List<SelectionDormitory> list = selectionDormitoryPageInfo.getList();
        String[] ids = dormitoryIds.split(",");
        //比较前端传来的宿舍id和已经分配宿舍的班级的宿舍id作比较。一个宿舍不能分配给两个班级（暂不考虑混合宿舍）
        for (SelectionDormitory selectionDormitory1 : list) {
            for (String id : ids) {
                if (selectionDormitory1.getDormitoryId().equals(Integer.valueOf(id))) {
                    return Result.fail("该宿舍已经分配，请选择其他宿舍。");
                }
            }
        }
        int flag = selectionDormitoryService.saveSelectionDormitory(clazzId, dormitoryIds);
        if (flag > 0) {
            return Result.ok("分配成功！");
        } else {
            return Result.fail("分配失败！");
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

    /**
     *查询所有选择宿舍的班级
     * @param selectionDormitory
     * @return
     */
    @PostMapping("/querySelectionDormitory")
    public Map<String, Object> querySelectionDormitory(@RequestBody SelectionDormitory selectionDormitory) {
        PageInfo<SelectionDormitory> pageInfo = selectionDormitoryService.querySelectionDormitory(selectionDormitory);
        return Result.ok(pageInfo);
    }
}
