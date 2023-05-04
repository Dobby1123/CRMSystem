package com.zzy.relation.service;

import com.zzy.relation.entity.User;
import com.zzy.relation.mapper.UserMapper;
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
public class UserService {

    @Autowired
    private UserMapper userDao;


    /**
     * 通过ID查询单条数据
     */
    public User queryById(Integer nid) {
       return userDao.queryById(nid);
    }

    /**
     * 分页查询
     */
    public List<User> queryAllByLimit(Map mp) {
       return userDao.queryAllByLimit(mp);
    }

    /**
     * 新增数据
     */
    public int insert(User user) {
        return userDao.insert(user);
    }

    /**
     * 修改数据
     */
    public boolean update(User user) {
        return userDao.update(user);
    }

    /**
     * 通过主键删除数据
     */
    public boolean deleteById(Integer id) {
        return userDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     */
    public List<User> queryCondition(User user) {
        return userDao.queryCondition(user);
    }


}
