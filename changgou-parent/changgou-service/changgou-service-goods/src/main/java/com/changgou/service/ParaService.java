package com.changgou.service;

import com.changgou.goods.pojo.Para;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 20:23
 * @Version 1.0
 *
 * 参数管理
 */
public interface ParaService {

    /**
     * 查找所有
     * @return
     */
    public List<Para> findAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Para findById(Integer id);

    /**
     * 修改
     * @param para
     */
    public void update(Para para);

    /**
     * 增加
     * @param para
     */
    public void add(Para para);

    /**
     * 删除
     * @param id
     */
    public void delete(Integer id);
}
