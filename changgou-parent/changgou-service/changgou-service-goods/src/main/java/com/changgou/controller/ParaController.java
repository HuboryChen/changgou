package com.changgou.controller;

import com.changgou.goods.pojo.Para;
import com.changgou.service.ParaService;
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
 * 参数管理
 */
@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {

    @Autowired
    private ParaService paraService;

    /**
     * 删除 参数
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id){
        paraService.delete(id);
        return new Result<>(true, StatusCode.OK,"删除模板成功");
    }

    /**
     * 修改 参数
     * @param para
     * @return
     */
    @PostMapping("/{id}")
    public Result update(@PathVariable("id")Integer id,@RequestBody Para para){
        para.setId(id);
        paraService.update(para);
        return new Result<>(true, StatusCode.OK,"修改模板成功");
    }

    /**
     * 增加 参数
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Para para){
        paraService.add(para);
        return new Result<>(true, StatusCode.OK,"增加模板成功");
    }

    /**
     * 通过id查找
     * @return
     */
    @GetMapping("/{id}")
    public Result<Para> findById(@PathVariable("id")Integer id){
        Para para = paraService.findById(id);
        return new Result<>(true, StatusCode.OK,"查找成功",para);
    }

    /**
     * 查找所有
     * @return
     */
    @GetMapping
    public Result<List<Para>> findAll(){
        List<Para> ParaList = paraService.findAll();
        return new Result<>(true, StatusCode.OK,"查询所有集合成功",ParaList);
    }

}
