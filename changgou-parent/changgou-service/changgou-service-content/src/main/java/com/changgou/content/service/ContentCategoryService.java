package com.changgou.content.service;


import com.changgou.content.pojo.ContentCategory;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/8 15:28
 * @Version 1.0
 */
public interface ContentCategoryService {

    /***
     * ContentCategory多条件分页查询
     * @param contentCategory
     * @param page
     * @param size
     * @return
     */
    PageInfo<ContentCategory> findPage(ContentCategory contentCategory, int page, int size);

    /***
     * ContentCategory分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<ContentCategory> findPage(int page, int size);

    /***
     * ContentCategory多条件搜索方法
     * @param contentCategory
     * @return
     */
    List<ContentCategory> findList(ContentCategory contentCategory);

    /***
     * 删除Content
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改 ContentCategory数据
     * @param contentCategory
     */
    void update(ContentCategory contentCategory);

    /***
     * 新增 ContentCategory
     * @param contentCategory
     */
    void add(ContentCategory contentCategory);

    /**
     * 根据ID查询 ContentCategory
     * @param id
     * @return
     */
    ContentCategory findById(Integer id);

    /**
     * 查询所有
     * @return
     */
    public List<ContentCategory> findAll();

}
