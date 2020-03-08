package com.changgou.content.service;

import com.changgou.content.pojo.Content;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/8 15:27
 * @Version 1.0
 *
 * 广告
 */
public interface ContentService {

    /***
     * 根据categoryId查询广告集合
     * @param categoryId
     * @return
     */
    List<Content> findByCategory(Integer categoryId);

    /***
     * Content多条件分页查询
     * @param content
     * @param page
     * @param size
     * @return
     */
    PageInfo<Content> findPage(Content content, int page, int size);

    /***
     * Content分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Content> findPage(int page, int size);

    /***
     * Content多条件搜索方法
     * @param content
     * @return
     */
    List<Content> findList(Content content);

    /***
     * 删除Content
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改 content数据
     * @param content
     */
    void update(Content content);

    /***
     * 新增 Content
     * @param content
     */
    void add(Content content);

    /**
     * 根据ID查询 Content
     * @param id
     * @return
     */
    Content findById(Integer id);

    /**
     * 查询所有
     * @return
     */
    public List<Content> findAll();

}
