package com.changgou.service.impl;

import com.changgou.dao.SpecDao;
import com.changgou.goods.pojo.Spec;
import com.changgou.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 20:18
 * @Version 1.0
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecDao specDao;

    @Override
    public List<Spec> findAll() {
        return specDao.selectAll();
    }

    @Override
    public Spec findById(Integer id) {
        return specDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Spec spec) {
        specDao.updateByPrimaryKeySelective(spec);
    }

    @Override
    public void add(Spec spec) {
        specDao.insertSelective(spec);
    }

    @Override
    public void delete(Integer id) {
        specDao.deleteByPrimaryKey(id);
    }
}
