package com.changgou.service.impl;

import com.changgou.dao.SpecMapper;
import com.changgou.goods.pojo.Spec;
import com.changgou.service.SpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 20:18
 * @Version 1.0
 *
 * 规格管理
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    /**
     * 分页  + 条件
     * @param spec   条件
     * @param page   当前页
     * @param size   每页条数
     * @return
     */
    @Override
    public PageInfo<Spec> findPage(Spec spec, int page, int size) {
        PageHelper.startPage(page,size);
        //构造条件 查询数量
        Example example = createExample(spec);
        List<Spec> specs = specMapper.selectByExample(example);
        return new PageInfo<>(specs);
    }

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Spec> findPage(int page, int size) {
        //分页实现
        PageHelper.startPage(page, size);
        //查找数据
        List<Spec> specs = specMapper.selectAll();
        //将数据封装进PageInfo
        return new PageInfo<>(specs);
    }

    /**
     * 多条件查找
     * @param spec
     * @return
     */
    @Override
    public List<Spec> findList(Spec spec) {
        //构建 条件
        Example example = createExample(spec);
        //根据条件查找，并返回
        return   specMapper.selectByExample(example);
    }

    /**
     * 构键条件
     * @param spec
     * @return
     */
    public Example createExample(Spec spec){
        Example example = new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if (spec != null){
            if (!StringUtils.isEmpty(spec.getName())){
                //根据规格名字查找
                criteria.andLike("name","%"+spec.getName()+"%");
            }
        }
        return example;
    }

    @Override
    public List<Spec> findAll() {
        return specMapper.selectAll();
    }

    @Override
    public Spec findById(Integer id) {
        return specMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKeySelective(spec);
    }

    @Override
    public void add(Spec spec) {
        specMapper.insertSelective(spec);
    }

    @Override
    public void delete(Integer id) {
        specMapper.deleteByPrimaryKey(id);
    }
}
