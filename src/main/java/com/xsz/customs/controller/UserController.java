package com.xsz.customs.controller;

import com.xsz.customs.model.dcUser;
import com.xsz.customs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping("/login")
    public String login(@RequestParam(name = "gqdm")String gqdm,
                        @RequestParam(name = "password")String password,
                        HttpServletResponse response){
        dcUser user = new dcUser();
        user.setDcGqdm(gqdm);
        user.setDcGqpword(password);
        boolean flag = userService.login(user);
        //flag==1登录成功,则将登录的关区代码写入cookie中，供实现持久登录功能使用
        if (flag){
            Cookie cookie = new Cookie("gqdm", gqdm);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
            return "redirect:/";
        }else{
            System.out.println("登录失败");
            return "redirect:/";
        }
    }

    //用户退出
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("gqdm", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    //当用户提出修改请求时的方法,功能未定先放一放
    @GetMapping("/modify")
    public String modify(){
        return "";
    }

    //注册功能（插入操作），但目前不清楚是否需要注册功能，但也可以理解为插入新用户的操作
    @PostMapping("/Register")
    public String register(@RequestParam(name = "gqdm")String gqdm,
                           @RequestParam(name = "password")String password,
                           @RequestParam(name = "gqname")String gqname,
                           HttpServletRequest request,
                           HttpServletResponse response){
        dcUser user = new dcUser();
        user.setDcGqdm(gqdm);
        user.setDcGqpword(password);
        user.setDcGqname(gqname);
        userService.createOrUpdate(user);
        return "index";
    }

}
