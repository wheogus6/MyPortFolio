package com.wheogus.myportfolio.controller;

import com.wheogus.myportfolio.dao.BoardDao;
import com.wheogus.myportfolio.domain.BoardDto;
import com.wheogus.myportfolio.domain.CommentDto;
import com.wheogus.myportfolio.service.BoardService;
import com.wheogus.myportfolio.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    BoardService boardService;


    @PostMapping("/write")
    public String write(CommentDto commentDto, HttpSession session) throws Exception {
        String commenter = (String) session.getAttribute("id");
        commentDto.setCommenter(commenter);

        commentService.write(commentDto);
        return "redirect:/board/read?num=" + commentDto.getNum();
    }
    @GetMapping("/write")
    public String write(Model model, Integer num){
        model.addAttribute("commentMode", "modify");
        return "redirect:/board/read?num=" + num;
    }

    @PostMapping("/delete")
    public String remove(Integer cno, Integer num, HttpSession session) throws Exception {
        String commenter = (String) session.getAttribute("id");

        commentService.delete(cno, num, commenter);

        return "redirect:/board/read?num=" + num;
    }


    @PostMapping("/modify")
    public String modify(CommentDto commentDto) throws Exception{
        commentService.modify(commentDto);
        return "redirect:/board/read?num=" + commentDto.getNum();
    }
}


