package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Menu)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-09 18:40:58
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> queryMenu(Integer userID);
}
