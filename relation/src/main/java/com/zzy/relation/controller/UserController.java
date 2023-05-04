package com.zzy.relation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzy.relation.entity.User;
import com.zzy.relation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户控制层
 * @author: zzy
 */
@RestController
@RequestMapping("user")
public class UserController {

    // 依赖注入
    @Autowired
    private UserService userService;


    /**
     * 分页查询
     */
    @PostMapping("selectAll")
    public PageInfo<User> selectAll(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<User> list = userService.queryAllByLimit(mp);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return pageInfo;
    }

    /**
     * 修改
     */
    @RequestMapping("edit")
    public String edit(@RequestBody User user) {
        try {
            List<User> userList = userService.queryCondition(new User());
            for(int i=0;i<userList.size();i++){
                if(user.getPhone().equals(userList.get(i).getPhone()) && !user.getId().equals(userList.get(i).getId())){
                    return "202";
                }
            }
            userService.update(user);
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
    public String add(@RequestBody User user) {
        try {
            List<User> userList = userService.queryCondition(new User());
            for(int i=0;i<userList.size();i++){
                if(user.getPhone().equals(userList.get(i).getPhone())){
                    return "202";
                }
            }
            Date date = new Date();
            user.setCreateTime(date);
            userService.insert(user);
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
    public User selectOne(Integer id) {
        return userService.queryById(id);
    }


    /**
     * 通过主键删除数据
     */
    @GetMapping("deleteById")
    public String deleteById(Integer id) {
        try {
            userService.deleteById(id);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }


}
