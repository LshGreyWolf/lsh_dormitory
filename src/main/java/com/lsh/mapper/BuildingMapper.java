package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 楼宇(Building)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-24 18:04:11
 */
@Mapper
public interface BuildingMapper extends BaseMapper<Building> {

    List<Building> BuildingQueryByPage(Building building);

    boolean saveBuilding(Building building);
}
