package com.lsh.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.*;
import com.lsh.service.*;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lsh.constants.SystemConstants.USER_TYPE_ADMINISTRATORS;

/**
 * @author lsh
 * @since 2023-04-15 11:46:05
 */
@RestController
@RequestMapping("assess")
public class AssessController {
    @Autowired
    private AssessService assessService;
    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private UserService userService;
    @Autowired
    private StoreyService storeyService;

    @Autowired
    private BuildingService buildingService;

    /**
     * 评价分页管理
     *
     * @param assess
     * @return
     */
    @PostMapping("/queryByPage")
    public Map<String, Object> queryByPage(@RequestBody Assess assess) {
        User entity = UserHolder.getUser();
        User param = userService.getById(entity.getId());
        PageInfo<Assess> pageInfo = null;
        //如果登录的用户是宿管员，则只显示该宿管员管理的楼宇
        if (param.getType() == USER_TYPE_ADMINISTRATORS) {
            Building one = buildingService.getOne(new LambdaQueryWrapper<Building>().eq(Building::getUserId, param.getId()));
            assess.setBuildingId(one.getId());
            pageInfo = assessService.queryByPage(assess);
        } else {
            pageInfo = assessService.queryByPage(assess);
        }

        pageInfo.getList().forEach(item -> {
            Dormitory dormitory =
                    dormitoryService.getById(item.getDormitoryId());
            Storey storey = storeyService.getById(dormitory.getStoreyId());
            Building building = buildingService.getById(dormitory.getBuildingId());

            dormitory.setStorey(storey);
            dormitory.setBuilding(building);
            User user = userService.getById(item.getUserId());
            item.setDormitory(dormitory);
            item.setUser(user);
        });
        return Result.ok(pageInfo);
    }

    /**
     * 新增评价
     *
     * @param assess
     * @return
     */
    @PostMapping("/saveAssess")
    public Result saveAssess(@RequestBody Assess assess) {
        assess.setCreateTime(new Date());
        assess.setUserId(UserHolder.getUser().getId());
        assessService.save(assess);
        return Result.ok("新增成功！");
    }

    /**
     * 删除评价
     *
     * @param ids
     * @return
     */
    @GetMapping("/deleteAssess")
    public Result deleteAssess(String ids) {
        int flag = assessService.deleteAssess(ids);
        if (flag > 0) {
            return Result.ok("删除成功！");
        } else {
            return Result.fail("删除失败！");
        }
    }

    /**
     * 更新评价
     *
     * @param assess
     * @return
     */
    @PostMapping("/updateAssess")
    public Result updateAssess(@RequestBody Assess assess) {
        //更新评价时间
        assess.setUpdateTime(new Date());
        assessService.updateById(assess);
        return Result.ok("更新成功");
    }
}
