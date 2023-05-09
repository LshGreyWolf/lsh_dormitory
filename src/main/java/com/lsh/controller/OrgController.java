package com.lsh.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Grade;
import com.lsh.domain.Org;
import com.lsh.service.GradeService;
import com.lsh.service.OrgService;
import com.lsh.utils.RedisCache;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lsh.constants.RedisConstants.SCHOOL_TREE;

/**
 * (Org)表控制层
 *
 * @author makejava
 * @since 2023-03-18 14:55:18
 */
@RestController
@RequestMapping("org")
public class OrgController {
    @Autowired
    private OrgService orgService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private GradeService gradeService;

    /**
     * 年级树形结构
     * @param org
     * @return
     */
    @PostMapping("/tree")
    public Result tree(@RequestBody Org org) {
        PageInfo<Org> pageInfo = orgService.query(org);
        //所有的树形数据
        List<Org> list = pageInfo.getList();
        //要构建的树形结构
        List<Map<String, Object>> trees = new ArrayList<>();
        for (Org entity : list) {
            if (entity.getParentId() == 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", entity.getId());
                map.put("name", entity.getName());
                if (entity.getType() < 4) {
                    map.put("isParent", true);
                    map.put("open", true);
                    map.put("children", getChild(entity, list));
                } else {
                    map.put("isParent", false);
                }
                trees.add(map);
            }
        }
        redisCache.setCacheObject(SCHOOL_TREE, JSONUtil.toJsonStr(trees));
        return Result.ok(trees);
    }

    //自己调自己、有出口
    public List<Map<String, Object>> getChild(Org parent, List<Org> list) {
        List<Map<String, Object>> child = new ArrayList<>();
        for (Org org : list) {
            if (parent.getId() == org.getParentId()) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", org.getId());
                map.put("name", org.getName());
                if (org.getType() < 4) {
                    map.put("isParent", true);
                    map.put("children", getChild(org, list));
                    map.put("open", true);
                } else {
                    map.put("isParent", false);
                }
                child.add(map);
            }
        }
        return child;
    }

    /**
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Org org) {
        boolean flag = orgService.save(org);
        if (flag) {
            return Result.ok("新增成功！");
        }
        return Result.ok("新增失败！");
    }

    @PostMapping("/updateOrg")
    public Result updateOrg(@RequestBody Org org) {
        int flag = orgService.updateOrg(org);
        if (flag > 0) {
            return Result.ok("更新成功！");
        }
        return Result.ok("更新失败！");
    }

    @GetMapping("/delete")
    public Result delete(String ids) {
        int flag = orgService.delete(ids);
        if (flag > 0) {
            return Result.ok("删除成功！");
        }
        return Result.ok("删除失败！");
    }

    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Org org) {
        PageInfo<Org> pageInfo = orgService.queryByPage(org);
        pageInfo.getList().forEach(item->{
            Grade grade = gradeService.getOne(new LambdaQueryWrapper<Grade>().eq(Grade::getId, item.getGradeId()));
            item.setGradeName(grade.getName());
        });
        return Result.ok(pageInfo);
    }

    /**
     * 查询上级栏目名称，回显
     */
    @PostMapping("/queryParentName")
    public Result queryParentName(@RequestBody Org org) {
        Org org1 = orgService.getOne(new LambdaQueryWrapper<Org>().eq(Org::getId, org.getId()));
        Integer parentId = org1.getParentId();
        if (parentId == 0) {
            return Result.ok("");
        } else {
            Org org2 = orgService.getOne(new LambdaQueryWrapper<Org>().eq(Org::getId, parentId));
            String name = org2.getName();
            return Result.ok(name);
        }
    }

    /***
     * 查询年级名字，回显
     */
    @PostMapping("/queryGradeName")
    public Result queryGradeName(@RequestBody Org org) {
        Integer gradeId = org.getGradeId();
        Grade grade = gradeService.getOne(new LambdaQueryWrapper<Grade>().eq(Grade::getId, gradeId));
        String name = grade.getName();
        return Result.ok(name);
    }

}
