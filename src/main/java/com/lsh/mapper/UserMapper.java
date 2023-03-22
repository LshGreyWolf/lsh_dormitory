package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-05 12:56:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAll(User user);

    User login(String userName, String password);

   List<User> queryByPage(User user);


    boolean addUser(User user);

    boolean updateUserById(User user);


    User getUser(User user);
}

