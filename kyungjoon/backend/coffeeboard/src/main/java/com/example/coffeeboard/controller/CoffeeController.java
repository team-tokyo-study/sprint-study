package com.example.coffeeboard.controller;

import com.example.coffeeboard.dto.CoffeeDTO;
import com.example.coffeeboard.entity.Board;
import com.example.coffeeboard.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@Slf4j

public class CoffeeController {
    @Autowired
    private BoardRepository boardRepository; // CRUD 사용 가능

    @GetMapping("/board/write") // 글쓰기 페이지
    public String write(Model model) {
        model.addAttribute("name", "write model"); // write model의 값을 name으로 지정 후 model에 등록

        return "board/write";
    }

    @PostMapping("/board/write/create") //글쓰기 버튼 클릭 후
    public String createBoard(CoffeeDTO form) { // write.mustache의 form에서 입력받은 내용을 DTO에 담는 메소드 createBoard
        log.info(form.toString());

        Board board = form.toEntity(); // 전달된 DTO의 데이터를 엔티티로 변환
        log.info(board.toString());

        Board saved = boardRepository.save(board); // Repository에게 Entity를 DB안에 저장하게 한다
        // boardRepository가 save라는 메소드를 호출할 것인데, 위에서 데이터가 저장된 board를 save하게 하고, Board saved로 반환
        log.info(saved.toString());
        log.info("Number is " + saved.getNo());

        return "redirect:/board/" + saved.getNo();
    }

    @GetMapping("/board/{no}")
    public String detail (@PathVariable Long no, Model model) { // 모델 객체를 Long id값에 담아 가져오는 detail이라는 메소드 선언
        log.info("Number is" + no);

        Board boardEntity = boardRepository.findById(no).orElse(null);
        // 1. Repository에서 해당 id를 읽어와 boardEntity라는 객체에 전달하여 저장. id가 존재하지 않을 경우 null
        model.addAttribute("board", boardEntity);
        // 2. boardEntity로 전달된 데이터를 board라는 이름으로 모델에 등록

        return "/board/detail";
    }

    @GetMapping("/board")
    public String list (Model model) { // // 모델 객체를 가져오는 list라는 메소드 선언
        List<Board> boardList = boardRepository.findAll();
        // 1. boardRepository에서 값을 가져와 List<Board>타입의 boardList 객체에 저장
        log.info("Board list is " + boardList);

        model.addAttribute("boardList", boardList);
        // 2. boardList로 전달된 데이터를 boardList라는 이름으로 모델에 등록

        return "board/list";
    }

    @GetMapping("/board/{no}/edit")
    public String edit(@PathVariable Long no, Model model) {
        log.info("Number is" + no);

        Board boardEntity = boardRepository.findById(no).orElse(null);
        // 1. Repository에서 해당 id를 읽어와 boardEntity라는 객체에 전달하여 저장. id가 존재하지 않을 경우 null
        model.addAttribute("board", boardEntity);
        // 2. boardList로 전달된 데이터를 boardList라는 이름으로 모델에 등록
        return "board/edit";
    }

    @PostMapping("/board/edit")
    public String edit(CoffeeDTO form) {
        log.info(form.toString());

        Board boardEntity = form.toEntity();
        // 1. 전달된 DTO의 데이터를 엔티티로 변환
        log.info(boardEntity.toString());

        Board target = boardRepository.findById(boardEntity.getNo()).orElse(null);
        // 2. boardRepository가 DB에서 id값을 검색 후 해당 데이터를 받아와 엔티티에 전달 및 저장
        if (target != null){ // // 검색한 id가 엔티티에 저장되어 있으면
            boardRepository.save(boardEntity); // // 엔티티가 저장되어 레포지토리로 전달, DB가 갱신
        }

        return "redirect:/board/" + boardEntity.getNo();
        // 수정결과 페이지로 리다이렉트
    }

    @GetMapping("/board/{no}/delete")
    public String delete(@PathVariable Long no, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다");

        Board target = boardRepository.findById(no).orElse(null);
        log.info(target.toString());

        if (target != null) {
            boardRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제 완료");
        }

        return "redirect:/board";
    }
}
