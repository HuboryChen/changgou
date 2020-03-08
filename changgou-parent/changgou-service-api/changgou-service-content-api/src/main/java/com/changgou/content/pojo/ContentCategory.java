package com.changgou.content.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author DL_Wu
 * @Date 2020/3/8 15:22
 * @Version 1.0
 */
@Table(name = "tb_content_category")
@Getter
@Setter
public class ContentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;  //类目ID

    @Column(name = "name")
    private String name;  //分类名称

}
