package com.example.newbie.repository;

import com.example.newbie.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository <Article, Long> { // CrudRepository를 상속받는 ArticleRepository인터페이스. <Article, Long>은 무슨 뜻?


}
