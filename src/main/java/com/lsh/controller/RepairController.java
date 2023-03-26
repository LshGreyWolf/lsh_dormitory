package com.lsh.controller;


import com.github.pagehelper.PageInfo;
import com.lsh.domain.Repair;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lsh.service.RepairService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * (Repair)表控制层
 *
 * @author lsh
 * @since 2023-03-25 17:23:28
 */
@RestController
@RequestMapping("repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    public Map<String, Object> queryByPage(@RequestBody Repair repair) {
        PageInfo<Repair> repairPageInfo = repairService.queryByPage(repair);

        return Result.ok(new PageInfo());
    }

}

