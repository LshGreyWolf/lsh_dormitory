package com.lsh.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lsh.config.exception.MyException;
import com.lsh.domain.Menu;
import com.lsh.domain.Student;
import com.lsh.domain.User;
import com.lsh.domain.UserMenu;
import com.lsh.service.MenuService;
import com.lsh.service.UserMenuService;
import com.lsh.utils.RedisCache;
import com.lsh.constants.RedisConstants;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lsh.constants.RedisConstants.USER_MENU_KEY;
import static com.lsh.constants.RedisConstants.USER_MENU_TTL;

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
    @Autowired
    private RedisCache redisCache;

    /**
     * 菜单的树形结构
     *
     * @param request
     * @return
     */
    @GetMapping("/query")
    public Result queryMenu(HttpServletRequest request) {
        List<Menu> menus = new ArrayList<>();

        User user = (User) request.getAttribute("user");
        Student student = (Student) request.getAttribute("student");
        if (user != null) {

            menus = menuService.queryMenu(user.getId());
        } else if (student != null) {
            menus = menuService.queryByType();
        }

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

        //将菜单数据上传到redis
        redisCache.setCacheObject(USER_MENU_KEY, JSONUtil.toJsonStr(menuList1), USER_MENU_TTL, TimeUnit.MINUTES);

        return Result.ok(menuList1);

    }

    /**
     * 菜单列表的ztree树形结构
     *
     * @param checked
     * @return
     */
    @GetMapping("/tree")
    public Result tree(@RequestParam(value = "checked", required = false) Integer checked
            , @RequestParam(value = "id", required = false) Integer userId) {
        try {
            //checked表示菜单是否被选中，查询的时候方便回显
            List<Integer> menuCheckedIdList = null;
            if (!StringUtils.isEmpty(checked) && !StringUtils.isEmpty(userId)) {
                menuCheckedIdList = userMenuService.getMenu(userId);
            }
            //查询不为类型不为学生的菜单
            List<Menu> list = menuService.list(new LambdaQueryWrapper<Menu>().ne(Menu::getType, 1));

            List<Map<String, Object>> menus = new ArrayList<>();

            for (Menu menu : list) {
                //父级菜单
                if (menu.getParentId() == 0) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("id", menu.getId());
                    map.put("name", menu.getTitle());
                    map.put("isParent", true);
                    map.put("open", true);
                    if (checked != null) {
                        map.put("checked", menuCheckedIdList.contains(menu.getId()));
                    }
                    List<Map<String, Object>> child = new ArrayList<>();
                    for (Menu menuChild : list) {
                        if (menuChild.getParentId() != 0 && menuChild.getParentId() == menu.getId()) {
                            HashMap<String, Object> childMap = new HashMap<>();
                            childMap.put("id", menuChild.getId());
                            childMap.put("name", menuChild.getTitle());
                            childMap.put("isParent", false);
                            childMap.put("open", false);
                            if (!StringUtils.isEmpty(checked)) {
                                childMap.put("checked", menuCheckedIdList.contains(menuChild.getId()));
                            }
                            child.add(childMap);
                        }
                    }
                    map.put("children", child);
                    menus.add(map);
                }

            }
            return Result.ok(menus);
        } catch (MyException e) {
            e.printStackTrace();
            return Result.fail("系统异常，请稍后再试");
        }
    }

    /**
     * 查询所有的菜单
     *
     * @param request
     * @return
     */
    @GetMapping("/selectMenu")
    public Result selectMenu(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<Menu> menus = new ArrayList<>();
        if (!StringUtils.isEmpty(user)) {
            menus = menuService.queryMenu(user.getId());
        }
        return Result.ok(menus);
    }

    @GetMapping("/deleteMenu")
    @Transactional
    public Result deleteMenu(Integer id) {
        try {
            menuService.removeById(id);
            userMenuService.remove(new LambdaQueryWrapper<UserMenu>().eq(UserMenu::getMenuId, id));
            return Result.ok("删除成功！");
        } catch (MyException e) {
            e.printStackTrace();
            return Result.fail("系统异常，请稍后再试");
        }
    }


    @PostMapping("/updateMenu")
    public Result updateMenu(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return Result.ok("修改成功！");
    }
}
