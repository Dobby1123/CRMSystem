package com.zzy.relation.service;

import com.zzy.relation.entity.Department;
import com.zzy.relation.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service接口实现类
 * @author: zzy
 */
@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentDao;


    /**
     * 通过ID查询单条数据
     */
    public Department queryById(Integer nid) {
       return departmentDao.queryById(nid);
    }

    /**
     * 分页查询
     */
    public List<Department> queryAllByLimit(Map mp) {
       return departmentDao.queryAllByLimit(mp);
    }

    /**
     * 新增数据
     */
    public int insert(Department department) {
        return departmentDao.insert(department);
    }

    /**
     * 修改数据
     */
    public boolean update(Department department) {
        return departmentDao.update(department);
    }

    /**
     * 通过主键删除数据
     */
    public boolean deleteById(Integer id) {
        return departmentDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     */
    public List<Department> queryCondition(Department department) {
        return departmentDao.queryCondition(department);
    }


}
