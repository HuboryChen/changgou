package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuEsService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author DL_Wu
 * @Date 2020/3/9 17:09
 * @Version 1.0
 */
@Service
public class SkuEsServiceImpl implements SkuEsService {

    @Autowired
    private SkuEsMapper skuEsMapper;

    @Autowired
    private SkuFeign skuFeign;

    /**
     * 导入SKu数据
     */
    @Override
    public void importSku() {
        //调用changgou-service-goods微服务
        Result<List<Sku>> skuListResult = skuFeign.findByStatus("1");
        //将数据转成search.Sku
        List<SkuInfo> skuInfos = JSON.parseArray(JSON.toJSONString(skuListResult.getData()), SkuInfo.class);
        for (SkuInfo skuInfo : skuInfos) {
            Map<String,Object> specMap = JSON.parseObject(skuInfo.getSpec());
            skuInfo.setSpecMap(specMap);
        }
    }
}
