package com.example.newbie.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // Entity는 DB의 자료를 구성하는 레코드를 의미.
public class Article { // Article이라는 공개 클래스 생성


    @Id // primary key 역할을 부여
    @GeneratedValue // primary key의 자동 생성을 해주는 어노테이션
    private Long id; // id는 롱 값으로 비공개 객체

    @Column // 객체 필드와 DB 테이블 컬럼을 맵핑
    private String title; // title은 스트링값으로 비공개 객체

    @Column  // 객체 필드와 DB 테이블 컬럼을 맵핑
    private String content; // content은 스트링값으로 비공개 객체

    public Article(Long id, String title, String content){ // id, title, content를 파라미터로 가지는 Article이라는 생성자
        this.id = id; // this.id는 필드, id는 파라미터
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() { // 객체를 문자열 형태로 바꾸어 출력
        return "Article{" +
                "id=" + id + // @GeneratedValue로 글 쓸 때마다 아이디값이 한 개씩 증가
                ", title='" + title + '\'' + // 입력받은 제목을 출력
                ", content='" + content + '\'' + // 입력받은 내용을 출력
                '}';
    }
}