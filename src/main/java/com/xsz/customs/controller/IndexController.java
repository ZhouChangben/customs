package com.xsz.customs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    //首页貌似就是登录界面，但是未来应该要在内部界面中实现分页操作。
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }


}
