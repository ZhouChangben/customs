package com.xsz.customs.controller;

import com.xsz.customs.model.dcUser;
import com.xsz.customs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    //获取目标用户列表的方法
    public String index(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model){
        HttpSession session = request.getSession();
        //由于持久登录的实现导致session中一直有一个user对象，想要获取下属的列表至少需要获得当前用户
        dcUser user = (dcUser) session.getAttribute("user");
        if (user != null) {
            int degree = user.getDcGqdj();
            String gqdm = user.getDcGqdm();
            List<dcUser> users = userService.getUserList(gqdm, degree);
            model.addAttribute("subusers", users);
            return "index";
        }
        else{
            return "login";
        }

    }
}
