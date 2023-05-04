package com.zzy.relation.service;

import com.zzy.relation.entity.Menus;
import com.zzy.relation.mapper.MenusMapper;
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
public class MenusService {

    @Autowired
    private MenusMapper menusDao;


    /**
     * 通过ID查询单条数据
     */
    public Menus queryById(Integer nid) {
       return menusDao.queryById(nid);
    }

    /**
     * 分页查询
     */
    public List<Menus> queryAllByLimit(Map mp) {
       return menusDao.queryAllByLimit(mp);
    }

    /**
     * 新增数据
     */
    public int insert(Menus menus) {
        return menusDao.insert(menus);
    }

    /**
     * 修改数据
     */
    public boolean update(Menus menus) {
        return menusDao.update(menus);
    }

    /**
     * 通过主键删除数据
     */
    public boolean deleteById(Integer id) {
        return menusDao.deleteById(id) > 0;
    }

    /**
     * 根据条件查询
     */
    public List<Menus> queryCondition(Menus menus) {
        return menusDao.queryCondition(menus);
    }


}
