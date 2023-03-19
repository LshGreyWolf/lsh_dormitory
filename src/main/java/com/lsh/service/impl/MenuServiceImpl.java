package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Menu;
import com.lsh.mapper.MenuMapper;
import com.lsh.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.MenuService;

import java.util.List;

/**
 * (Menu)表服务实现类
 *
 * @author lsh
 * @since 2023-03-09 18:40:58
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> queryMenu(Integer userID) {

        return menuMapper.queryMenu(userID);
    }
}
