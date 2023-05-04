package com.zzy.relation.mapper;


import com.zzy.relation.entity.Contacter;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface ContacterMapper extends MyMapper<Contacter> {

    /**
     * 通过ID查询单条数据
     */
    Contacter queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<Contacter> queryAllByLimit(Map mp);

    /**
     * 通过实体作为筛选条件查询
     */
    List<Contacter> queryCondition(Contacter contacter);

    /**
     * 新增数据
     */
    int insert(Contacter contacter);

    /**
     * 修改数据
     */
    boolean update(Contacter contacter);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
