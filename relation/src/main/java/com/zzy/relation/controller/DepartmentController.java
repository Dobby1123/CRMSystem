package com.zzy.relation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.relation.entity.Department;
import com.zzy.relation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 部门控制层
 * @author: zzy
 */
@RestController
@RequestMapping("department")
public class DepartmentController {

    // 依赖注入
    @Autowired
    private DepartmentService departmentService;


    /**
     * 分页查询
     */
    @PostMapping("selectAll")
    public PageInfo<Department> selectAll(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Department> list = departmentService.queryAllByLimit(mp);
        PageInfo<Department> pageInfo = new PageInfo<Department>(list);
        return pageInfo;
    }


    @PostMapping("selectByCondition")
    public List<Department> selectByCondition(@RequestBody Map<String,String> mp) {
        List<Department> list = departmentService.queryAllByLimit(mp);
        return list;
    }


    /**
     * 修改
     */
    @RequestMapping("edit")
    public String edit(@RequestBody Department department) {
        try {
            departmentService.update(department);
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
    public String add(@RequestBody Department department) {
        try {
            Date date = new Date();
            department.setCreateTime(date);
            departmentService.insert(department);
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
    public Department selectOne(Integer id) {
        return departmentService.queryById(id);
    }


    /**
     * 通过主键删除数据
     */
    @GetMapping("deleteById")
    public String deleteById(Integer id) {
        try {
            departmentService.deleteById(id);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }


}
