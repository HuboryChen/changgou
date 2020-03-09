package com.changgou.search.dao;

import com.changgou.goods.pojo.Sku;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author DL_Wu
 * @Date 2020/3/9 17:06
 * @Version 1.0
 *
 * 该接口主要用于索引数据操作，主要使用它来实现将数据导入到ES索引库中
 */
public interface SkuEsMapper extends ElasticsearchRepository<Sku,Long> {

}
