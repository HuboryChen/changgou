package com.changgou.service;

import com.changgou.goods.pojo.Album;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 16:21
 * @Version 1.0
 *
 * 相册
 */
public interface AlbumService {


    /**
     * 分页   +  多条件 查询
     * @param album  当前条件
     * @param page  当前页
     * @param size  每页显示的条数
     * @return
     */
    public PageInfo<Album> findPage(Album album, int page, int size);

    /**
     * 分页查询
     * @param page  当前页
     * @param size  每页显示的条数
     * @return
     */
    public PageInfo<Album> findPage(int page, int size);

    /**
     * 多条件 搜索
     * @param album
     * @return
     */
    public List<Album> findList(Album album);

    /**
     * 查找所有
     * @return
     */
    public List<Album> findAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Album findById(Long id);

    /**
     * 修改
     * @param album
     */
    public void update(Album album);

    /**
     * 增加
     * @param album
     */
    public void add(Album album);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);

}
