package com.changgou.service;


import com.changgou.goods.pojo.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/3 11:33
 * @Version 1.0
 *
 * 商品分类
 */
public interface CategoryService {

    /**
     * 分页 + 多条件
     * @param category 条件
     * @param page 当前页
     * @param size  每页数量
     * @return
     */
    public PageInfo<Category> findPage(Category category, int page, int size);

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    public PageInfo<Category > findPage(int page, int size);

    /**
     * 多条件
     * @param category
     * @return
     */
    public List<Category> findList(Category category);

    /**
     * 查找所有
     * @return
     */
    public List<Category> findAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Category findById(Integer id);

    /**
     * 修改
     * @param category
     */
    public void update(Category category);

    /**
     * 增加
     * @param category
     */
    public void add(Category category);

    /**
     * 删除
     * @param id
     */
    public void delete(Integer id);

}
