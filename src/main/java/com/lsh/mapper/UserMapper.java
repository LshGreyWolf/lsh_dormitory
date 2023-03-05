package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-05 12:56:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAll();
}

