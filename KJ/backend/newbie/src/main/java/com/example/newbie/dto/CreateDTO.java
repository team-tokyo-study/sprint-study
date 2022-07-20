package com.example.newbie.dto;

import com.example.newbie.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 변수를 사용하는 생성자를 자동완성 시켜주는 어노테이션
@ToString // 클래스의 변수들을 기반으로 ToString 메소드를 자동으로 완성시켜 주는 어노테이션

public class CreateDTO { // CreateDTO라는 클래스

    private String title; // title 필드
    private String content; // content 필드

    /* @AllArgsConstructor를 통해 아래 같은 클래스 생성코드의 생략이 가능해짐
    public CreateDTO(String title, String content) { //String title과 String content를 파라미터로 가지는 CreateDTO 메소드(동작)
    this.title = title; // this.title는 필드, title는 파라미터
    this.content = content; // // this.content는 필드, content는 파라미터
     */

     /* @ToString를 통해 @Override로 toString메소드를 재정의 해주는 부분의 생략이 가능해짐
     @Override
     public String toString() { // toString은 객체가 가진 모든 정보를 반환해야 함
        return "CreateDTO{" + // 여기선 Create DTO를 리턴 하기 때문에
                "title='" + title + '\'' + // title과
                ", content='" + content + '\'' + // content를 모두 반환해야 함
                '}';
     }
     */

    public Article toEntity() {return new Article(null, title, content);} //id로 null값, String title과 String content를 파라미터로 가짐 (동작)

}
