package com.changgou.canal;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.changgou.content.feign.ContentFeign;
import com.changgou.content.pojo.Content;
import com.xpand.starter.canal.annotation.*;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @Author DL_Wu
 * @Date 2020/3/8 8:54
 * @Version 1.0
 *
 *  实现mysql数据监听
 */
@CanalEventListener
public class CanalDataEventListener {

    @Autowired
    private ContentFeign contentFeign;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 自定义数据库的 操作来监听
     * destination = "example"
     * @param eventType 当前操作的类型  增加数据
     * @param rowData  发生变更的一行数据
     */
    @ListenPoint(destination = "example",
            schema = "changgou_content",
            table = {"tb_content", "tb_content_category"},
            eventType = {
                    CanalEntry.EventType.UPDATE,
                    CanalEntry.EventType.DELETE,
                    CanalEntry.EventType.INSERT})
    public void onEventCustomUpdate(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        //1.获取列名 为category_id的值
        String categoryId= getColumnValue(eventType, rowData);
        //2.调用feign 获取该分类下的所有的广告集合
        Result<List<Content>> categoryresut = contentFeign.findByCategory(Integer.valueOf(categoryId));
        List<Content> data = categoryresut.getData();
        //3.使用redisTemplate存储到redis中
        stringRedisTemplate.boundValueOps("content_"+categoryId).set(JSON.toJSONString(data));
    }

    /**
     * 监听数据库的变化
     * @param eventType 当前操作的类型  增加数据
     * @param rowData  发生变更的一行数据
     * @return
     */
    private String getColumnValue(CanalEntry.EventType eventType ,CanalEntry.RowData rowData){
        String categoryId = "";
        //判断 如果是删除  则获取beforlist
        if (eventType == CanalEntry.EventType.DELETE){
            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
                if (column.getName().equalsIgnoreCase("category_id")){
                    return column.getValue();
                }
            }
        }else {
            //判断 如果是添加 或者是更新 获取afterlist
            for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
                if (column.getName().equalsIgnoreCase("category_id")){
                    return column.getValue();
                }
            }
        }
        return categoryId;
    }



    /*    *//**
     * 增加数据监听
     * @InsertListenPoint 只增加后的数据
     *      rowData.getAfterColumnsList() 增加， 修改
     *      rowData.getBeforeColumnsList() 删除，修改
     * @param entryType 当前操作的类型  增加数据
     * @param rowData  发生变更的一行数据
     *//*
    @InsertListenPoint
    public void onEventInsert(CanalEntry.EntryType entryType,CanalEntry.RowData rowData){
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("列名"+ column.getName()+"---- 增加的数据" +column.getValue());
        }
    }

    *//**
     * @UpdateListenPoint 修改数据监听
     *      rowData.getAfterColumnsList() 增加， 修改
     *      rowData.getBeforeColumnsList() 删除，修改
     * @param entryType 当前操作的类型  增加数据
     * @param rowData  发生变更的一行数据
     *//*
    @UpdateListenPoint
    public void onEventUpdate(CanalEntry.EntryType entryType,CanalEntry.RowData rowData){
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            System.out.println("修改前列名"+ column.getName()+"---- 修改前的数据" +column.getValue());
        }

        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("修改后列名"+ column.getName()+"---- 修改后的数据" +column.getValue());
        }
    }

    *//**
     * @DeleteListenPoint 删除数据监听
     *      rowData.getAfterColumnsList() 增加， 修改
     *      rowData.getBeforeColumnsList() 删除，修改
     * @param entryType 当前操作的类型  增加数据
     * @param rowData  发生变更的一行数据
     *//*
    @DeleteListenPoint
    public void onEventDelete(CanalEntry.EntryType entryType,CanalEntry.RowData rowData){
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            System.out.println("删除前列名"+ column.getName()+"---- 删除前的数据" +column.getValue());
        }
    }*/


}
