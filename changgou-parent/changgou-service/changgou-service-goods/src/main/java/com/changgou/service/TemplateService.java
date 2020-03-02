package com.changgou.service;

import com.changgou.goods.pojo.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/2 19:15
 * @Version 1.0
 *
 * 模板管理
 */
public interface TemplateService {


    /**
     * 条件   +   分页
     * @param template   条件
     * @param page  当前页
     * @param size  每页显示的条数
     * @return
     */
    public PageInfo<Template> findPage(Template template,int page, int size);

    /**
     * 分页查询
     * @param page  当前页
     * @param size  每页显示的条数
     * @return
     */
    public PageInfo<Template> findPage(int page, int size);

    /**
     * 多条件查询
     * @param template
     * @return
     */
    public List<Template> findList(Template template);

    /**
     * 查找所有
     * @return
     */
    public List<Template> findAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Template findById(Integer id);

    /**
     * 修改
     * @param template
     */
    public void update(Template template);

    /**
     * 增加
     * @param template
     */
    public void add(Template template);

    /**
     * 删除
     * @param id
     */
    public void delete(Integer id);

}
