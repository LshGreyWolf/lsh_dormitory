package com.lsh.controller;

import com.lsh.domain.Menu;
import com.lsh.domain.User;
import com.lsh.mapper.UserMenuMapper;
import com.lsh.service.MenuService;
import com.lsh.service.UserMenuService;
import com.lsh.utils.JWTUtil;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/09
 */
@RestController
@Slf4j
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserMenuService userMenuService;

    @GetMapping("/query")
    public Result queryMenu(HttpServletRequest request) {
        List<Menu> menus = new ArrayList<>();
        if (request.getAttribute("user") != null) {
            User user = (User) request.getAttribute("user");
            menus = menuService.queryMenu(user.getId());
        }
//        else if(request.getAttribute("student") != null){
//            menus = menuService.queryByType();
//        }

        List<Menu> menuList1 = new ArrayList<>();
        //找出一级菜单
        for (Menu menu : menus) {
            if (menu.getParentId() == 0) {
                menuList1.add(menu);
            }
        }

        //嵌套循环找出关联设置child属性
        for (Menu parent : menuList1) {
            List<Menu> child = new ArrayList<>();
            for (Menu entity : menus) {
                if (parent.getId() == entity.getParentId()) {
                    child.add(entity);
                }
            }
            parent.setChild(child);
        }
        return Result.ok(menuList1);

    }

    @GetMapping("/tree")
    public Result tree(Integer checked,HttpServletRequest request) {
        //checked表示菜单是否被选中，查询的时候方便回显
        List<Integer> menuCheckedIdList=null;
        if (checked != null){
            //TODO 使用TreadLocal
            User user =(User) request.getAttribute("user");
            menuCheckedIdList  = userMenuService.getMenu(user.getId());
        }

        List<Menu> list = menuService.list(null);

        List<Map<String, Object>> menus = new ArrayList<>();

        for (Menu menu : list) {
            //父级菜单
            if (menu.getParentId() == 0) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", menu.getId());
                map.put("name", menu.getTitle());
                map.put("isParent", true);
                map.put("open", true);
                if (checked != null){
                    map.put("checked",menuCheckedIdList.contains(menu.getId()));
                }
                List<Map<String, Object>> child = new ArrayList<>();
                for (Menu menuChild : list) {
                    if (menuChild.getParentId() != 0 && menuChild.getParentId() == menu.getId()) {
                        HashMap<String, Object> childMap = new HashMap<>();
                        childMap.put("id", menuChild.getId());
                        childMap.put("name", menuChild.getTitle());
                        childMap.put("isParent", false);
                        childMap.put("open", false);
                        if (checked != null){
                            childMap.put("checked",menuCheckedIdList.contains(menuChild.getId()));
                        }
                        child.add(childMap);
                    }
                }

                map.put("children", child);
                menus.add(map);
            }

        }
        return Result.ok(menus);
    }
}
