package com.zzy.relation.mapper;


import com.zzy.relation.entity.Menus;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface MenusMapper extends MyMapper<Menus> {

    /**
     * 通过ID查询单条数据
     */
    Menus queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<Menus> queryAllByLimit(Map mp);

    /**
     * 通过实体作为筛选条件查询
     */
    List<Menus> queryCondition(Menus menus);

    /**
     * 新增数据
     */
    int insert(Menus menus);

    /**
     * 修改数据
     */
    boolean update(Menus menus);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
