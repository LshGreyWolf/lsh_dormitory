package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-03-05 12:55:32
 */
public interface UserService extends IService<User> {
//    List<User> selectAll();

    User login(String userName, String password);
    PageInfo<User> queryByPage(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    boolean updateUserById(User user);
    /**
     * @description 删除用户信息
     * @param ids
    */
    int delete(String ids);


    User getUser(User user);

    boolean updateStatusById(User user);


    User findByUserName( String userName);

    public String registerUser(User user);
}

