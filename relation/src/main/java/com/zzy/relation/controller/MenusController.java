package com.zzy.relation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.relation.entity.Authority;
import com.zzy.relation.entity.Menus;
import com.zzy.relation.service.AuthorityService;
import com.zzy.relation.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @description: 菜单控制层
 * @author: zzy
 */
@RestController
@RequestMapping("menus")
public class MenusController {

    // 依赖注入
    @Autowired
    private MenusService menusService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 分页查询
     */
    @PostMapping("selectAll")
    public PageInfo<Menus> selectAll(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Menus> list = menusService.queryAllByLimit(mp);
        PageInfo<Menus> pageInfo = new PageInfo<Menus>(list);
        return pageInfo;
    }


    @PostMapping("selectByCondition")
    public List<Menus> selectByCondition(@RequestBody Map<String,String> mp) {
        List<Menus> list = menusService.queryAllByLimit(mp);
        return list;
    }

    /**
     * 修改
     */
    @RequestMapping("edit")
    public String edit(@RequestBody Menus menus) {
        try {
            menusService.update(menus);
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
    public String add(@RequestBody Menus menus) {
        try {
            Date date = new Date();
            menus.setCreateTime(date);
            menusService.insert(menus);
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
    public Menus selectOne(Integer id) {
        return menusService.queryById(id);
    }


    /**
     * 通过主键删除数据
     */
    @GetMapping("deleteById")
    public String deleteById(Integer id) {
        try {
            menusService.deleteById(id);
            Map mp = new HashMap();
            List<Authority> authorities = authorityService.queryAllByLimit(mp);
            for(int i=0;i<authorities.size();i++){
                String mids = authorities.get(i).getMid();
                List<String> midlIst= new ArrayList<>(Arrays.asList(mids.split(",")));
                boolean flag = false;
                for(int j=0;j<midlIst.size();j++) {
                    if(String.valueOf(id).equals(midlIst.get(j))) {
                        midlIst.remove(j);
                        flag = true;
                    }
                }
                //如果删了这个菜单，需要移除权限中这个
                if(flag){
                    if(midlIst.size()>0){
                        String midTemp = String.join(",", midlIst);
                        Authority authority = new Authority();
                        authority.setId(authorities.get(i).getId());
                        authority.setMid(midTemp);
                        authorityService.update(authority);
                    }else{
                        authorityService.deleteById(authorities.get(i).getId());
                    }
                }
            }
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }


}
