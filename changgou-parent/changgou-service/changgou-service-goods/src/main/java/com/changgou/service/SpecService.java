package com.changgou.service;

import com.changgou.goods.pojo.Spec;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 20:18
 * @Version 1.0
 *
 * 规格管理
 */
public interface SpecService {

    /**
     * 查找所有
     * @return
     */
    public List<Spec> findAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Spec findById(Integer id);

    /**
     * 修改
     * @param spec
     */
    public void update(Spec spec);

    /**
     * 增加
     * @param spec
     */
    public void add(Spec spec);

    /**
     * 删除
     * @param id
     */
    public void delete(Integer id);

}
