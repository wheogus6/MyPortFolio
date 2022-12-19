package com.wheogus.myportfolio.controller;

import com.wheogus.myportfolio.dao.UserDao;
import com.wheogus.myportfolio.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLEncoder;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserDao userDao;

    @GetMapping("/addUser")
    public String register() {
        return "registerForm";
    }

    @PostMapping("/plusUser")
    public String infoSave(@Valid UserDto userDto, Model model) {
        if(!isValid(userDto)){
            String msg = URLEncoder.encode("dddd");
            return "redirect:/register/add";
        }
//        userDao.insertUser(userDto);
//        model.addAttribute(userDto);
        return "registerInfo";
    }

//    @GetMapping("/plusUser")
//    public String infoSave(Model model) {
////        model.addAttribute("mode", "new");
//        return "/";
//    }
}
