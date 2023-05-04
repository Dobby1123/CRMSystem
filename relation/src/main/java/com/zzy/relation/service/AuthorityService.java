package com.zzy.relation.service;

import com.zzy.relation.entity.Authority;
import com.zzy.relation.mapper.AuthorityMapper;
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
public class AuthorityService {

    @Autowired
    private AuthorityMapper authorityDao;


    /**
     * 通过ID查询单条数据
     */
    public Authority queryById(Integer nid) {
       return authorityDao.queryById(nid);
    }

    /**
     * 分页查询
     */
    public List<Authority> queryAllByLimit(Map mp) {
       return authorityDao.queryAllByLimit(mp);
    }

    /**
     * 新增数据
     */
    public int insert(Authority authority) {
        return authorityDao.insert(authority);
    }

    /**
     * 修改数据
     */
    public boolean update(Authority authority) {
        return authorityDao.update(authority);
    }

    /**
     * 通过主键删除数据
     */
    public boolean deleteById(Integer id) {
        return authorityDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     */
    public List<Authority> queryCondition(Authority authority) {
        return authorityDao.queryCondition(authority);
    }


}
