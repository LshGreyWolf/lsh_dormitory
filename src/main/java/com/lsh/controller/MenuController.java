package com.lsh.controller;

import com.lsh.domain.Menu;
import com.lsh.domain.User;
import com.lsh.service.MenuService;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/query")
    public Result queryMenu(HttpServletRequest request) {
        List<Menu> menus = new ArrayList<>();
        if(request.getAttribute("user") != null){
            User user = (User) request.getAttribute("user");
            menus = menuService.queryMenu(user.getId());
        }
//        else if(request.getAttribute("student") != null){
//            menus = menuService.queryByType();
//        }

        List<Menu> menuList1 = new ArrayList<>();
        //找出一级菜单
        for (Menu menu : menus) {
            if(menu.getParentId() == 0){
                menuList1.add(menu);
            }
        }

        //嵌套循环找出关联设置child属性
        for (Menu parent : menuList1) {
            List<Menu> child = new ArrayList<>();
            for (Menu entity : menus) {
                if(parent.getId() == entity.getParentId()){
                    child.add(entity);
                }
            }
            parent.setChild(child);
        }
        return Result.ok(menuList1);

    }
}
