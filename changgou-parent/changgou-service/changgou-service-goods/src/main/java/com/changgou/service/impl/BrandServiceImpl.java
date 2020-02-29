package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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

    /**
     * 条件 + 分页查询
     * @param brand  当前品牌
     * @param page   当前页
     * @param size   每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索数据
        Example example = createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        //封装pageInfo
        return new PageInfo<Brand>(brands);
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(int page, int size) {
        //分页实现
        PageHelper.startPage(page, size);
        //查询集合
        List<Brand> brands = brandMapper.selectAll();
        //封装PageInfo -> list
        return new PageInfo<Brand>(brands);
    }

    /**
     * 多条件搜索品牌
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        //构建查询条件
        Example example = createExample(brand);
        //根据构建的条件查询数据
        return brandMapper.selectByExample(example);
    }

    /**
     * 构造查询对象
     * @param brand
     * @return
     */
    public Example createExample(Brand brand){
        //构造查询条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();  //条件构造器

        if (brand != null){
            //brand.name != null 根据名字进行模糊查询 where name like %华为%
            if (!StringUtils.isEmpty(brand.getName())){
                /**
                 * 1.Brand的属性名
                 * 2.占位符参数，搜索的条件
                 */
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            //brand.letter != null 根据首字母搜索 and letter = "H"
            if (!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter",brand.getLetter());
            }
            //根据品牌图片地址
            if (!StringUtils.isEmpty(brand.getImage())){
                criteria.andLike("image","%"+brand.getImage()+"%");
            }
            //根据品牌id
            if (!StringUtils.isEmpty(brand.getId())){
                criteria.andEqualTo("id",brand.getId());
            }
            //排序
            if (!StringUtils.isEmpty(brand.getSeq())){
                criteria.andEqualTo("seq",brand.getSeq());
            }
        }
        return example;
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> findAll() {
        List<Brand> brands = brandMapper.selectAll();
        return brands;
    }

    /**
     * 根据id查询
     * // select * from tb_brand where id = ?
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加品牌
     *  //insert into tb_brand(id,name,....) values(#{})
     *
     *      mapper.insertSelective()会忽略空值
     *      mapper.insert()不会忽略空值
     * @param brand
     * @return
     */
    @Override
    public void add(Brand brand) {
         brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }
}
