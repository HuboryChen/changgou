package com.changgou.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author DL_Wu
 * @Date 2020/3/9 16:41
 * @Version 1.0
 */
@FeignClient("goods") // 调用goods微服务
@RequestMapping("/sku")
public interface SkuFeign {



}
