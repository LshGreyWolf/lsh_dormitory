package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Menu;
import com.lsh.domain.UserMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (UserMenu)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-09 18:41:08
 */
@Mapper
public interface UserMenuMapper extends BaseMapper<UserMenu> {

    boolean saveUserMenu(@Param("userId") Integer userId, @Param("menuId") Integer menuId);

    List<Integer> getMenu(Integer userId);
}
