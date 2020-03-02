package com.changgou.service.impl;

import com.changgou.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 16:21
 * @Version 1.0
 */
@Service
public class AlbumServiceImpl  implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 分页   +  多条件
     * @param album
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Album> findPage(Album album, int page, int size) {
        //分页实现
        PageHelper.startPage(page,size);
        //构造查询条件
        Example example = createExample(album);
        List<Album> albumList = albumMapper.selectByExample(example);
        return new PageInfo<>(albumList);
    }

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Album> findPage(int page, int size) {
        //分页实现
        PageHelper.startPage(page,size);
        //查询集合
        List<Album> albums = albumMapper.selectAll();
        //封装PageInfo -> list
        return new PageInfo<Album>(albums);
    }

    /**
     * 多条件查询
     * @param album
     * @return
     */
    @Override
    public List<Album> findList(Album album) {
        //构造查询条件
        Example example = createExample(album);
        //根据构建的条件查询数据
        return albumMapper.selectByExample(example);
    }

    /**
     * 构造 Album 的查询对象
     * @param album
     * @return
     */
    public Example createExample(Album album){
        //构造查询条件
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();   //条件构造器
        if (album != null){
            //根据相册标题查找
            if (!StringUtils.isEmpty(album.getTitle())){
                //where title like "华"
                criteria.andLike("title","%"+album.getTitle()+"%");
            }
            //根据相册封面查找
            if (!StringUtils.isEmpty(album.getImage())){
                //where image =  "1.jpg"
                criteria.andEqualTo("image",album.getImage());
            }
        }
        return example;
    }

    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    @Override
    public void add(Album album) {
        albumMapper.insertSelective(album);
    }

    @Override
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }
}
