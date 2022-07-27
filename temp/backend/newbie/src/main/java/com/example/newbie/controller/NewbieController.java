package com.example.newbie.controller;

import com.example.newbie.dto.CreateDTO;
import com.example.newbie.entity.Article;
import com.example.newbie.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller // 아래의 클래스를 컨트롤러로 지정하겠다고 선언하는 어노테이션
@Slf4j // lombok의 어노테이션 중 하나로, log를 쉽게 찍어줄 수 있게 한다
public class NewbieController { // NewbieController라는 어디서나 접근 가능한 퍼블릭 클래스 생성

    @Autowired // 의존성이 주입
    private ArticleRepository articleRepository; // Crud를 상속받은 ArticleRepository 인터페이스의 의존성이 주입되어, Crud가 가능하게 됨

    @GetMapping("/kj") // Get요청을 처리하는 메소드를 /kj에 맵핑
    public String hello(Model model) { // Model은 Controller 에서 생성된 데이터를 담아 View 로 전달할 때 사용하는 객체
        model.addAttribute("name", "KyungJoon"); // KyungJoon이라는 값을 가지는 name이라는 변수명. /template/kj.mustache에서 name으로 표시
        return "kj"; // view에 넘기기
    }

    @GetMapping("/mypage") // Get요청을 처리하는 메소드를 /mypage에 맵핑. 보여질때는 get방식이지만 글쓰기가 실행되면 post방식으로 넘어감
    public String mypage(Model model) { // 모델 객체를 가져오는 mypage라는 메소드 선언
        model.addAttribute("name", "mypage model");
        // addAttribute("변수명", "값") 메소드를 사용하여 mypage model에 담긴 데이터를 "name"이란 변수명으로 지정 후 모델에 등록

        return "mypage"; // view에 넘기기
    }

    @PostMapping("/mypage/create")

    public String createArticle(CreateDTO form) { // form을 CreateDTO에 담아 가져오는 createArticle 메소드 선언
        log.info(form.toString()); // CreateDTO의 toString() 메소드의 오버라이드 된 리턴값을 출력
        // @Slf4j를 사용함으로써 System.out.println에서 log.info로 변경
        Article article = form.toEntity();
        // 1. CreateDTO에서 설정된 toEntity()의 값을 가지는 form을 articleEntity에 전달 및 저장
        log.info(article.toString()); // Article의 toString() 메소드의 오버라이드 된 리턴값을 출력
        Article saved = articleRepository.save(article); // save()는 Entity 객체를 DB에 저장하는 메소드. save() 메소드는 Spring Data 에서 정의한 CrudRepository 인터페이스의 메소드임
        // 2. Repository에게 Entity를 DB로 저장하게 함
        log.info(saved.toString()); // Article의 toString() 메소드의 오버라이드 된 리턴값을 출력해 주므로, id값이 글쓸때마다 1씩 증가하게 출력됨
        log.info("ID is "+saved.getId());

        return "redirect:/article/" + saved.getId();
    }

    @GetMapping("/article/{id}") // /article/{id}를 GetMapping해주겠다
    public String detail(@PathVariable Long id, Model model) { // 모델 객체를 Long id값에 담아 가져오는 detail이라는 메소드 선언
        log.info("ID is " + id); // 로그 찍어줌

        Article articleEntity = articleRepository.findById(id).orElse(null);
            // 1. articleRepository에서 해당 id를 읽어와 Article 타입의 articleEntity라는 객체에 전달하여 저장. id가 존재하지 않을 경우 null

        model.addAttribute("article", articleEntity);
            // 2. addAttribute("변수명", "값") 메소드를 사용하여 articleEntity로 전달된 데이터를 "article"이란 변수명으로 지정 후 모델에 등록

        return "article/detail";
        // 3. 뷰에서 보여줄 페이지를 설정 (detail.mustache에 작성된 내용을 보여줌)
    }

    @GetMapping("/article") // /article을 GetMapping
    public String index (Model model){ // 모델 객체를 가져오는 index라는 메소드 선언

        List<Article> articleList = articleRepository.findAll();
        // 1. articleRepository에서 모든 값(article 목록)을 가져와 List<Article>타입의 articleList 객체에 저장
        log.info ("article list is", articleList); // 로그 찍음

        model.addAttribute("articleList", articleList);
        // 2. addAttribute("변수명", "값") 메소드를 사용하여 articleList에 담긴 데이터를 "articleList"이란 변수명으로 지정 후 모델에 등록

        return "article/index";
        // 3. 뷰에서 보여줄 페이지 설정 (index.mustache에 작성된 내용을 보여줌)
    }


    @GetMapping("/article/{id}/edit") // /article/{id}/edit를 GetMapping
    public String edit(@PathVariable Long id, Model model) { // 모델 객체를 가져오는 edit라는 메소드 선언
        log.info("ID is " + id); // 로그 찍어줌

        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 1. articleRepository에서 해당 id를 읽어와 Article 타입의 articleEntity라는 객체에 전달하여 저장. id가 존재하지 않을 경우 null

        model.addAttribute("article", articleEntity);
        // 2. addAttribute("변수명", "값") 메소드를 사용하여 articleEntity로 전달된 데이터를 "article"이란 변수명으로 지정 후 모델에 등록

        return "article/edit";
        // 3. 뷰에서 보여줄 페이지 설정 (edit.mustache에 작성된 내용을 보여줌)
    }


    @PostMapping("/article/update")
    public String update(CreateDTO form) { // form을 CreateDTO에 담아 가져오는 update 메소드 선언
        log.info(form.toString()); // 로그 찍어줌
        // @Slf4j를 사용함으로써 System.out.println에서 log.info로 변경
        Article articleEntity = form.toEntity();
        // 1. CreateDTO에서 설정된 toEntity()의 값을 가지는 form을 articleEntity에 전달 및 저장
        log.info(articleEntity.toString()); // articleEntity에 담긴 값이 출력

        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2. articleRepository가 DB에서 id값을 검색 후 해당 데이터를 받아와 엔티티에 전달 및 저장
        if (target != null) {// 검색한 id가 엔티티에 저장되어 있으면 (즉, 검색한 id로 읽어온 데이터가 있다면)
            articleRepository.save(articleEntity); // 엔티티가 저장되어 레포지토리로 전달, DB가 갱신
        } 

        return "redirect:/article/" + articleEntity.getId();
        // 수정결과 페이지로 리다이렉트
    }


    @GetMapping("/article/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) { // id를 가져오는 delete라는 메소드 선언. RedirectAttributes는 일회성으로 데이터를 전달하는 용도로 쓰이는 클래스
        log.info("삭제 요청이 들어왔습니다!!"); //로그 찍음

        Article target = articleRepository.findById(id).orElse(null);
        // 1. 1. articleRepository에서 해당 id를 읽어와 Article 타입의 target이라는 객체에 전달하여 저장. id가 존재하지 않을 경우 null
        log.info(target.toString()); //target에 담긴 값이 출력

        if (target != null) {
            articleRepository.delete(target);
        } // 2. 검색한 id가 target이라는 Article엔티티에 저장되어 있으면 (즉, 검색한 id로 읽어온 데이터가 있다면) 데이터 삭제를 수행
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
            // addFlashAttribute 설명 https://prodo-developer.tistory.com/11

        return "redirect:/article";
        // 3. 게시글리스트 페이지로 리다이렉트
    }
}

