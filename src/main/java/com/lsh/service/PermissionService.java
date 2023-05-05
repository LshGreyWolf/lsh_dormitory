package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import entity.Permission;

import java.util.List;


/**
 * (Permission)表服务接口
 *
 * @author lsh
 * @since 2023-05-04 18:12:28
 */
public interface PermissionService {
    public List<String> findByRoleId(List<Integer> roleIds);

}
