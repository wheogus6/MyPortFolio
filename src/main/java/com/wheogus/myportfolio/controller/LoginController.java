package com.wheogus.myportfolio.controller;

import com.wheogus.myportfolio.dao.UserDao;
import com.wheogus.myportfolio.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserDao userDao;

    @GetMapping("/in")
    public String loginPage(){
        return "loginForm";
    }

    @PostMapping("/in")
    public String login(String id, String pwd, String toURL, boolean rememberId, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //id, pwd 데이터 검증
        if (!loginCheck(id, pwd)) {
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
            return "redirect:/login/in?msg="+msg;
        }
        //세션 객체 생성 후 객체 가져와서 id 저장
        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        //cookie 생성 & 삭제
        if (rememberId) {
            Cookie cookie = new Cookie("id", id);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("id", id);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        toURL = toURL == null || toURL.equals("") ? "/" : toURL;
        return "redirect:"+toURL;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    public boolean loginCheck(String id, String pwd) {
        UserDto userDto = null;
        try {
            //id로 db데이터 select = userDto에 값 저장
            userDto = userDao.selectUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
            // userDto가 null이 아니고 userDto에 저장된 pwd가 같아야 true
        return userDto != null && userDto.getPwd().equals(pwd);
    }



}
