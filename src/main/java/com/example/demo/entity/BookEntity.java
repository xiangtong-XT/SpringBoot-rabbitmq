package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class BookEntity {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String _id;
   //标题
    private String title;

    //作者
    private String author;

    //简介
    private String shortIntro;

    //封面图
    private String cover;

    //
    private String site;

    //分类
    private String majorCate;

    //大类
    private String minorCate;

    //
    private String sizetype;

    //
    private String superscript;

    //
    private String contentType;

    //
    private String allowMonthly;

    //
    private String banned;

    //追书人数
    private String latelyFollower;

    //留存率
    private String retentionRatio;

    //章节
    private String lastChapter;
}
