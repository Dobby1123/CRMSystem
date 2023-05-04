package com.zzy.relation.mapper;


import com.zzy.relation.entity.Sales;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface SalesMapper extends MyMapper<Sales> {

    /**
     * 通过ID查询单条数据
     */
    Sales queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<Sales> queryAllByLimit(Map mp);

    /**
     * 通过实体作为筛选条件查询
     */
    List<Sales> queryCondition(Sales sales);

    /**
     * 新增数据
     */
    int insert(Sales sales);

    /**
     * 修改数据
     */
    boolean update(Sales sales);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
