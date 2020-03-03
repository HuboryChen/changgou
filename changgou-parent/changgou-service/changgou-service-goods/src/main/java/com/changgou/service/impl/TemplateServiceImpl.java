package com.changgou.service.impl;

import com.changgou.dao.TemplateMapper;
import com.changgou.goods.pojo.Template;
import com.changgou.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 19:16
 * @Version 1.0
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    /**
     *  分页  +   条件
     * @param template   条件
     * @param page  当前页
     * @param size  每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Template> findPage(Template template, int page, int size) {
        //实现分页
        PageHelper.startPage(page,size);
        //查询集合
        Example example = createExample(template);
        List<Template> templates = templateMapper.selectByExample(example);
        //封装数据
        return new PageInfo<Template>(templates);
    }

    /**
     * 分页
     * @param page  当前页
     * @param size  每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Template> findPage(int page, int size) {
        //实现分页
        PageHelper.startPage(page,size);
        //查询集合
        List<Template> templates = templateMapper.selectAll();
        //封装数据
        return new PageInfo<Template>(templates);
    }

    /**
     * 多条件查询
     * @param template
     * @return
     */
    @Override
    public List<Template> findList(Template template) {
        //构造查询条件
        Example example = createExample(template);
        //根据构建的条件查询数据
        return templateMapper.selectByExample(example);
    }

    /**
     * 构造多条件
     * @param template
     * @return
     */
    public Example createExample(Template template){
        Example example  = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if (template != null){
            //根据模板名字模糊查找   where name like "电视"
            if (!StringUtils.isEmpty(template.getName())){
                criteria.andLike("name", "%"+template.getName()+"%");
            }
        }
        return example;
    }

    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }

    @Override
    public Template findById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKeySelective(template);
    }

    @Override
    public void add(Template template) {
        templateMapper.insertSelective(template);
    }

    @Override
    public void delete(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }
}
