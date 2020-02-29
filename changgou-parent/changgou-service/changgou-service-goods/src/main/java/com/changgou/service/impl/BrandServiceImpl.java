package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/2/28 19:26
 * @Version 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
   private BrandMapper brandMapper;


    @Override
    public List<Brand> findAll() {
        List<Brand> brands = brandMapper.selectAll();
        return brands;
    }
}
