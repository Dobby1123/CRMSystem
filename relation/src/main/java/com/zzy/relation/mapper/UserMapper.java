package com.zzy.relation.mapper;


import com.zzy.relation.entity.User;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface UserMapper extends MyMapper<User> {

    /**
     * 通过ID查询单条数据
     */
    User queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<User> queryAllByLimit(Map mp);

    /**
     * 通过实体作为筛选条件查询
     */
    List<User> queryCondition(User user);

    /**
     * 新增数据
     */
    int insert(User user);

    /**
     * 修改数据
     */
    boolean update(User user);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
