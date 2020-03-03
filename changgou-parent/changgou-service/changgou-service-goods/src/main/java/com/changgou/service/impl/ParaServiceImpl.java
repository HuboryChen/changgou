package com.changgou.service.impl;

import com.changgou.dao.ParaMapper;
import com.changgou.goods.pojo.Para;
import com.changgou.service.ParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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
    private ParaMapper paraMapper;

    /**
     * 分页  +  条件
     * @param para 条件
     * @param page 当前页
     * @param size 每页数量
     * @return
     */
    @Override
    public PageInfo<Para> findPage(Para para, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(para);
        List<Para> paras = paraMapper.selectByExample(example);
        return new PageInfo<>(paras);
    }

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Para> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Para> paras = paraMapper.selectAll();
        return new PageInfo<>(paras);
    }

    /**
     * 条件查询
     * @param para
     * @return
     */
    @Override
    public List<Para> findList(Para para) {
        Example example = createExample(para);
        return paraMapper.selectByExample(example);
    }

    /**
     * 构键条件
     * @param para
     * @return
     */
    public Example createExample(Para para){
        Example example = new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        if (para != null){
            if (!StringUtils.isEmpty(para.getName())){
                //根据参数名字查找
                criteria.andLike("name","%"+para.getName()+"%");
            }
        }
        return example;
    }

    @Override
    public List<Para> findAll() {
        return paraMapper.selectAll();
    }

    @Override
    public Para findById(Integer id) {
        return paraMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKeySelective(para);
    }

    @Override
    public void add(Para para) {
        paraMapper.insertSelective(para);
    }

    @Override
    public void delete(Integer id) {
        paraMapper.deleteByPrimaryKey(id);
    }
}
