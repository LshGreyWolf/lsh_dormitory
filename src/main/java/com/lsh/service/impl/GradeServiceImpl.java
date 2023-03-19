package com.lsh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Grade;
import com.lsh.domain.User;
import com.lsh.mapper.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.GradeService;

/**
 * (Grade)表服务实现类
 *
 * @author makejava
 * @since 2023-03-18 13:00:25
 */
@Service("gradeService")
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public PageInfo<Grade> gradeQueryByPage(Grade grade) {
        if (grade != null && grade.getPage() != null) {
            PageHelper.startPage(grade.getPage(), grade.getLimit());
        }
        return new PageInfo<Grade>(gradeMapper.queryByPage(grade));

    }

    @Override
    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String id : arr) {
            LambdaQueryWrapper<Grade> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Grade::getId, id);
            if (!id.isEmpty()) {
                gradeMapper.delete(queryWrapper);
                row++;
            }
        }
        return row;
    }

    @Override
    public boolean add(Grade grade) {
        return gradeMapper.add(grade);
    }

    @Override
    public boolean updateGradeById(Grade grade) {

        return gradeMapper.updateGradeById(grade);

    }
}

