package com.changgou.controller;

import com.changgou.goods.pojo.Spec;
import com.changgou.service.SpecService;
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
