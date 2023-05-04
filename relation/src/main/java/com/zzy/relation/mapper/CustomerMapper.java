package com.zzy.relation.mapper;


import com.zzy.relation.entity.Customer;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface CustomerMapper extends MyMapper<Customer> {

    /**
     * 通过ID查询单条数据
     */
    Customer queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<Customer> queryAllByLimit(Map mp);

    /**
     * 通过实体作为筛选条件查询
     */
    List<Customer> queryCondition(Customer customer);

    /**
     * 新增数据
     */
    int insert(Customer customer);

    /**
     * 修改数据
     */
    boolean update(Customer customer);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
