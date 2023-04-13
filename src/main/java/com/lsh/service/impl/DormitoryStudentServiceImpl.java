package com.lsh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Dormitory;
import com.lsh.domain.DormitoryStudent;
import com.lsh.mapper.DormitoryMapper;
import com.lsh.mapper.DormitoryStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.DormitoryStudentService;

import java.util.Date;
import java.util.List;

/**
 * (DormitoryStudent)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:28:10
 */
@Service("dormitoryStudentService")
public class DormitoryStudentServiceImpl extends ServiceImpl<DormitoryStudentMapper, DormitoryStudent> implements DormitoryStudentService {
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private DormitoryStudentMapper dormitoryStudentMapper;

    @Override
    public synchronized int selectDormitorySubmit(Integer studentId, Integer dormitoryId, Integer bedId) {
        Dormitory dormitory = dormitoryMapper.selectById(dormitoryId);
        //得出该宿舍的容量
        Integer capacity = dormitory.getCapacity();
        DormitoryStudent dormitoryStudent = new DormitoryStudent();
        dormitoryStudent.setDormitoryId(dormitoryId);
        //得到该宿舍已经选择的数量
        List<DormitoryStudent> selectList = dormitoryStudentMapper.selectList(new LambdaQueryWrapper<DormitoryStudent>().eq(DormitoryStudent::getDormitoryId, dormitoryId));
        if (capacity == selectList.size()) {
            //不能选择
            return 0;
        } else {
            //先删除该学生已经选择的信息
            LambdaQueryWrapper<DormitoryStudent> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DormitoryStudent::getStudentId,studentId);
            dormitoryStudentMapper.delete(queryWrapper);
            //
            DormitoryStudent dormitoryStudent1 = new DormitoryStudent();
            dormitoryStudent1.setDormitoryId(dormitoryId);
            dormitoryStudent1.setBedId(bedId);
            dormitoryStudent1.setStudentId(studentId);
            //入住时间
            dormitoryStudent1.setCheckin(new Date());
            //1-已入住 0-待入住
            dormitoryStudent1.setStatus(1);

            dormitoryStudentMapper.insert(dormitoryStudent1);
        }
        return 1;
    }


}
