package com.lsh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;
import com.lsh.mapper.BuildingMapper;
import com.lsh.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 楼宇(Building)表服务实现类
 *
 * @author lsh
 * @since 2023-03-24 18:04:11
 */
@Service("buildingService")
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public PageInfo<Building> BuildingQueryByPage(Building building) {

        if (building.getPage() != null & building != null) {
            PageHelper.startPage(building.getPage(), building.getLimit());
        }
        return new PageInfo<Building>(buildingMapper.BuildingQueryByPage(building));


    }

    @Override
    public boolean saveBuilding(Building building) {

        boolean flag = buildingMapper.saveBuilding(building);
        return flag;
    }

    @Override
    public int deleteBuilding(String ids) {
        String[] idsArr = ids.split(",");
        int row = 0;
        for (String id : idsArr) {
            LambdaQueryWrapper<Building> queryWrapper = new LambdaQueryWrapper<Building>().eq(Building::getId, id);
            buildingMapper.delete(queryWrapper);
            row++;
        }
        return row;


    }
}
