package com.changgou.controller;

import com.changgou.goods.pojo.Spec;
import com.changgou.service.SpecService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 20:21
 * @Version 1.0
 *
 * 规格管理
 */
@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {
    
    @Autowired
    private SpecService specService;

    /**
     * 条件 +  分页
     * @param spec  条件
     * @param page  当前页
     * @param size  每页条数
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Spec>> findPage(@RequestBody Spec spec,
                                           @PathVariable("page")int page,
                                           @PathVariable("size")int size){
        PageInfo<Spec> specPage = specService.findPage(spec,page, size);
        return new Result<>(true,StatusCode.OK,"分页+  条件 查询成功",specPage);
    }

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Spec>> findPage(@PathVariable("page")int page,@PathVariable("size")int size){
        PageInfo<Spec> specPage = specService.findPage(page, size);
        return new Result<>(true,StatusCode.OK,"分页查询成功",specPage);
    }

    /**
     * 条件查询
     * @param spec
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Spec>> findList(@RequestBody Spec spec){
        List<Spec> specList = specService.findList(spec);
        return new Result<>(true,StatusCode.OK,"多条件查找成功",specList);
    }

    /**
     * 删除 规格
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id){
        specService.delete(id);
        return new Result<>(true, StatusCode.OK,"删除模板成功");
    }

    /**
     * 修改 规格
     * @param spec
     * @return
     */
    @PostMapping("/{id}")
    public Result update(@PathVariable("id")Integer id,@RequestBody Spec spec){
        spec.setId(id);
        specService.update(spec);
        return new Result<>(true, StatusCode.OK,"修改模板成功");
    }

    /**
     * 增加 规格
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Spec spec){
        specService.add(spec);
        return new Result<>(true, StatusCode.OK,"增加模板成功");
    }

    /**
     * 通过id查找
     * @return
     */
    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable("id")Integer id){
        Spec spec = specService.findById(id);
        return new Result<>(true, StatusCode.OK,"查找成功",spec);
    }

    /**
     * 查找所有
     * @return
     */
    @GetMapping
    public Result<List<Spec>> findAll(){
        List<Spec> SpecList = specService.findAll();
        return new Result<>(true, StatusCode.OK,"查询所有集合成功",SpecList);
    }
    
}
