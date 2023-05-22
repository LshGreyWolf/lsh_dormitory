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
import com.lsh.utils.RedisCache;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private RedisCache redisCache;

    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private DormitoryStudentService dormitoryStudentService;
    @Autowired
    private NoticeService noticeService;

    /**
     * 表格
     *
     * @return
     */
    @GetMapping("/building")
    public Result building() {

        List<Building> buildingList = buildingService.list();
        List<Map<String, Object>> list = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("######0.00");

        //根据所有楼宇的总宿舍量
        buildingList.forEach(entity -> {

            List<Dormitory> dormitoryList =
                    dormitoryService.list(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getBuildingId, entity.getId()));
            Map<String, Object> map = new HashMap<>();
            int all = dormitoryList.size();
            map.put("name", entity.getName());
            map.put("all", all);
            //根据楼宇 查询宿舍的占有率
            int used = dormitoryStudentService.countByBuildingId(entity.getId());
            map.put("used", used);
            int unused = all - used;
            map.put("unused", unused);
            if (all == 0) {
                map.put("percent", 0);
            } else {
                map.put("percent", df.format((float) used / all));
            }

            list.add(map);
        });

        return Result.ok(list);
    }

    /**
     * 折线图
     *
     * @return
     */
    @GetMapping("/buildingLine")
    public Result buildingLine() {

        List<Building> buildingList = buildingService.list();
        List<Map<String, Object>> list = new ArrayList<>();

        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        HashMap<String, Object> map4 = new HashMap<>();
        //宿舍数量
        ArrayList<Integer> countList = new ArrayList<>();
        //入住人数
        ArrayList<Integer> occupyList = new ArrayList<>();
        //闲置数量
        ArrayList<Integer> leaveUnusedList = new ArrayList<>();
        //使用率
        ArrayList<Integer> percentList = new ArrayList<>();

        buildingList.forEach(item -> {
            List<Dormitory> dormitoryList =
                    dormitoryService.list(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getBuildingId, item.getId()));
            map.put("name", "宿舍数量");
            map.put("type", "line");
            map.put("stack", "Total");
            countList.add(dormitoryList.size());
        });
        //每栋楼的宿舍数量
        map.put("data", countList);
        buildingList.forEach(item -> {
            map2.put("name", "入住人数");
            map2.put("type", "line");
            map2.put("stack", "Total");
            //根据楼栋id查询每栋楼宇的入住人数
            int used = dormitoryStudentService.countByBuildingId(item.getId());
            occupyList.add(used);

        });
        map2.put("data", occupyList);

        buildingList.forEach(item -> {
            List<Dormitory> dormitoryList =
                    dormitoryService.list(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getBuildingId, item.getId()));
            map3.put("name", "闲置数量");
            map3.put("type", "line");
            map3.put("stack", "Total");
            //总宿舍量
            int count = dormitoryList.size();
            //使用的宿舍量
            int used = dormitoryStudentService.countByBuildingId(item.getId());
            leaveUnusedList.add(count - used);
        });
        map3.put("data", leaveUnusedList);
        buildingList.forEach(item -> {

            List<Dormitory> dormitoryList =
                    dormitoryService.list(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getBuildingId, item.getId()));
            map4.put("name", "使用率");
            map4.put("type", "line");
            map4.put("stack", "Total");

            //总宿舍量
            int count = dormitoryList.size();
            //使用的宿舍量
            int used = dormitoryStudentService.countByBuildingId(item.getId());
            if (count == 0) {
                percentList.add(0);
            } else {
                percentList.add(used / count);
            }


        });
        map4.put("data", percentList);
        list.add(map);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        return Result.ok(list);
    }

    /**
     * 使用率饼状图
     *
     * @return
     */
    @GetMapping("/buildingRate")
    public Result buildingRate() {

        List<Building> buildingList = buildingService.list();
        List<Map<String, Object>> list = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("######0.00");
        buildingList.forEach(entity -> {
            Map<String, Object> map = new HashMap<>();

            List<Dormitory> dormitoryList =
                    dormitoryService.list(new LambdaQueryWrapper<Dormitory>().eq(Dormitory::getBuildingId, entity.getId()));

            int all = dormitoryList.size();
            //根据楼宇 查询宿舍的占有率
            int used = dormitoryStudentService.countByBuildingId(entity.getId());
            map.put("name", entity.getName());
            if (all == 0) {
                map.put("value", 0);
            } else {
                map.put("value", df.format((float) used / all));
            }

            list.add(map);
        });
        return Result.ok(list);
    }

    /**
     * 公告
     *
     * @return
     */
    @GetMapping("/notice")
    public Result notice() {
        List<Notice> noticeList = noticeService.list();
        return Result.ok(noticeList);
    }

    @GetMapping("/deleteAllRedisKey")
    public Result deleteAllRedisKey() {
        Collection<String> keys = redisCache.keys("*");
        redisCache.deleteObject(keys);
        return Result.ok(keys);
    }

}
