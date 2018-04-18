package com.dong.music.controller;

import com.dong.music.repository.UserRepository;
import com.dong.music.beans.UserBean;
import com.dong.music.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserService userService;
    /**
     * 返回登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    /**
     * 调用登录方法，如果有用户返回一个index视图，反之返回登录页面
     * @param userName
     * @param password
     * @param rq
     * @param rp
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/login", produces = {"application/json;charset=utf-8"})
    public ModelAndView login(String userName, String password,String CheckCode, HttpServletRequest rq, HttpServletResponse rp) {
        HttpSession session=rq.getSession();
        if (!CheckCode.equalsIgnoreCase(session.getAttribute("code").toString())){
            return new ModelAndView("redirect:/login");
        }
        UserBean u=new UserBean();
        u.setUserName(userName);
        u.setPwd(password);
        UserBean user=userService.findUser(u);
        System.out.println(user);
        if (user != null) {
            session.setAttribute("user", user);
            return new ModelAndView("redirect:/index");
        }
        return new ModelAndView("redirect:/login");
    }

    /**
     * 退出方法，返回一个登录视图
     * @param rq
     * @return
     */
    @RequestMapping(value = "/exit")
    public ModelAndView exit(HttpServletRequest rq) {
        HttpSession session=rq.getSession();
        session.removeAttribute("user");
        return new ModelAndView("redirect:/login");
    }


}