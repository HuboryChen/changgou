package com.changgou.service.impl;

import com.changgou.dao.CategoryMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/3 11:33
 * @Version 1.0
 *
 * 商品分类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分页 + 多条件
     * @param category 条件
     * @param page 当前页
     * @param size  每页数量
     * @return
     */
    @Override
    public PageInfo<Category> findPage(Category category, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(category);
        List<Category> categories = categoryMapper.selectByExample(example);
        return new PageInfo<>(categories);
    }

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Category> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Category> categories = categoryMapper.selectAll();
        return new PageInfo<>(categories);
    }

    /**
     * 条件查询
     * @param category
     * @return
     */
    @Override
    public List<Category> findList(Category category) {
        Example example = createExample(category);
        List<Category> categories = categoryMapper.selectByExample(example);
        return categories;
    }

    /**
     * 构键条件
     * @param category
     * @return
     */
    public Example createExample(Category category){
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if (category != null){
            if (!StringUtils.isEmpty(category.getName())){
                //根据参数名字查找
                criteria.andLike("name","%"+category.getName()+"%");
            }
        }
        return example;
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insertSelective(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
