package com.wheogus.myportfolio.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.wheogus.myportfolio.dao.BoardDao;
import com.wheogus.myportfolio.domain.BoardDto;
import com.wheogus.myportfolio.domain.PageHandler;
import com.wheogus.myportfolio.domain.SearchCondition;
import com.wheogus.myportfolio.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;
        // id가 null이 아니라면 true -> id값이 있다면 true
    }

    @GetMapping("/list")
    public String listForm(Model model, SearchCondition sc, HttpServletRequest request) {
        if (!loginCheck(request)) {
            return "redirect:/login/in?toURL=" + request.getRequestURL(); //loginCheck가 false라면 login화면으로 이동.
        }
        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            model.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchSelectPage(sc);
            model.addAttribute("list", list);
            model.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            model.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "LIST_ERR");
            model.addAttribute("totalCnt", 0);
        }
        return "boardList";
    }

    @GetMapping("/read")
    public String read(Integer num, Integer page, Integer pageSize, Model model) {
        try {
            BoardDto boardDto = boardService.read(num);
            model.addAttribute(boardDto);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("mode", "new");
        return "/board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, HttpSession session, Model model, RedirectAttributes ratter) {
        String writer = (String) session.getAttribute("id");  //형변환 주의
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.insert(boardDto);
            if (rowCnt != 1) {
                throw new Exception("write falid");
            }
            ratter.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
           e.printStackTrace();
            model.addAttribute(boardDto);
            model.addAttribute("msg", "WRT_ERR");
            return "board";
        }
    }

    @PostMapping("/remove")
    public String remove(Integer num, Integer page, Integer pageSize, Model model, HttpSession session, RedirectAttributes ratter) {
        String writer = (String) session.getAttribute("id"); //LoginController 에서 set함.
        try {
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.remove(num, writer);
            if (rowCnt != 1){
                throw new Exception("remove Error");}            //나중에 괄호 수정
            ratter.addFlashAttribute("msg", "DEL_OK");

        } catch (Exception e) {
            e.printStackTrace();
            ratter.addFlashAttribute("msg", "DEL_ERR");
        }
        return "redirect:/board/list";
    }

}
