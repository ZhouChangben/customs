package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.dcDcrw;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.service.DcrwService;
import com.xsz.customs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DcrwController {

    @Autowired
    private DcrwService dcrwService;

    @Autowired
    private UserService userService;

    //添加单个关区任务
    @ResponseBody
    @RequestMapping(value = "addsingledcrw",method = RequestMethod.POST)
    public Object addSingleDcrw(@RequestBody AddSingleDcrwDTO addSingleDcrwDTO,
                          HttpServletRequest request,
                          HttpServletResponse response){

        return "";
    }

    //添加全体关区任务
    @ResponseBody
    @RequestMapping(value = "addalldcrw",method = RequestMethod.POST)
    public Object addAllDcrw(@RequestBody AddAllDcrwDTO addAllDcrwDTO,
                             HttpServletRequest request,
                             HttpServletResponse response){
        String dcrwName = addAllDcrwDTO.getDcrwName();
        dcDcrw dcrw = new dcDcrw();
        dcrw.setDcRenwumc(dcrwName);
        AddDcrwResultDTO addDcrwResultDTO = new AddDcrwResultDTO();
        boolean flag = dcrwService.createAll(dcrw);
        addDcrwResultDTO.setSuccess(flag);
        if (flag == true){
            addDcrwResultDTO.setMessage("添加全体任务成功");
        }
        else {
            addDcrwResultDTO.setMessage("添加全体任务失败");
        }
        return addDcrwResultDTO;
    }

    //删除任务（选中然后删除）
    @ResponseBody
    @RequestMapping(value = "deletedcrw",method = RequestMethod.POST)
    public Object deleteMission(@RequestBody DeleteDcrwDTO deleteDcrwDTO,
                         HttpServletRequest request,
                         HttpServletResponse response){

        return "";
    }

    //快捷删除的方法
    @GetMapping("deletedcrw")
    public void deleteByRwmc(){
        dcrwService.deleteDcrwByRwmc("2020年第二次");
    }

    //显示当前任务(高级关区要显示自己的子关区的任务）
    @ResponseBody
    @RequestMapping(value = "dcrwgl",method = RequestMethod.POST)
    public Object getMissionListLastest(Integer page,
                                     Integer rows,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        LatestMissionDTO latestMissionDTO = new LatestMissionDTO();
        int max = dcrwService.findLatestMission();
        if (user != null){
            //此时为北京科技司，理论上显示所有的任务
            if (user.getDcGqdj() == 0){
                /*System.out.println("我是北京科技司");*/
                List<dcDcrw> dcrws = dcrwService.getLatestMissionList(max);
                int size = dcrws.size();
                /*System.out.println(size);*/
                dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                latestMissionDTO.setRows(dcrws);
                latestMissionDTO.setTotal(size);
            }
            //当用户为二级关区时
            if (user.getDcGqdj() == 1){
                /*System.out.println("进入方法");*/
                List<dcDcrw> dcrws = dcrwService.findDcrwByGqdm(user.getDcGqdm(),max);
                List<dcUser> users = userService.getUserList(user.getDcGqdm(),1);
                /*System.out.println("本关区子关区的数量"+users.size());*/
                if (users != null) {
                    //这一步非常重要，将两表的内容联系起来
                    for (dcUser dcuser : users) {
                        dcrws.addAll(dcrwService.findDcrwByGqdm(dcuser.getDcGqdm(),max));
                    }
                    int size = dcrws.size();
                    //此方法仅仅用于分页
                    dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                    latestMissionDTO.setTotal(size);
                    latestMissionDTO.setRows(dcrws);
                }
            }
            //当用户为三级关区时（只能查看自己的任务）
            if (user.getDcGqdj() > 1){
                List<dcDcrw> dcrws = dcrwService.findDcrwByGqdm(user.getDcGqdm(),max);
                int size = dcrws.size();
                dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                latestMissionDTO.setTotal(size);
                latestMissionDTO.setRows(dcrws);
            }
        }
        return latestMissionDTO;
    }


}
