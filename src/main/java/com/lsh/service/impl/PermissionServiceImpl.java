package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.mapper.PermissionMapper;
import entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.PermissionService;

import java.util.List;

/**
 * (Permission)表服务实现类
 *
 * @author lsh
 * @since 2023-05-04 18:12:29
 */
@Service
public class PermissionServiceImpl  implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> findByRoleId(List<Integer> roleIds) {
        return permissionMapper.findByRoleId(roleIds);
    }
}
