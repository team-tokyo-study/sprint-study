package com.example.newbie.repository;

import com.example.newbie.entity.Article;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList; // ArrayList 임포트

public interface ArticleRepository extends CrudRepository <Article, Long> { // CrudRepository를 상속받는 ArticleRepository인터페이스. 제너럴타입
    @Override
    ArrayList<Article> findAll(); // Article타입의 ArrayList 선언(ArrayList는 동적배열)이고, findAll()이 ArrayList 형태로 반환

}

/*
insert 작업 : save(엔티티 객체)
select 작업 : findById(키 타입), getOne(키 타입)
update 작업 : save(엔티티 객체)
delete 작업 : deleteById(키 타입), delete(엔티티 객체)
 */