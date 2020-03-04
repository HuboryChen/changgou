package com.changgou.goods.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/4 11:27
 * @Version 1.0
 *
 * 商品信息组合对象
 *  spu
 *  List<sku>
 */
@Getter
@Setter
public class Goods implements Serializable {

    private Spu spu;
    private List<Sku> skuList;

}
