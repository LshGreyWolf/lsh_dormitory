package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Bed;
import com.lsh.domain.Dormitory;
import com.lsh.domain.Record;
import com.lsh.domain.Student;
import com.lsh.service.BedService;
import com.lsh.service.DormitoryService;
import com.lsh.service.RecordService;
import com.lsh.service.StudentService;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/28
 */
@RestController
@Slf4j
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BedService bedService;
    @Autowired
    private DormitoryService dormitoryService;


    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Record record) {
        PageInfo<Record> recordPageInfo = recordService.queryByPage(record);
        List<Record> recordList = recordPageInfo.getList();
        recordList.forEach(item -> {
            Student student = studentService.getStudent(item.getStudentId());
            item.setStudent(student);
            Bed bed = bedService.getOne(new LambdaQueryWrapper<Bed>().eq(Bed::getId, item.getBedId()));
            item.setBed(bed);
            Dormitory dormitory = dormitoryService.getOne(new LambdaQueryWrapper<Dormitory>()
                    .eq(Dormitory::getId, item.getDormitoryId()));
            item.setDormitory(dormitory);
        });
        return Result.ok(recordPageInfo);
    }

}
