package com.lsh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.User;
import com.lsh.mapper.UserMapper;
import com.lsh.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-03-05 12:55:32
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

//    @Override
//    public List<User> selectAll() {
//      return userMapper.selectAll();
//    }

    @Override
    public User login(String userName, String password) {


        return userMapper.login(userName, password);
    }


    @Override
    public PageInfo<User> queryByPage(User user) {

        if (user != null && user.getPage() != null) {
            PageHelper.startPage(user.getPage(), user.getLimit());
        }
        return new PageInfo<User>(userMapper.queryByPage(user));

    }

    @Override
    public boolean addUser(User user) {

        return userMapper.addUser(user);

    }

    @Override
    public boolean updateUserById(User user) {


        return userMapper.updateUserById(user);
    }

    @Override
    public int delete(String ids) {
        String[] arr = ids.split(",");
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        int row = 0;
        for (String id : arr) {
            if (!id.isEmpty()) {
                wrapper.eq(User::getId, id);
                userMapper.delete(wrapper);
                row++;
            }

        }
        return row;
    }

    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }


}

