package com.changgou.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/2/28 19:26
 * @Version 1.0
 */
public interface BrandService {

    /**
     * 条件 + 分页查询
     * @param brand  当前品牌（搜索条件）
     * @param page   当前页
     * @param size   每页显示的条数
     * @return
     */
    PageInfo<Brand> findPage(Brand brand ,int page, int size);

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每页显示的条数
     * @return
     */
    PageInfo<Brand> findPage(int page, int size);

    /**
     * 多条件搜索品牌
     * @return
     */
    public List<Brand> findList(Brand brand);

    /**
     * 查询所有
     * @return
     */
    public List<Brand> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Brand findById(Integer id);

    /**
     * 增加品牌
     * @param brand
     */
    public void add(Brand brand);

    /**
     * 修改品牌
     * @param brand
     */
    public void update(Brand brand);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);


}
