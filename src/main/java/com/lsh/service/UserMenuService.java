package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsh.domain.UserMenu;
import org.apache.ibatis.annotations.Param;


/**
 * (UserMenu)表服务接口
 *
 * @author lsh
 * @since 2023-03-09 18:41:09
 */
public interface UserMenuService extends IService<UserMenu> {
    public boolean saveUserMenu(@Param("userId") Integer userId, @Param("menuId") Integer menuId);
}
