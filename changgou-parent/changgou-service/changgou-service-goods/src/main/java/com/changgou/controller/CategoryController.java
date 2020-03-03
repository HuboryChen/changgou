package com.changgou.controller;

import com.changgou.goods.pojo.Category;
import com.changgou.service.CategoryService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/3 11:36
 * @Version 1.0
 *
 * 商品分类
 */
@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 条件 + 分页
     * @param category  条件
     * @param page  当前页
     * @param size  每页条数
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Category>> findPage(@RequestBody Category category,
                                           @PathVariable("page")int page,
                                           @PathVariable("size") int size){
        PageInfo<Category> paraPage = categoryService.findPage(category,page, size);
        return new Result<>(true,StatusCode.OK,"分页+ 条件 查询成功",paraPage);
    }

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Category>> findPage(@PathVariable("page")int page,@PathVariable("size") int size){
        PageInfo<Category> categoryPage = categoryService.findPage(page, size);
        return new Result<>(true,StatusCode.OK,"分页查询成功",categoryPage);
    }

    /**
     * 条件查询
     * @param category
     * @return
     */
    @PostMapping("/search")
    public Result<List<Category>> findList(@RequestBody Category category){
        List<Category> categorys = categoryService.findList(category);
        return new Result<>(true,StatusCode.OK,"条件查询成功",categorys);
    }


    /**
     * 删除 商品分类
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return new Result<>(true, StatusCode.OK,"删除商品分类成功");
    }

    /**
     * 修改 商品分类
     * @param category
     * @return
     */
    @PostMapping("/{id}")
    public Result update(@PathVariable("id")Integer id,@RequestBody Category category){
        category.setId(id);
        categoryService.update(category);
        return new Result<>(true, StatusCode.OK,"修改商品分类成功");
    }

    /**
     * 增加 商品分类
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return new Result<>(true, StatusCode.OK,"增加商品分类成功");
    }

    /**
     * 通过id查找
     * @return
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable("id")Integer id){
        Category category = categoryService.findById(id);
        return new Result<>(true, StatusCode.OK,"查找商品分类成功",category);
    }

    /**
     * 查找所有  商品分类
     * @return
     */
    @GetMapping
    public Result<List<Category>> findAll(){
        List<Category> categoryList = categoryService.findAll();
        return new Result<>(true, StatusCode.OK,"查询所有商品分类集合成功",categoryList);
    }

}
