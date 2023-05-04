package com.zzy.relation.service;

import com.zzy.relation.entity.Activity;
import com.zzy.relation.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service接口实现类
 * @author: zzy
 */
@Service
@Transactional
public class ActivityService {

    @Autowired
    private ActivityMapper activityDao;


    /**
     * 通过ID查询单条数据
     */
    public Activity queryById(Integer nid) {
       return activityDao.queryById(nid);
    }

    /**
     * 分页查询
     */
    public List<Activity> queryAllByLimit(Map mp) {
       return activityDao.queryAllByLimit(mp);
    }

    public List<Activity> selectAct(){return activityDao.selectAct();}
    /**
     * 新增数据
     */
    public int insert(Activity activity) {
        return activityDao.insert(activity);
    }

    /**
     * 修改数据
     */
    public boolean update(Activity activity) {
        return activityDao.update(activity);
    }

    /**
     * 通过主键删除数据
     */
    public boolean deleteById(Integer id) {
        return activityDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     */
    public List<Activity> queryCondition(Activity activity) {
        return activityDao.queryCondition(activity);
    }


}
