package com.zzy.relation.mapper;


import com.zzy.relation.entity.Activity;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface ActivityMapper extends MyMapper<Activity> {

    /**
     * 通过ID查询单条数据
     */
    Activity queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<Activity> queryAllByLimit(Map mp);

    List<Activity> selectAct();
    /**
     * 通过实体作为筛选条件查询
     */
    List<Activity> queryCondition(Activity activity);

    /**
     * 新增数据
     */
    int insert(Activity activity);

    /**
     * 修改数据
     */
    boolean update(Activity activity);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
