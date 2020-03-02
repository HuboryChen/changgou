package com.changgou.controller;

import com.changgou.goods.pojo.Template;
import com.changgou.service.TemplateService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 19:16
 * @Version 1.0
 */
@RestController
@RequestMapping("/template")
@CrossOrigin
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /**
     * 分页  +  条件
     * @param template  条件
     * @param page   当前页
     * @param size    每页显示条数
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Template>> findList(@RequestBody Template template,
                                               @PathVariable("page") int page,
                                               @PathVariable("size") int size){
        PageInfo<Template> templates  = templateService.findPage(template,page, size);
        return new Result<>(true, StatusCode.OK,"分页条件查询成功",templates);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Template>> findList(@PathVariable("page") int page,@PathVariable("size") int size){
        PageInfo<Template> templates = templateService.findPage(page, size);
        return new Result<>(true, StatusCode.OK,"分页查询成功",templates);
    }

    /**
     * 条件查询
     * @param template
     * @return
     */
    @PostMapping("/search")
    public Result<List<Template>> findList(@RequestBody Template template){
        List<Template> templateList = templateService.findList(template);
        return new Result<>(true, StatusCode.OK,"按条件成功",templateList);
    }

    /**
     * 删除 模板
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id){
        templateService.delete(id);
        return new Result<>(true, StatusCode.OK,"删除模板成功");
    }

    /**
     * 修改 模板
     * @param template
     * @return
     */
    @PostMapping("/{id}")
    public Result update(@PathVariable("id")Integer id,@RequestBody Template template){
        template.setId(id);
        templateService.update(template);
        return new Result<>(true, StatusCode.OK,"修改模板成功");
    }

    /**
     * 增加 模板
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Template template){
        templateService.add(template);
        return new Result<>(true, StatusCode.OK,"增加模板成功");
    }

    /**
     * 通过id查找
     * @return
     */
    @GetMapping("/{id}")
    public Result<Template> findById(@PathVariable("id")Integer id){
        Template template = templateService.findById(id);
        return new Result<>(true, StatusCode.OK,"查找成功",template);
    }

    /**
     * 查找所有
     * @return
     */
    @GetMapping
    public Result<List<Template>> findAll(){
        List<Template> templateList = templateService.findAll();
        return new Result<>(true, StatusCode.OK,"查询所有集合成功",templateList);
    }

}
