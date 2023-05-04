package com.zzy.relation.service;

import com.zzy.relation.entity.ActivityDetails;
import com.zzy.relation.mapper.ActivityDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author zzy
 */
@Service
@Transactional
public class ActivityDetailsService {
    @Autowired
    private ActivityDetailsMapper adm;

    public int insert(ActivityDetails activityDetails){
        return adm.insert(activityDetails);
    }

}
