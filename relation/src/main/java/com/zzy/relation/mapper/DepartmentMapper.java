package com.zzy.relation.mapper;


import com.zzy.relation.entity.Department;
import com.zzy.relation.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * DAO层接口
 * @author: zzy
 */
public interface DepartmentMapper extends MyMapper<Department> {

    /**
     * 通过ID查询单条数据
     */
    Department queryById(Integer nid);

    /**
     * 分页查询数据
     */
    List<Department> queryAllByLimit(Map mp);

    /**
     * 通过实体作为筛选条件查询
     */
    List<Department> queryCondition(Department department);

    /**
     * 新增数据
     */
    int insert(Department department);

    /**
     * 修改数据
     */
    boolean update(Department department);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}
