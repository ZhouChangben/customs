package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.dcLog;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.service.LogService;
import com.xsz.customs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@RequestBody UserLoginDTO userLoginDTO,
                        HttpServletRequest request,
                        HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("登录");
        logService.InsertLog(log);

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
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("退出");
        logService.InsertLog(log);

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
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("新增用户");
        logService.InsertLog(log);

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
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("更新用户信息");
        logService.InsertLog(log);

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
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("删除用户");
        logService.InsertLog(log);

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
        SubUsersDTO subUsersDTO = new SubUsersDTO();
        if (user != null){
            String gqdm = user.getDcGqdm();
            int gqdj = user.getDcGqdj();
            List<dcUser> users = userService.getUserList(gqdm, gqdj);
            if (users != null){
                users.add(user);
                int size = users.size();
                users = userService.getUserList(gqdm, gqdj,page,rows,size);
                subUsersDTO.setTotal(size);
                subUsersDTO.setRows(users);
            }
        }
            return subUsersDTO;
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

    //获取用户信息的方法
    @ResponseBody
    @RequestMapping(value = "/userInfo",method = RequestMethod.POST)
    public Object userInfoList(Integer page,
                              Integer rows,
                              HttpServletRequest request,
                              HttpServletResponse response){
        dcUser user = (dcUser) request.getSession().getAttribute("user");
        ShowUserInfoDTO showUserInfoDTO = new ShowUserInfoDTO();
        List<UserInfoDTO> userInfoDTOS = userService.getUserInfo(user);
        int size = userInfoDTOS.size();
        userInfoDTOS = userService.getUserInfoListPage(userInfoDTOS,page,rows,size);
        showUserInfoDTO.setTotal(size);
        showUserInfoDTO.setRows(userInfoDTOS);
        return showUserInfoDTO;
    }

    //编辑前的数据写入
    @ResponseBody
    @RequestMapping(value = "/showUserInfo",method = RequestMethod.POST)
    public Object showUserInfo(HttpServletRequest request,
                               HttpServletResponse response){
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        String gqdm = user.getDcGqdm();
        List<UserInfoDTO> userInfoDTOS = userService.getUserInfo(user,null);
        UserInfoDTO userInfoDTO = userInfoDTOS.get(0);
        return userInfoDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public Object updateUserInfo(@RequestBody UserInfoDTO userInfoDTO,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("更新用户联系方式");
        logService.InsertLog(log);

        dcUser user = (dcUser)request.getSession().getAttribute("user");
        ResultDTO resultDTO = new ResultDTO();

        user.setDcWjlxr(userInfoDTO.getWjLxr());
        user.setDcWjlxdh(userInfoDTO.getWjLxdh());
        user.setDcWjfzr(userInfoDTO.getWjFzr());
        user.setDcWjfzdh(userInfoDTO.getWjFzrdh());
        user.setDcDjlxr(userInfoDTO.getDjLxr());
        user.setDcDjlxdh(userInfoDTO.getDjLxdh());
        user.setDcDjfzr(userInfoDTO.getDjFzr());
        user.setDcDjfzdh(userInfoDTO.getDjFzrdh());
        user.setDcZjlxr(userInfoDTO.getZjLxr());
        user.setDcZjlxdh(userInfoDTO.getZjLxdh());
        user.setDcZjfzr(userInfoDTO.getZjFzr());
        user.setDcZjfzdh(userInfoDTO.getZjFzrdh());

        boolean flag = userService.updateUserInfomation(user);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("更新联系人成功");
        }
        else {
            resultDTO.setMessage("更新联系人失败");
        }
        return resultDTO;
    }

    @GetMapping("/index")
    public String toLogin(){
        return "index";
    }

}