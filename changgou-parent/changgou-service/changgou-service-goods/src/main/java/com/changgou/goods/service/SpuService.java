package com.changgou.goods.service;
import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author: DL_Wu
 * @Description:Spu业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SpuService {



    /**
     * 还原被删除的商品
     * @param spuId
     */
    void restoreSpu(Long spuId);

    /**
     * 逻辑删除
     * @param spuId
     */
    void logicDeleteSpu(Long spuId);

    /**
     * 批量 下架
     * @param ids  要下架的所有Id（spuId）
     * @return
     */
    int pullMany(Long [] ids);

    /**
     * 批量上架
     * @param ids  要上架的所有Id（spuId）
     * @return
     */
    int putMany(Long [] ids);

    /**
     * 商品上架
     * @param spuId
     */
    void put(Long spuId);

    /**
     * 商品的下架
     * @param spuId
     */
    void pull(Long spuId);


    /**
     * 商品的审核
     * @param spuId
     */
    void auditSpu(Long spuId);

    /**
     * 根据id 查询GoodS的数据
     * @param spuId spu 的ID
     * @return
     */
    Goods findGoodsById(Long spuId);

    /**
     * 添加商品(SPU+ SKUlIST)
     * @param goods   update  add
     */
    void saveGoods(Goods goods);

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
     Spu findById(Long id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();

}
