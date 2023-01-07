package com.wheogus.myportfolio.controller;

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

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService service;
    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String commentList(Model model, BoardDto boardDto){
        try {
            Integer num = boardDto.getNum();
            List<CommentDto> list = service.getList(num);
            model.addAttribute("commentList", list);
            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            model.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/board/read";
    }

    @GetMapping("/write")
    public String write(Model model) {
//        model.addAttribute("mode", "new");
        return "redirect:/comments/list";
    }

    @PostMapping("/write")
    public String write(CommentDto commentDto, BoardDto boardDto, HttpSession session, Model model) {
        String commenter = (String)session.getAttribute("id");
        Integer num = boardDto.getNum();
        commentDto.setCommenter(commenter);
        commentDto.setNum(num);

        try {
            int newComment = service.write(commentDto);
            if (newComment != 1) {
                throw new Exception("error");
            }
            return "board";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(commentDto);
            return "board";
        }
    }

    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer num, HttpSession session) {
        String commenter = (String) session.getAttribute("id");
        try {
            int rowCnt = service.delete(cno, num, commenter);
            if (rowCnt != 1) {
                throw new Exception("Delete Failed");
            }
            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto, HttpSession session) {
        String commenter = (String) session.getAttribute("id");
        commentDto.setComment(commenter);
        commentDto.setCno(cno);
        try {
            if (service.modify(commentDto)!=1){
                throw new Exception("Write failed!");
            }
            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
}

