package com.example.newbie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity // Entity는 DB의 자료를 구성하는 레코드를 의미.
@AllArgsConstructor // 모든 변수를 사용하는 생성자를 자동완성 시켜주는 어노테이션
@ToString // 클래스의 변수들을 기반으로 ToString 메소드를 자동으로 완성시켜 주는 어노테이션
@NoArgsConstructor // 어떤 필드도 존재하지 않는 기본 생성자를 자동생성해주는 어노테이션
@Getter

public class Article { // Article이라는 공개 클래스 생성


    @Id // primary key 역할을 부여
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key의 자동 생성을 해주는 어노테이션
    private Long id; // id는 롱 값으로 비공개 객체

    @Column // 객체 필드와 DB 테이블 컬럼을 맵핑
    private String title; // title은 스트링값으로 비공개 객체

    @Column  // 객체 필드와 DB 테이블 컬럼을 맵핑
    private String content; // content은 스트링값으로 비공개 객체

    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.title;
        }
        if(article.content != null) {
            this.content = article.content;
        }
    }

    /* @AllArgsConstructor를 통해 아래 같은 클래스 생성코드의 생략이 가능해짐
    public Article(Long id, String title, String content){ // id, title, content를 파라미터로 가지는 Article이라는 생성자
    this.id = id; // this.id는 필드, id는 파라미터
    this.title = title;
    this.content = content;
    }
    */

    /* @ToString를 통해 @Override로 toString메소드를 재정의 해주는 부분의 생략이 가능해짐
    @Override
    public String toString() { // 객체를 문자열 형태로 바꾸어 출력
        return "Article{" +
                "id=" + id + // @GeneratedValue로 글 쓸 때마다 아이디값이 한 개씩 증가
                ", title='" + title + '\'' + // 입력받은 제목을 출력
                ", content='" + content + '\'' + // 입력받은 내용을 출력
                '}';
    }
    */

}