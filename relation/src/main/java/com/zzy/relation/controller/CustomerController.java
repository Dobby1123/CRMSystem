package com.zzy.relation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.relation.entity.Customer;
import com.zzy.relation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 客户控制层
 * @author: zzy
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    // 依赖注入
    @Autowired
    private CustomerService customerService;


    /**
     * 分页查询
     */
    @PostMapping("selectAll")
    public PageInfo<Customer> selectAll(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Customer> list = customerService.queryAllByLimit(mp);
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);
        return pageInfo;
    }


    @PostMapping("selectByCondition")
    public List<Customer> selectByCondition(@RequestBody Map<String,String> mp) {
        List<Customer> list = customerService.queryAllByLimit(mp);
        return list;
    }

    /**
     * 修改
     */
    @RequestMapping("edit")
    public String edit(@RequestBody Customer customer) {
        try {
            customerService.update(customer);
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
    public String add(@RequestBody Customer customer) {
        try {
            Date date = new Date();
            customer.setCreateTime(date);
            customerService.insert(customer);
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
    public Customer selectOne(Integer id) {
        return customerService.queryById(id);
    }


    /**
     * 通过主键删除数据
     */
    @GetMapping("deleteById")
    public String deleteById(Integer id) {
        try {
            customerService.deleteById(id);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }


}
