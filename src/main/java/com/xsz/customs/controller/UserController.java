package com.xsz.customs.controller;

import com.xsz.customs.model.dcUser;
import com.xsz.customs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //前端这里要填的其实是关区代码，但是为了更好的可读性写成username
    @GetMapping("/login")
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
}
