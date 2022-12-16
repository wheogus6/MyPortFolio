package com.wheogus.myportfolio.controller;

import com.wheogus.myportfolio.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String listForm(){
        return "boardList";
    }

//    @PostMapping("/remove")
//    public String remove(Integer num, Model model, HttpSession session) {
//        String writer = (String) session.getAttribute("id");
//
//        try {
//            boardService.remove(num, writer);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return "redirect:/board/list";
//    }


}
