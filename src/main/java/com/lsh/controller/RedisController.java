package com.lsh.controller;

import cn.hutool.json.JSONUtil;
import com.lsh.domain.Student;
import com.lsh.mapper.StudentMapper;
import com.lsh.utils.RedisCache;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/01/31
 */
@RequestMapping("redis")
@RestController
public class RedisController {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private StudentMapper studentMapper;
    @GetMapping
    public Result testCache(){
        List<Student> students = studentMapper.selectList(null);
        Map<String, String> map = students.stream()
                .collect(Collectors.toMap(student -> String.valueOf(student.getId()), JSONUtil::toJsonStr,(oldVal, currVal) -> oldVal));
        String key = "student2";
        
        return Result.ok();
    }
}
