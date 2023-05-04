package com.zzy.relation.controller;

import com.zzy.relation.entity.Activity;
import com.zzy.relation.entity.Customer;
import com.zzy.relation.entity.Deals;
import com.zzy.relation.entity.ParamEntity;
import com.zzy.relation.service.ActivityService;
import com.zzy.relation.service.CustomerService;
import com.zzy.relation.service.DealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("statistic")
public class StatisticController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DealsService dealsService;


    /**
     * 获取饼状图
     */
    @RequestMapping("getInfo")
    public List<ParamEntity> getReport() {
        try {
            List<ParamEntity> paramEntities = new ArrayList<>();
            //举办的市场活动
            Map mp = new HashMap();
            List<Activity> activities = activityService.queryAllByLimit(mp);
            ParamEntity paramEntity = new ParamEntity();
            paramEntity.setName("市场活动数");
            paramEntity.setValue(activities.size());
            paramEntities.add(paramEntity);
            //市场活动产生的潜在客户
            Map mp2 = new HashMap();
            List<Customer> customers = customerService.queryAllByLimit(mp2);
            ParamEntity paramEntity2 = new ParamEntity();
            paramEntity2.setName("潜在客户数");
            paramEntity2.setValue(customers.size());
            paramEntities.add(paramEntity2);
            //潜在客户转化为客户
            Map mp3 = new HashMap();
            mp3.put("status","02");
            List<Customer> customers3 = customerService.queryAllByLimit(mp3);
            ParamEntity paramEntity3 = new ParamEntity();
            paramEntity3.setName("转化客户数");
            paramEntity3.setValue(customers3.size());
            paramEntities.add(paramEntity3);
            //产生交易客户数
            Map mp4 = new HashMap();
            List<Deals> deals = dealsService.queryAllByLimit(mp4);
            deals = deals.stream().collect(Collectors.collectingAndThen(
                    Collectors.toCollection(() -> new TreeSet<>(
                            Comparator.comparing(p -> p.getCid()))), ArrayList::new));
            ParamEntity paramEntity4 = new ParamEntity();
            paramEntity4.setName("交易客户数");
            paramEntity4.setValue(deals.size());
            paramEntities.add(paramEntity4);
            return paramEntities;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
