package com.zzy.relation.mapper;


import com.zzy.relation.entity.Deals;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface DealsMapper extends MyMapper<Deals> {

    /**
     * 通过ID查询单条数据
     */
    Deals queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<Deals> queryAllByLimit(Map mp);

    /**
     * 通过实体作为筛选条件查询
     */
    List<Deals> queryCondition(Deals deals);

    /**
     * 新增数据
     */
    int insert(Deals deals);

    /**
     * 修改数据
     */
    boolean update(Deals deals);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
