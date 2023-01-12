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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//    @GetMapping("/modify")
//    public String modify(Model model, Integer num){
//        model.addAttribute("commentMode", "modify");
//        return "redirect:/board/read?num=" + num;
//    }

    @PostMapping("/delete")
    public String remove(Integer cno, Integer num) throws Exception {

        commentService.delete(cno, num);

        return "redirect:/board/read?num" + num;
    }


    @GetMapping("/modify")
    public String getModify(@RequestParam("num") Integer num, @RequestParam("cno") Integer cno, Model model) throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setCno(cno);
        commentDto.setNum(num);

        CommentDto comment = commentService.read(commentDto);

        model.addAttribute("comment", comment);
        return "commentMod";
    }

    @PostMapping("/modify")
    public String postModify(CommentDto commentDto) throws Exception{
        commentService.modify(commentDto);
        return "redirect:/board/read?num=" + commentDto.getNum();
    }

}


