package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Repair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Repair)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-25 17:23:28
 */
@Mapper
public interface RepairMapper extends BaseMapper<Repair> {


    List<Repair> queryByPage(Repair repair);

    int updateRepair(Repair repair);


}
