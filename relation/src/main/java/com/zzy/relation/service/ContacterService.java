package com.zzy.relation.service;

import com.zzy.relation.entity.Contacter;
import com.zzy.relation.mapper.ContacterMapper;
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
public class ContacterService {

    @Autowired
    private ContacterMapper contacterDao;


    /**
     * 通过ID查询单条数据
     */
    public Contacter queryById(Integer nid) {
       return contacterDao.queryById(nid);
    }

    /**
     * 分页查询
     */
    public List<Contacter> queryAllByLimit(Map mp) {
       return contacterDao.queryAllByLimit(mp);
    }

    /**
     * 新增数据
     */
    public int insert(Contacter contacter) {
        return contacterDao.insert(contacter);
    }

    /**
     * 修改数据
     */
    public boolean update(Contacter contacter) {
        return contacterDao.update(contacter);
    }

    /**
     * 通过主键删除数据
     */
    public boolean deleteById(Integer id) {
        return contacterDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     */
    public List<Contacter> queryCondition(Contacter contacter) {
        return contacterDao.queryCondition(contacter);
    }


}
