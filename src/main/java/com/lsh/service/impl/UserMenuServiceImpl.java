package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Student;
import com.lsh.domain.UserMenu;
import com.lsh.mapper.StudentMapper;
import com.lsh.mapper.UserMenuMapper;
import com.lsh.service.UserMenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/22
 */
@Service
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenu> implements UserMenuService {
    @Autowired
    private UserMenuMapper userMenuMapper;

    @Override
    public boolean saveUserMenu(@Param("userId") Integer userId, @Param("menuId") Integer menuId) {
        return userMenuMapper.saveUserMenu(userId, menuId);
    }
}
