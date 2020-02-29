package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/2/28 19:27
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/brand")
@CrossOrigin   //开启跨域
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 条件  +  分页查询实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand,                 //当前条件
                                            @PathVariable(value = "page")int page,    //当前页
                                            @PathVariable(value = "size") int size){  //每页显示条数
        PageInfo<Brand> pageList = brandService.findPage(brand,page, size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"条件 + 分页查询",pageList);
    }

    /**
     * 分页查询实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page")int page,    //当前页
                                    @PathVariable(value = "size") int size){  //每页显示条数
        PageInfo<Brand> pageList = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询",pageList);
    }

    /**
     * 条件搜索
     * @param brand
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand){
        List<Brand> brands = brandService.findList(brand);
        return new Result<List<Brand>>(true,StatusCode.OK,"多条件搜索查询",brands);
    }

    /**
     * 查询所有品牌
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> brands = brandService.findAll();
        return  new Result<List<Brand>>(true, StatusCode.OK,"查询品牌集合成功",brands);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(value = "id")Integer id){
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true,StatusCode.OK,"根据id查询",brand);
    }

    /**
     * 增加品牌
     * @param brand
     */
    public Result addBrand(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true, StatusCode.OK,"增加品牌");
    }

    /**
     * 品牌修改实现
     * @param id
     * @param brand
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result updateBrand(@PathVariable(value = "id")Integer id,@RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"品牌修改成功！");
    }

    /**
     * 品牌删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteBrand(@PathVariable(value = "id")Integer id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"品牌删除成功！！！");
    }

}
