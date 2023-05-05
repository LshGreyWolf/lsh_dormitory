package com.lsh.mapper;

import com.lsh.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/05/04
 */
@Mapper
public interface RoleMapper {
    List<Role> findRoleByUserId(@Param("userId") Integer userId);
}
