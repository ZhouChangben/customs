package com.xsz.customs.controller;

import com.xsz.customs.model.dcLog;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.service.LogService;
import com.xsz.customs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @GetMapping("/")
    //获取目标用户列表的方法,设想中也希望进入首页时显示任务代码最大的。
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
        HttpSession session = request.getSession();
        //由于持久登录的实现导致session中一直有一个user对象，想要获取下属的列表至少需要获得当前用户
        dcUser user = (dcUser) session.getAttribute("user");
        if (user != null) {
            return "index";
        }
        else{
            return "login";
        }
    }

    @GetMapping("testTime")
    public String getLogService() {
        dcLog log = logService.selectLogById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        Date date = new Date(log.getTime());
        System.out.println(formatter.format(date));
        return "index";
    }

    @GetMapping("/userMP")
    public String toUserManage(){
        return "userManage";
    }
    @GetMapping("/dcrwGL")
    public String toDcrwgl(){
        return "dcrwgl";
    }
    @GetMapping("/lsdcrw")
    public String toHistoryDcrw(){return "lsdcrw";}
    @GetMapping("/userInfo")
    public String toUserInfo(){return "userInfo";}
    @GetMapping("/wjtj")
    public String toWjTj(){return "wjtj";}
    @GetMapping("/djtj")
    public String toDjTj(){return "djtj";}
    @GetMapping("/zjtj")
    public String toZjTj(){return "zjtj";}

    @GetMapping("/wjSearch")
    public String toWjSearch(){return "wjsearch";}
    @GetMapping("/djSearch")
    public String toDjSearch(){return "djsearch";}
    @GetMapping("/zjSearch")
    public String toZjSearch(){return "zjsearch";}
}
