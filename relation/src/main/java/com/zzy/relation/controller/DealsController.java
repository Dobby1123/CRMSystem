package com.zzy.relation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.relation.entity.Deals;
import com.zzy.relation.service.DealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 交易控制层
 * @author: zzy
 */
@RestController
@RequestMapping("deals")
public class DealsController {

    // 依赖注入
    @Autowired
    private DealsService dealsService;


    /**
     * 分页查询
     */
    @PostMapping("selectAll")
    public PageInfo<Deals> selectAll(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Deals> list = dealsService.queryAllByLimit(mp);
        PageInfo<Deals> pageInfo = new PageInfo<Deals>(list);
        return pageInfo;
    }

    @PostMapping("selectByCondition")
    public List<Deals> selectByCondition(@RequestBody Map<String,String> mp) {
        List<Deals> list = dealsService.queryAllByLimit(mp);
        return list;
    }

    /**
     * 修改
     */
    @RequestMapping("edit")
    public String edit(@RequestBody Deals deals) {
        try {
            dealsService.update(deals);
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
    public String add(@RequestBody Deals deals) {
        try {
            Date date = new Date();
            deals.setCreateTime(date);
            dealsService.insert(deals);
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
    public Deals selectOne(Integer id) {
        return dealsService.queryById(id);
    }


    /**
     * 通过主键删除数据
     */
    @GetMapping("deleteById")
    public String deleteById(Integer id) {
        try {
            dealsService.deleteById(id);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }


}
