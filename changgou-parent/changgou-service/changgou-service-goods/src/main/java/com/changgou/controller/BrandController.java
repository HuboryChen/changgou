package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 查询所有品牌
     * @return
     */
    @RequestMapping("/findAll")
    public Result<List<Brand>> findAll(){
        List<Brand> brands = brandService.findAll();
        return  new Result<List<Brand>>(true, StatusCode.OK,"查询品牌集合成功",brands);
    }

}
