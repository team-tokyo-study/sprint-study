package com.example.newbie.api;

import com.example.newbie.dto.CreateDTO;
import com.example.newbie.entity.Article;
import com.example.newbie.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ApiController {

    @Autowired
    private ArticleRepository articleRepository;

    // GET READ
    @GetMapping ("/api/articles")
    public List<Article> index() {

        return articleRepository.findAll();
    }

    // GET READ from ID
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {

        return articleRepository.findById(id).orElse(null);
    }

    // CREATE
    @PostMapping("/api/articles")
    public Article create(@RequestBody CreateDTO dto) {
        log.info(dto.toString());
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // UPDATE (PATCH)
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody CreateDTO dto) {

        Article article = dto.toEntity();
        // 1. DTO를 엔티티화
        log.info(article.toString(), id);
        log.info(id.toString());

        Article target = articleRepository.findById(id).orElse(null);
        //2. 타겟조회
        log.info(target.toString());

        if(target == null || id != article.getId()) { // 3. 잘못된 요청처리
            log.info("Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 정상처리
        target.patch(article);

        Article updated  = articleRepository.save(target);

            return ResponseEntity.status(HttpStatus.OK).body(updated);

    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리
        if (target == null) {
            log.info("Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. 정상처리
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}