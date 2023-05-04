package com.zzy.relation.controller;

import com.zzy.relation.entity.ActivityDetails;
import com.zzy.relation.service.ActivityDetailsService;
import com.zzy.relation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * author zzy
 */
@RestController
@RequestMapping("activityDetails")
public class ActivityDetailsController {
    @Autowired
    private ActivityDetailsService ads;
    @Autowired
    private ActivityDetailsService as;
    @Autowired
    private UserService us;
    @RequestMapping("/add")
    public String add(@RequestBody Map<String,String> mp) {
        String a[]=new String[100];
        BigDecimal money=new BigDecimal(mp.get("money"));
        byte[] bytes = mp.get("图片").getBytes();
        ActivityDetails activityDetails = new ActivityDetails();
        try {
//            Date date = new Date();
//            activity.setCreateTime(date);
            ads.insert(activityDetails);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "201";
        }
    }
}
