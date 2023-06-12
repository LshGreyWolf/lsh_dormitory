package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;


/**
 * 楼宇(Building)表服务接口
 *
 * @author lsh
 * @since 2023-03-24 18:04:11
 */
public interface BuildingService extends IService<Building> {

    PageInfo<Building> BuildingQueryByPage(Building building);

    boolean saveBuilding(Building building);

    int deleteBuilding(String ids);

    Building getBuilding(Building building);
    boolean updateBuilding(Building building);
}
