package com.changgou.service.impl;

import com.changgou.dao.ParaDao;
import com.changgou.goods.pojo.Para;
import com.changgou.service.ParaService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 20:24
 * @Version 1.0
 *
 * 参数管理
 */
@Service
public class ParaServiceImpl implements ParaService {

    @Autowired
    private ParaDao paraDao;

    @Override
    public List<Para> findAll() {
        return paraDao.selectAll();
    }

    @Override
    public Para findById(Integer id) {
        return paraDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Para para) {
        paraDao.updateByPrimaryKeySelective(para);
    }

    @Override
    public void add(Para para) {
        paraDao.insertSelective(para);
    }

    @Override
    public void delete(Integer id) {
        paraDao.deleteByPrimaryKey(id);
    }
}
