package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsh.domain.Menu;

import java.util.List;


/**
 * (Menu)表服务接口
 *
 * @author lsh
 * @since 2023-03-09 18:40:58
 */
public interface MenuService extends IService<Menu> {
    List<Menu> queryMenu(Integer userID);



}
