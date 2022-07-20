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

@Controller // 아래의 클래스를 컨트롤러로 지정하겠다고 선언하는 어노테이션
@Slf4j // lombok의 어노테이션 중 하나로, log를 쉽게 찍어줄 수 있게 한다
public class NewbieController { // NewbieController라는 어디서나 접근 가능한 퍼블릭 클래스 생성

    @Autowired // 의존성이 주입
    private ArticleRepository articleRepository; // Crud를 상속받은 ArticleRepository 인터페이스의 의존성이 주입되어, Crud가 가능하게 됨

    @GetMapping("/kj") // Get요청을 처리하는 메소드를 /kj에 맵핑
    public String hello(Model model) { // Model은 Controller 에서 생성된 데이터를 담아 View 로 전달할 때 사용하는 객체
        model.addAttribute("name", "KyungJoon"); // KyungJoon이라는 값을 가지는 name이라는 key. /template/kj.mustache에서 name으로 표시
        return "kj"; // view에 넘기기
    }

    @GetMapping("/mypage") // Get요청을 처리하는 메소드를 /mypage에 맵핑. 보여질때는 get방식이지만 글쓰기가 실행되면 post방식으로 넘어감
    public String mypage(Model model) {
        model.addAttribute("name", "mypage model");

        return "mypage"; // view에 넘기기
    }

    @PostMapping("/mypage/create")

    public String createArticle(CreateDTO form) { // CreateDTO 메소드는 title과 content를 파라미터로 가짐
        log.info(form.toString()); // CreateDTO의 toString() 메소드의 오버라이드 된 리턴값을 출력
        // @Slf4j를 사용함으로써 System.out.println에서 log.info로 변경
        Article article = form.toEntity(); // CreateDTO의 가장 마지막줄에서 동작 - toEntity()를 가지는 Article 메소드
        log.info(article.toString()); // Article의 toString() 메소드의 오버라이드 된 리턴값을 출력. 다만 바로 위에서 Article을 toEntity() 메소드로 불러왔기 때문에 id는 null값으로 표시됨
        Article save = articleRepository.save(article); // save()는 Entity 객체를 DB에 저장하는 메소드. save() 메소드는 Spring Data 에서 정의한 CrudRepository 인터페이스의 메소드임
        log.info(save.toString()); // Article의 toString() 메소드의 오버라이드 된 리턴값을 출력해 주므로, id값이 글쓸때마다 1씩 증가하게 출력됨

        return "";
    }

    @GetMapping("/articles/{id}") // /articles/{id}를 GetMapping해주겠다
    public String show(@PathVariable Long id, Model model) { // @PathVariable 어노테이션을 사용함으로써 URL에서 id를 변수로 가져올 수 있음
        log.info("ID is " + id); // 로그 찍어줌

        Article articleEntity = articleRepository.findById(id).orElse(null);
            // 1. 레포지토리에서 해당 id를 찾아 엔티티에 전달. id가 존재하지 않을 경우 null

        model.addAttribute("article", articleEntity);
            // 2. 엔티티에 전달된 해당 id의 데이터를 모델에 등록

        return "article/show";
        // 3. 뷰에서 보여줄 페이지를 설정 (show.mustache에 작성된 내용을 보여줌)
    }

    // 궁금점 : kj나 mypage같이 template디렉토리 아래 바로 생성하지 않고 article이라는 디렉토리를 생성하고 그 안에 show.mustache를 만든 이유는?

}