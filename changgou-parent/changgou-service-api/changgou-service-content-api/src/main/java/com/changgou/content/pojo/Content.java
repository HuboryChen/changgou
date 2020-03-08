package com.changgou.content.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author DL_Wu
 * @Date 2020/3/8 15:00
 * @Version 1.0
 *
 * 广告
 */
@Table(name = "tb_content")
@Getter
@Setter
public class Content implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;  //内容类目ID

    @Column(name = "title")
    private String title;  //内容标题

    @Column(name = "url")
    private String url;  //链接

    @Column(name = "pic")
    private String pic;  //图片绝对路径

    @Column(name = "status")
    private String status; //状态,0无效，1有效

    @Column(name = "sort_order")
    private Integer sortOrder;  //排序


}
