package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@RequestBody UserLoginDTO userLoginDTO,
                        HttpServletRequest request,
                        HttpServletResponse response){
        dcUser user = new dcUser();
        user.setDcGqdm(userLoginDTO.getGqdm());
        user.setDcGqpword(userLoginDTO.getPassword());
        boolean flag = userService.login(user);
        LoginResultDTO loginResultDTO = new LoginResultDTO();
        loginResultDTO.setSuccess(flag);
        if (flag == true){
            loginResultDTO.setMessage("登录成功");
            Cookie cookie = new Cookie("gqdm", userLoginDTO.getGqdm());
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
        }
        else {
            loginResultDTO.setMessage("用户名或密码错误");
        }
        return loginResultDTO;
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

    @ResponseBody
    @RequestMapping(value = "/preregister",method = RequestMethod.GET)
    public  Object preRegister(HttpServletRequest request,
                               HttpServletResponse response){
        HttpSession session = request.getSession();
        dcUser user = (dcUser)session.getAttribute("user");
        String gqdm = userService.getMaxSubUserDm(user.getDcGqdm());
        NextGqdmDTO nextGqdmDTO = new NextGqdmDTO();
        nextGqdmDTO.setGqdm(gqdm);
        return nextGqdmDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Object register(@RequestBody InsertUserDTO insertUserDTO,
                        HttpServletRequest request,
                        HttpServletResponse response){
        dcUser user = new dcUser();
        user.setDcGqdm(insertUserDTO.getGqdm());
        user.setDcGqname(insertUserDTO.getGqname());
        user.setDcLxr(insertUserDTO.getGqlxr());
        user.setDcLxdh(insertUserDTO.getGqlxdh());
        user.setDcGqpword(insertUserDTO.getGqpwd());
        boolean flag = userService.create(user);
        InsertUserResultDTO insertUserResultDTO = new InsertUserResultDTO();
        insertUserResultDTO.setSuccess(flag);
        if (flag == true){
            insertUserResultDTO.setMessage("插入成功");
        }
        else
            insertUserResultDTO.setMessage("插入失敗");
        return insertUserResultDTO;
    }


    @ResponseBody
    @RequestMapping(value = "/preupdate",method = RequestMethod.POST)
    public Object preUpdateuser(@RequestBody UpdateUserFirstDTO updateUserFirstDTO,
                                HttpServletRequest request,
                                HttpServletResponse response){
        String gqdm = updateUserFirstDTO.getGqdm();
        dcUser user = userService.findUserByGqdm(gqdm);
        UpdateUserSecondDTO updateUserSecondDTO = new UpdateUserSecondDTO();
        if (user != null){
            updateUserSecondDTO.setGqdm(user.getDcGqdm());
            updateUserSecondDTO.setGqname(user.getDcGqname());
            updateUserSecondDTO.setGqpwd(user.getDcGqpword());
            updateUserSecondDTO.setGqlxr(user.getDcLxr());
            updateUserSecondDTO.setGqlxdh(user.getDcLxdh());
        }
        return updateUserSecondDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateUser(@RequestBody UpdateUserSecondDTO updateUserSecondDTO,
                             HttpServletRequest request,
                             HttpServletResponse response){
        dcUser user = new dcUser();
        //由于返回的DTO都是一样的所以使用insert的
        InsertUserResultDTO insertUserResultDTO = new InsertUserResultDTO();
        String gqdm = updateUserSecondDTO.getGqdm();
        String gqname = updateUserSecondDTO.getGqname();
        String gqpwd = updateUserSecondDTO.getGqpwd();
        String gqlxr = updateUserSecondDTO.getGqlxr();
        String gqlxdh = updateUserSecondDTO.getGqlxdh();
        user.setDcGqdm(gqdm);
        user.setDcGqname(gqname);
        user.setDcGqpword(gqpwd);
        user.setDcLxr(gqlxr);
        user.setDcLxdh(gqlxdh);
        boolean flag = userService.update(user);
        insertUserResultDTO.setSuccess(flag);
        if (flag){
            insertUserResultDTO.setMessage("更新用户信息成功");
        }
        else{
            insertUserResultDTO.setMessage("更新用户信息失败");
        }
        return insertUserResultDTO;
    }

    //删除用户
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object deleteUser(@RequestBody DeleteUserDTO deleteUserDTO,
                             HttpServletRequest request,
                             HttpServletResponse response){
        String gqdm = deleteUserDTO.getGqdm();
        boolean flag = userService.deleteUser(gqdm);
        DeleteUserResultDTO deleteUserResultDTO = new DeleteUserResultDTO();
        deleteUserResultDTO.setSuccess(flag);
        if (flag == true){
            deleteUserResultDTO.setMessage("删除成功");
        }
        else
            deleteUserResultDTO.setMessage("删除失败");
        return deleteUserResultDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/userManage",method = RequestMethod.POST)
    public Object subUserList(Integer page,
                              Integer rows,
                              HttpServletRequest request,
                              HttpServletResponse response){
        //System.out.println(page);
        dcUser user = (dcUser) request.getSession().getAttribute("user");
        if (user != null){
            String gqdm = user.getDcGqdm();
            int gqdj = user.getDcGqdj();
            List<dcUser> users = userService.getUserList(gqdm, gqdj);
            int size = users.size();
            users = userService.getUserList(gqdm, gqdj,page,rows,size);
            SubUsersDTO subUsersDTO = new SubUsersDTO();
            subUsersDTO.setTotal(size);
            subUsersDTO.setRows(users);
            return subUsersDTO;
        }
        else
            return null;
    }

    //获取所有二级关区，是在点击发布单个任务的时候使用的
    @ResponseBody
    @RequestMapping(value = "/allsubuser",method = RequestMethod.POST)
    public Object findAllSubUser(){
        List<dcUser> users = userService.findAllSubuser();
        List<ShowAllSubUserDTO> allSubUsers = new ArrayList<>();
        for (dcUser user : users) {
            ShowAllSubUserDTO showAllSubUserDTO = new ShowAllSubUserDTO();
            String gqdm = user.getDcGqdm();
            String gqname = user.getDcGqname();
            showAllSubUserDTO.setDcGqdm(gqdm);
            showAllSubUserDTO.setDcGqname(gqname);
            allSubUsers.add(showAllSubUserDTO);
        }
        return allSubUsers;
    }

    //以下均为测试用方法
    /*@GetMapping("/update")
    public String toUpdate(){
        return "update";
    }*/
    @GetMapping("/index")
    public String toLogin(){
        return "index";
    }


}
