package com.changgou.controller;

import com.changgou.goods.pojo.Album;
import com.changgou.service.AlbumService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 16:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/album")
@CrossOrigin    //跨域
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /**
     * 分页  +  多条件
     * @param album
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Album>> findPage(@RequestBody Album album,
                                            @PathVariable("page")int page,
                                            @PathVariable("size") int size){
        PageInfo<Album> albumList = albumService.findPage(album,page, size);
        return new Result<>(true,StatusCode.OK,"分页+ 条件查询成功！",albumList);
    }


    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Album>> findPage(@PathVariable("page")int page,
                                            @PathVariable("size") int size){
        PageInfo<Album> albumList = albumService.findPage(page, size);
        return new Result<>(true,StatusCode.OK,"分页查询成功！",albumList);
    }

    /**
     * 多条件查询
     * @param album
     * @return
     */
    @PostMapping("/search")
    public Result<List<Album>> findList(@RequestBody(required = false) Album album){
        List<Album> albumList = albumService.findList(album);
        return  new Result<List<Album>>(true,StatusCode.OK,"搜索成功",albumList);
    }

    /**
     * 修改 Album 数据
     * @param album
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    public Result update(@RequestBody Album album, @PathVariable("id") Long id) {
        //设置主键
        album.setId(id);
        //修改数据
        albumService.update(album);
        return new Result(true, StatusCode.OK, "修改成功！！！");
    }

    /**
     * 删除 Album
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id){
        albumService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功！！！");
    }

    /**
     * 增加相册
     * @param album
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Album album){
        albumService.add(album);
        return new Result(true,StatusCode.OK,"增加商品成功");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable("id")Long id){
        Album album = albumService.findById(id);
        return new Result<Album>(true,StatusCode.OK,"根据id查询成功",album);
    }

    /**
     * 查找所有相册集合
     * @return
     */
    @GetMapping
    public Result<List<Album>> findAll(){
        List<Album> albums = albumService.findAll();
        return new Result<List<Album>>(true, StatusCode.OK,"查找所有相册成功",albums);
    }

}
