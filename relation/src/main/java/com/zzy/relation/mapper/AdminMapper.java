package com.zzy.relation.mapper;


import com.zzy.relation.entity.Admin;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface AdminMapper extends MyMapper<Admin> {

    /**
     * 通过ID查询单条数据
     */
    Admin queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<Admin> queryAllByLimit(Map mp);

    /**
     * 通过实体作为筛选条件查询
     */
    List<Admin> queryCondition(Admin admin);

    /**
     * 新增数据
     */
    int insert(Admin admin);

    /**
     * 修改数据
     */
    boolean update(Admin admin);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
