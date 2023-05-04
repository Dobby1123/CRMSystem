package com.zzy.relation.service;

import com.zzy.relation.entity.Sales;
import com.zzy.relation.mapper.SalesMapper;
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
public class SalesService {

    @Autowired
    private SalesMapper salesDao;


    /**
     * 通过ID查询单条数据
     */
    public Sales queryById(Integer nid) {
       return salesDao.queryById(nid);
    }

    /**
     * 分页查询
     */
    public List<Sales> queryAllByLimit(Map mp) {
       return salesDao.queryAllByLimit(mp);
    }

    /**
     * 新增数据
     */
    public int insert(Sales sales) {
        return salesDao.insert(sales);
    }

    /**
     * 修改数据
     */
    public boolean update(Sales sales) {
        return salesDao.update(sales);
    }

    /**
     * 通过主键删除数据
     */
    public boolean deleteById(Integer id) {
        return salesDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     */
    public List<Sales> queryCondition(Sales sales) {
        return salesDao.queryCondition(sales);
    }


}
