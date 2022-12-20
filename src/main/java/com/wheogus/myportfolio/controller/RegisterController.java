package com.wheogus.myportfolio.controller;

import com.wheogus.myportfolio.dao.UserDao;

import com.wheogus.myportfolio.domain.UserDto;
import com.wheogus.myportfolio.domain.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserDao userDao;

    final int FAIL = 0;
    @InitBinder
    public void toDate(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
        binder.setValidator(new UserValidator());
    }

    @GetMapping("/addUser")
    public String register() {
        return "registerForm";
    }


    @PostMapping("/addUser")
    public String save(@Valid UserDto userDto, BindingResult result, Model model) throws Exception {
        System.out.println("result = " + result);
        System.out.println("userDto = " + userDto);
        if(!result.hasErrors()) {

            int rowCnt = userDao.insertUser(userDto);

            if(rowCnt != FAIL) {
                return "loginForm";
            }
        }
        return "registerForm";
    }

    private boolean isValid(UserDto userDto) {
        return true;
    }



}
