package com.lsh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface PermissionMapper {

    List<String> findByRoleId(@Param("roleIds") List<Integer> roleIds);
}