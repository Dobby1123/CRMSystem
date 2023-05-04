package com.zzy.relation.service;

import com.zzy.relation.entity.Deals;
import com.zzy.relation.mapper.DealsMapper;
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
public class DealsService {

    @Autowired
    private DealsMapper dealsDao;


    /**
     * 通过ID查询单条数据
     */
    public Deals queryById(Integer nid) {
       return dealsDao.queryById(nid);
    }

    /**
     * 分页查询
     */
    public List<Deals> queryAllByLimit(Map mp) {
       return dealsDao.queryAllByLimit(mp);
    }

    /**
     * 新增数据
     */
    public int insert(Deals deals) {
        return dealsDao.insert(deals);
    }

    /**
     * 修改数据
     */
    public boolean update(Deals deals) {
        return dealsDao.update(deals);
    }

    /**
     * 通过主键删除数据
     */
    public boolean deleteById(Integer id) {
        return dealsDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     */
    public List<Deals> queryCondition(Deals deals) {
        return dealsDao.queryCondition(deals);
    }


}
