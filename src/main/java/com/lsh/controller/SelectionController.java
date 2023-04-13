package com.lsh.controller;

import com.github.pagehelper.PageInfo;
import com.lsh.domain.Org;
import com.lsh.domain.Selection;
import com.lsh.service.OrgService;
import com.lsh.service.SelectionService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/querySelection")
    public Map<String, Object> querySelection(@RequestBody Selection selection) {
        PageInfo<Selection> pageInfo = selectionService.querySelection(selection);
        pageInfo.getList().forEach(item -> {
            List<Org> clazzes = orgService.queryOrgBySelectionId(item.getId());
            item.setClazzes(clazzes);
        });
        return Result.ok(pageInfo);
    }

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
    public Result deleteSelection(String ids){
       boolean flag =  selectionService.deleteSelection(ids);
       if (flag){
           return Result.ok("删除成功！");
       }
       return Result.fail("删除失败！");
    }
}
