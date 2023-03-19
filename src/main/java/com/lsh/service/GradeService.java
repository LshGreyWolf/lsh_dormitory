package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Grade;
import com.lsh.domain.User;


/**
 * (Grade)表服务接口
 *
 * @author makejava
 * @since 2023-03-18 13:00:25
 */
public interface GradeService extends IService<Grade> {
    /**
     * @param grade
     * @description 年纪管理的分页
     */
    PageInfo<Grade> gradeQueryByPage(Grade grade);


    int delete(String ids);

    boolean add(Grade grade);

    boolean updateGradeById(Grade grade);
}

