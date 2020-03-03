package com.changgou.service;

import com.changgou.goods.pojo.Para;
import com.github.pagehelper.PageInfo;

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
     * 分页 + 多条件
     * @param para 条件
     * @param page 当前页
     * @param size  每页数量
     * @return
     */
    public PageInfo<Para > findPage(Para para,int page, int size);

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    public PageInfo<Para > findPage(int page, int size);

    /**
     * 多条件
     * @param para
     * @return
     */
    public List<Para> findList(Para para);

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
