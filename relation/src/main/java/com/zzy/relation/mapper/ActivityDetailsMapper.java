package com.zzy.relation.mapper;

import com.zzy.relation.entity.ActivityDetails;
import com.zzy.relation.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * author zzy
 */
@Mapper
public interface ActivityDetailsMapper extends MyMapper<ActivityDetails> {
    int insert(ActivityDetails activityDetails);
}
