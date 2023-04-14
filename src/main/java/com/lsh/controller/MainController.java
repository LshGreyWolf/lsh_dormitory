package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;
import com.lsh.domain.Dormitory;
import com.lsh.domain.Notice;
import com.lsh.service.BuildingService;
import com.lsh.service.DormitoryService;
import com.lsh.service.DormitoryStudentService;
import com.lsh.service.NoticeService;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @version 1.0
 * @description TODO
 * @date 2023/4/13 14:44
 */
@RestController
@Slf4j
@RequestMapping("/main")
public class MainController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private DormitoryStudentService dormitoryStudentService;
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/building")
    public Result building(){

        List<Building> buildingList = buildingService.list();
        List<Map<String,Object>> list = new ArrayList<>();
        DecimalFormat df   = new DecimalFormat("######0.00");
        buildingList.forEach(entity->{
            Map<String,Object> map = new HashMap<>();

            List<Dormitory> dormitoryList =
                    dormitoryService.list(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getBuildingId, entity.getId()));

            int all = dormitoryList.size();
            map.put("name",entity.getName());
            map.put("all",all);

            //根据楼宇 查询宿舍的占有率
            int used = dormitoryStudentService.countByBuildingId(entity.getId());
            map.put("used",used);
            int unused = all-used;
            map.put("unused",unused);
            if(all == 0){
                map.put("percent",0);
            }else{
                map.put("percent",df.format((float)used/all));
            }

            list.add(map);
        });
        return Result.ok(list);
    }

    @GetMapping("/notice")
    public Result notice(){

        List<Notice> noticeList = noticeService.list();
        return Result.ok(noticeList);
    }

}
