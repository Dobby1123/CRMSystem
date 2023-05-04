package com.zzy.relation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.relation.entity.Sales;
import com.zzy.relation.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 售后信息控制层
 * @author: zzy
 */
@RestController
@RequestMapping("sales")
public class SalesController {

    // 依赖注入
    @Autowired
    private SalesService salesService;


    /**
     * 分页查询
     */
    @PostMapping("selectAll")
    public PageInfo<Sales> selectAll(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Sales> list = salesService.queryAllByLimit(mp);
        PageInfo<Sales> pageInfo = new PageInfo<Sales>(list);
        return pageInfo;
    }

    /**
     * 修改
     */
    @RequestMapping("edit")
    public String edit(@RequestBody Sales sales) {
        try {
            salesService.update(sales);
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
    public String add(@RequestBody Sales sales) {
        try {
            Date date = new Date();
            sales.setCreateTime(date);
            salesService.insert(sales);
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
    public Sales selectOne(Integer id) {
        return salesService.queryById(id);
    }


    /**
     * 通过主键删除数据
     */
    @GetMapping("deleteById")
    public String deleteById(Integer id) {
        try {
            salesService.deleteById(id);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }


}
