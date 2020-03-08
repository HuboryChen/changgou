package com.changgou.content.feign;

import com.changgou.content.pojo.Content;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/8 17:08
 * @Version 1.0
 */
@FeignClient(name = "content")
@RequestMapping(value = "/content")
public interface ContentFeign {

    /**
     * 根据分类ID查询所有广告
     * @param id  Content中的 categoryId
     * @return
     */
    @GetMapping(value = "/list/category/{id}")
    Result<List<Content>> findByCategory(@PathVariable("id") Integer id);

}
