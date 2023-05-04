package com.zzy.relation.service;

import com.zzy.relation.entity.Customer;
import com.zzy.relation.mapper.CustomerMapper;
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
public class CustomerService {

    @Autowired
    private CustomerMapper customerDao;


    /**
     * 通过ID查询单条数据
     */
    public Customer queryById(Integer nid) {
       return customerDao.queryById(nid);
    }

    /**
     * 分页查询
     */
    public List<Customer> queryAllByLimit(Map mp) {
       return customerDao.queryAllByLimit(mp);
    }

    /**
     * 新增数据
     */
    public int insert(Customer customer) {
        return customerDao.insert(customer);
    }

    /**
     * 修改数据
     */
    public boolean update(Customer customer) {
        return customerDao.update(customer);
    }

    /**
     * 通过主键删除数据
     */
    public boolean deleteById(Integer id) {
        return customerDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     */
    public List<Customer> queryCondition(Customer customer) {
        return customerDao.queryCondition(customer);
    }


}
