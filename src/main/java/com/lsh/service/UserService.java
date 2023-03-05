package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsh.domain.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-03-05 12:55:32
 */
public interface UserService extends IService<User> {
    List<User> selectAll();
}

