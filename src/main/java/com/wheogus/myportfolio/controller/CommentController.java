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
    public String write(CommentDto commentDto, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String commenter = (String)session.getAttribute("id");
        commentDto.setCommenter(commenter);

        commentService.write(commentDto);
        return "redirect:/board/read?num=" + commentDto.getNum();
    }

    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer num, HttpSession session) {
        String commenter = (String) session.getAttribute("id");
        try {
            int rowCnt = commentService.delete(cno, num, commenter);
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
            if (commentService.modify(commentDto)!=1){
                throw new Exception("Write failed!");
            }
            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
}

