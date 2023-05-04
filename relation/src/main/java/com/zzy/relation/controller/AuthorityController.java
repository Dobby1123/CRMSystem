package com.zzy.relation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.relation.entity.Authority;
import com.zzy.relation.entity.Menus;
import com.zzy.relation.service.AuthorityService;
import com.zzy.relation.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 部门权限控制层
 * @author: zzy
 */
@RestController
@RequestMapping("authority")
public class AuthorityController {

    // 依赖注入
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private MenusService menusService;

    /**
     * 分页查询
     */
    @PostMapping("selectAll")
    public PageInfo<Authority> selectAll(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Authority> list = authorityService.queryAllByLimit(mp);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMid()!=null && !list.get(i).getMid().equals("")){
                String mids = list.get(i).getMid();
                List<String> midlIst= Arrays.asList(mids.split(","));
                List<String> midNames = new ArrayList<>();
                for(int n=0;n<midlIst.size();n++){
                    Menus menus = menusService.queryById(Integer.parseInt(midlIst.get(n)));
                    midNames.add(menus.getName());
                }
                list.get(i).setMname(String.join(",",midNames));
            }
        }
        PageInfo<Authority> pageInfo = new PageInfo<Authority>(list);
        return pageInfo;
    }

    @PostMapping("getMenuByDid")
    public List<Menus> getMenuByDid(@RequestBody Map<String,String> mp, HttpSession session) {
        List<Menus> menusList = new ArrayList<>();
        String type = (String)session.getAttribute("type");
        if(type.equals("01")){
            List<Menus> menus = menusService.queryAllByLimit(new HashMap());
            menusList = menus;
        }else{
            List<Authority> list = authorityService.queryAllByLimit(mp);
            if(list.size()>0){
                if(list.get(0).getMid()!=null && !list.get(0).getMid().equals("")){
                    String mids = list.get(0).getMid();
                    List<String> midlIst= Arrays.asList(mids.split(","));
                    midlIst = midlIst.stream().sorted(Comparator.comparing(Integer::new)).collect(Collectors.toList());
                    for(int n=0;n<midlIst.size();n++){
                        Menus menus = menusService.queryById(Integer.parseInt(midlIst.get(n)));
                        menusList.add(menus);
                    }
                }
            }
        }
        return menusList;
    }


    /**
     * 修改
     */
    @RequestMapping("edit")
    public String edit(@RequestBody Authority authority) {
        try {
            authorityService.update(authority);
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
    public String add(@RequestBody Authority authority) {
        try {
            Map mp = new HashMap();
            mp.put("did",authority.getDid());
            List<Authority> authorities = authorityService.queryAllByLimit(mp);
            if(authorities.size()>0){
                return "202";
            }
            Date date = new Date();
            authority.setCreateTime(date);
            authorityService.insert(authority);
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
    public Authority selectOne(Integer id) {
        return authorityService.queryById(id);
    }


    /**
     * 通过主键删除数据
     */
    @GetMapping("deleteById")
    public String deleteById(Integer id) {
        try {
            authorityService.deleteById(id);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }


}
