package com.zzy.relation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.relation.entity.Contacter;
import com.zzy.relation.service.ContacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 联系人控制层
 * @author: zzy
 */
@RestController
@RequestMapping("contacter")
public class ContacterController {

    // 依赖注入
    @Autowired
    private ContacterService contacterService;


    /**
     * 分页查询
     */
    @PostMapping("selectAll")
    public PageInfo<Contacter> selectAll(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Contacter> list = contacterService.queryAllByLimit(mp);
        PageInfo<Contacter> pageInfo = new PageInfo<Contacter>(list);
        return pageInfo;
    }

    /**
     * 修改
     */
    @RequestMapping("edit")
    public String edit(@RequestBody Contacter contacter) {
        try {
            contacterService.update(contacter);
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
    public String add(@RequestBody Contacter contacter) {
        try {
            Date date = new Date();
            contacter.setCreateTime(date);
            contacterService.insert(contacter);
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
    public Contacter selectOne(Integer id) {
        return contacterService.queryById(id);
    }


    /**
     * 通过主键删除数据
     */
    @GetMapping("deleteById")
    public String deleteById(Integer id) {
        try {
            contacterService.deleteById(id);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }


}
