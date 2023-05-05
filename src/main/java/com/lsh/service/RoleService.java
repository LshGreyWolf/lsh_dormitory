package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsh.domain.Role;

import java.util.List;


/**
 * (Role)表服务接口
 *
 * @author lsh
 * @since 2023-05-04 18:12:40
 */
public interface RoleService {
    public List<Role> findRoleByUserId(Integer id);
}
