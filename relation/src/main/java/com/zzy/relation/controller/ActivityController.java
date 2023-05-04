package com.zzy.relation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.relation.entity.Activity;
import com.zzy.relation.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 市场活动控制层
 * @author: zzy
 */
@RestController
@RequestMapping("activity")
public class ActivityController {

    // 依赖注入
    @Autowired
    private ActivityService activityService;


    /**
     * 分页查询
     */
    @PostMapping("selectAll")
    public PageInfo<Activity> selectAll(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Activity> list = activityService.queryAllByLimit(mp);
        PageInfo<Activity> pageInfo = new PageInfo<Activity>(list);
        return pageInfo;
    }

    @PostMapping("selectAct")
    public List<Activity> selectAct(){
        List<Activity> list=activityService.selectAct();
        return list;
    }

    @PostMapping("selectByCondition")
    public List<Activity> selectByCondition(@RequestBody Map<String,String> mp) {
        List<Activity> list = activityService.queryAllByLimit(mp);
        return list;
    }

    /**
     * 修改
     */
    @RequestMapping("edit")
    public String edit(@RequestBody Activity activity) {
        try {
            activityService.update(activity);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }

    /**
     * 新增
     */
    @RequestMapping("add")
    public String add(@RequestBody Activity activity) {
        try {
            Date date = new Date();
            activity.setCreateTime(date);
            activityService.insert(activity);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("selectOne")
    public Activity selectOne(Integer id) {
        return activityService.queryById(id);
    }


    /**
     * 通过主键删除数据
     */
    @GetMapping("deleteById")
    public String deleteById(Integer id) {
        try {
            activityService.deleteById(id);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }


}
