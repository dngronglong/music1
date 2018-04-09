package com.dong.music.controller;

import com.dong.music.beans.UserBean;
import com.dong.music.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class RegisterController {
    @Resource
    private UserRepository userRepository;


    @GetMapping("/registe")
    public String register(){
        return "register";
    }

    @ResponseBody
    @PostMapping(value = "/register")
    public ModelAndView register(String userName,String password,String email){
        UserBean user=new UserBean();
        user.setEmail(email);
        user.setPwd(password);
        user.setUserName(userName);
        userRepository.save(user);
        return new ModelAndView("/login");
    }
}
