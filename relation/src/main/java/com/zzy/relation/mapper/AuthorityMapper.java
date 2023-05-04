package com.zzy.relation.mapper;


import com.zzy.relation.entity.Authority;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface AuthorityMapper extends MyMapper<Authority> {

    /**
     * 通过ID查询单条数据
     */
    Authority queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<Authority> queryAllByLimit(Map mp);

    /**
     * 通过实体作为筛选条件查询
     */
    List<Authority> queryCondition(Authority authority);

    /**
     * 新增数据
     */
    int insert(Authority authority);

    /**
     * 修改数据
     */
    boolean update(Authority authority);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
