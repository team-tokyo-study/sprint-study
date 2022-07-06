package com.example.newbie.dto;

import com.example.newbie.entity.Article;

public class CreateDTO { // CreateDTO라는 클래스

    private String title; // title 필드
    private String content; // content 필드

    public CreateDTO(String title, String content) { //String title과 String content를 파라미터로 가지는 CreateDTO 메소드(동작)
        this.title = title; // this.title는 필드, title는 파라미터
        this.content = content; // // this.content는 필드, content는 파라미터
    }

    @Override
    public String toString() { // toString은 객체가 가진 모든 정보를 반환해야 함
        return "CreateDTO{" + // 여기선 Create DTO를 리턴 하기 때문에
                "title='" + title + '\'' + // title과
                ", content='" + content + '\'' + // content를 모두 반환해야 함
                '}';
    }

    public Article toEntity() {return new Article(null, title, content);} //id로 null값, String title과 String content를 파라미터로 가짐 (동작)

}
