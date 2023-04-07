package com.lsh.controller;


import com.github.pagehelper.PageInfo;
import com.lsh.domain.Org;
import com.lsh.service.OrgService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//    @GetMapping("tree")
//    public Result tree() {
//        PageInfo<Org> pageInfo = orgService.query(null);
//        //所有的树形数据
//        List<Org> list = pageInfo.getList();
//        //要构建的树形结构
//        List<Map<String, Object>> trees = new ArrayList<>();
//        for (Org entity : list) {
//            if (entity.getParentId() == 0) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("id", entity.getId());
//                map.put("title", entity.getName());
//                if (entity.getType() < 4) {
////                    map.put("isParent", true);
//                    map.put("spread", true);
//                    map.put("children", getChild(entity, list));
//                } else {
////                    map.put("isParent", false);
//                }
//                trees.add(map);
//            }
//        }
//        return Result.ok(trees);
//    }
//
//    //自己调自己、有出口
//    public List<Map<String, Object>> getChild(Org parent, List<Org> list) {
//        List<Map<String, Object>> child = new ArrayList<>();
//        for (Org org : list) {
//            if (parent.getId() == org.getParentId()) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("id", org.getId());
//                map.put("title", org.getName());
//                if (org.getType() < 4) {
////                    map.put("isParent", true);
//                    map.put("children", getChild(org, list));
//                    map.put("spread", true);
//                } else {
////                    map.put("isParent", false);
//                }
//                child.add(map);
//            }
//        }
//        return child;
//    }

    @GetMapping("tree")
    public Result tree() {
        PageInfo<Org> pageInfo = orgService.query(null);
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
        int flag = orgService.create(org);
        if (flag > 0) {
            return Result.ok("新增成功！");
        }
        return Result.ok("新增失败！");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Org org) {
        int flag = orgService.update(org);
        if (flag > 0) {
            return Result.ok("更新成功！");
        }
        return Result.ok("更新失败！");
    }

    @GetMapping("/delete")
    public Result delete( Integer id) {
        int flag = orgService.delete(id);
        if (flag > 0) {
            return Result.ok("删除成功！");
        }
        return Result.ok("删除失败！");
    }

}
