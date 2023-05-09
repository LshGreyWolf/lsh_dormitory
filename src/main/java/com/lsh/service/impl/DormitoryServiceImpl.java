package com.lsh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.domain.Bed;
import com.lsh.domain.Building;
import com.lsh.domain.Dormitory;
import com.lsh.domain.DormitorySet;
import com.lsh.mapper.BedMapper;
import com.lsh.mapper.BuildingMapper;
import com.lsh.mapper.DormitoryMapper;
import com.lsh.mapper.DormitorySetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lsh.service.DormitoryService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Dormitory)表服务实现类
 *
 * @author makejava
 * @since 2023-03-26 14:36:18
 */
@Service("dormitoryService")
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {

    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private DormitorySetMapper dormitorySetMapper;
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private BedMapper bedMapper;

    @Override
    public List<Dormitory> listNo() {
        return dormitoryMapper.listNo();
    }

    @Override
    public List<Dormitory> listDormitory(Dormitory dormitory) {


        return dormitoryMapper.listDormitory(dormitory);
    }

    @Override
    @Transactional
    public void initDormitory(Dormitory dormitory) {

        LambdaQueryWrapper<Dormitory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dormitory::getBuildingId, dormitory.getBuildingId());
        queryWrapper.eq(Dormitory::getStoreyId, dormitory.getStoreyId());

        //删除已有宿舍对应的床位信息
        //先查询该宿舍对应的床位信息
        List<Dormitory> dormitoryList = dormitoryMapper.listDormitory(dormitory);
        dormitoryList.forEach(i->{
            LambdaQueryWrapper<Bed> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Bed::getDormitoryId,i.getId());
            bedMapper.delete(wrapper);
        });
        //如果已存在宿舍信息，则删除
        dormitoryMapper.delete(queryWrapper);
        //无，则初始化
        DormitorySet dormitorySet = new DormitorySet();
        dormitorySet.setBuildingId(dormitory.getBuildingId());
        dormitorySet.setStoreyId(dormitory.getStoreyId());
        //查询出所有的宿舍预选设置
        List<DormitorySet> dormitorySets = dormitorySetMapper.queryDormitorySet(dormitorySet);
        //查出该性别和宿舍类型
        Building building1 = new Building();
        building1.setId(dormitory.getBuildingId());
        Building building = buildingMapper.getBuilding(building1);
        //循环预选设置
        dormitorySets.forEach(item -> {
            Dormitory entity = new Dormitory();
            //给宿舍初始化
            for (int i = item.getStart(); i <= item.getEnd(); i++) {
                entity.setNo(item.getPrefix() + i);
                entity.setBuildingId(item.getBuildingId());
                entity.setStoreyId(item.getStoreyId());
                entity.setSex(building.getSex());
                entity.setCapacity(item.getCapacity());
                dormitoryMapper.insert(entity);
                //初始化床位信息
                for (int j = 1; j <= entity.getCapacity(); j++) {
                    Bed bed = new Bed();
                    bed.setBno(entity.getNo() + "-" + j);
                    bed.setDormitoryId(entity.getId());
                    bedMapper.insert(bed);
                }
            }
        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int getVersion(Integer id) {

       return dormitoryMapper.getVersion(id);

    }

    @Override
    public int selectCountDormitory(Integer buildingId) {


        return dormitoryMapper.selectCountDormitory(buildingId);
    }
}

