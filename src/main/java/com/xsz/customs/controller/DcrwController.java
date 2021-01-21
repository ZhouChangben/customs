package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.dcDcrw;
import com.xsz.customs.model.dcLog;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.model.dcUserExample;
import com.xsz.customs.service.DcrwService;
import com.xsz.customs.service.LogService;
import com.xsz.customs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DcrwController {

    @Autowired
    private DcrwService dcrwService;

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    //添加单个关区任务
    @ResponseBody
    @RequestMapping(value = "addsingledcrw",method = RequestMethod.POST)
    public Object addSingleDcrw(@RequestBody AddSingleDcrwDTO addSingleDcrwDTO,
                          HttpServletRequest request,
                          HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("添加单个任务"+addSingleDcrwDTO.getDcrwName());
        logService.InsertLog(log);

        int rwxh = dcrwService.findLatestMission();
        dcDcrw dcrw = new dcDcrw();
        dcrw.setDcRenwuxh(rwxh);
        ResultDTO resultDTO = new ResultDTO();
        if (addSingleDcrwDTO.getDcbName().equals("所有任务")){
            dcrw.setDcRenwugqname(addSingleDcrwDTO.getDcGqName());
            dcrw.setDcRenwugqdm(addSingleDcrwDTO.getDcGqdm());
            dcrw.setDcRenwumc(addSingleDcrwDTO.getDcrwName());
            /*dcrw.setDcDcbname("卫生检疫");
            flag = dcrwService.createSingle(dcrw);*/
            //获取当前二级关区名下的三级关区列表，将其当做参数传入service中的方法中
            /*List<dcUser> users = userService.getUserList(dcrw.getDcRenwugqdm(),1);
            flag = dcrwService.createSingleForSub(dcrw,users);

            dcrw.setDcDcbname("动物检疫");
            *//*flag = dcrwService.createSingle(dcrw);*//*
            flag = dcrwService.createSingleForSub(dcrw,users);

            dcrw.setDcDcbname("植物检疫");
            *//*flag = dcrwService.createSingle(dcrw);*//*
            flag = dcrwService.createSingleForSub(dcrw,users);*/
            dcrw.setDcDcbname("卫生检疫");
            boolean flag1 = dcrwService.createSingle(dcrw);


            dcrw.setDcDcbname("动物检疫");
            boolean flag2 = dcrwService.createSingle(dcrw);


            dcrw.setDcDcbname("植物检疫");
            boolean flag3 = dcrwService.createSingle(dcrw);


            resultDTO.setSuccess(flag1 && flag2 && flag3);
            if (flag1 && flag2 && flag3 == true){
                resultDTO.setMessage("添加单个任务成功");
            }
            if (flag1 && flag2 && flag3 == false){
                resultDTO.setMessage("添加单个任务失败");
            }
        }
        else {
            dcrw.setDcDcbname(addSingleDcrwDTO.getDcbName());
            dcrw.setDcRenwugqname(addSingleDcrwDTO.getDcGqName());
            dcrw.setDcRenwugqdm(addSingleDcrwDTO.getDcGqdm());
            dcrw.setDcRenwumc(addSingleDcrwDTO.getDcrwName());
            /*List<dcUser> users = userService.getUserList(dcrw.getDcRenwugqdm(),1);
            *//*boolean flag = dcrwService.createSingle(dcrw);*//*
            flag = dcrwService.createSingleForSub(dcrw,users);*/

            boolean flag = dcrwService.createSingle(dcrw);
            resultDTO.setSuccess(flag);
            if (flag == true){
                resultDTO.setMessage("添加单个任务成功");
            }else{
                resultDTO.setMessage("添加单个任务失败");
            }
        }
        return resultDTO;
    }

    //添加全体关区任务
    @ResponseBody
    @RequestMapping(value = "addalldcrw",method = RequestMethod.POST)
    public Object addAllDcrw(@RequestBody AddAllDcrwDTO addAllDcrwDTO,
                             HttpServletRequest request,
                             HttpServletResponse response){

        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("添加全体任务"+addAllDcrwDTO.getDcrwName());
        logService.InsertLog(log);

        String dcrwName = addAllDcrwDTO.getDcrwName();
        dcDcrw dcrw = new dcDcrw();
        dcrw.setDcRenwumc(dcrwName);
        ResultDTO resultDTO = new ResultDTO();
        boolean flag = dcrwService.createAll(dcrw);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("添加全体任务成功");
        }
        else {
            resultDTO.setMessage("添加全体任务失败");
        }
        return resultDTO;
    }

    //删除任务（选中然后删除）
    @ResponseBody
    @RequestMapping(value = "deletedcrw",method = RequestMethod.POST)
    public Object deleteMission(@RequestBody DeleteDcrwDTO deleteDcrwDTO,
                         HttpServletRequest request,
                         HttpServletResponse response){
        //日志记录
         /*long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        Date date = new Date(currentTime);
        System.out.println(formatter.format(date));
        System.out.println(formatter.format(date).getClass());*/
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("删除一条调查任务");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        int rwxh = deleteDcrwDTO.getId();
        boolean flag = dcrwService.deleteDcrwById(rwxh);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除任务成功");
        }
        else {
            resultDTO.setMessage("删除任务失败");
        }
        return resultDTO;
    }

    /*//快捷删除的方法
    @GetMapping("deletedcrw")
    public String deleteByRwmc(){
        dcrwService.deleteDcrwByRwmc("2020第一次");
        return "redirect:/";
    }*/

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
        String gqdm = user.getDcGqdm();
        if (user != null){
            if (user.getDcGqdj() == 0){
                List<dcDcrw> dcrws = dcrwService.getLatestMissionList(max);
                int size = dcrws.size();
                dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                latestMissionDTO.setRows(dcrws);
                latestMissionDTO.setTotal(size);
            }
            else if (user.getDcGqdj() == 1){
                List<dcDcrw> dcrws = dcrwService.findDcrwByGqdm(gqdm,max);
                int size = dcrws.size();
                dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                latestMissionDTO.setRows(dcrws);
                latestMissionDTO.setTotal(size);
            }
            else if(user.getDcGqdj() > 1){
                dcUser user1 = dcrwService.findFatherGq(gqdm);
                List<dcDcrw> dcrws = dcrwService.findDcrwsForThird(user,user1,max);
                int size = dcrws.size();
                dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                latestMissionDTO.setRows(dcrws);
                latestMissionDTO.setTotal(size);
            }
        }
        /*if (user != null){
            //此时为北京科技司，理论上显示所有的任务
            if (user.getDcGqdj() == 0){
                List<dcDcrw> dcrws = dcrwService.getLatestMissionList(max);
                int size = dcrws.size();
                dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                latestMissionDTO.setRows(dcrws);
                latestMissionDTO.setTotal(size);
            }
            //当用户为二级关区时
            if (user.getDcGqdj() == 1){
                List<dcDcrw> dcrws = dcrwService.findDcrwByGqdm(user.getDcGqdm(),max);
                List<dcUser> users = userService.getUserList(user.getDcGqdm(),1);
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
        }*/
        return latestMissionDTO;
    }
    //显示历史调查任务的界面，和显示最新调查任务差不多，仅仅只有几个方法不同而已
    @ResponseBody
    @RequestMapping(value = "lsdcrwgl",method = RequestMethod.POST)
    public Object getMissionListHistory(Integer page,
                                        Integer rows,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        LatestMissionDTO latestMissionDTO = new LatestMissionDTO();
        int max = dcrwService.findLatestMission();
        if (user != null){
            //此时为北京科技司，理论上显示所有的任务
            if (user.getDcGqdj() == 0){
                List<dcDcrw> dcrws = dcrwService.getHistoryMissionList(max);
                int size = dcrws.size();
                dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                latestMissionDTO.setRows(dcrws);
                latestMissionDTO.setTotal(size);
            }
            //当用户为二级关区时
            if (user.getDcGqdj() == 1){
                /*List<dcDcrw> dcrws = dcrwService.findHistoryDcrwByGqdm(user.getDcGqdm(),max);
                List<dcUser> users = userService.getUserList(user.getDcGqdm(),1);
                if (users != null) {
                    //这一步非常重要，将两表的内容联系起来
                    for (dcUser dcuser : users) {
                        dcrws.addAll(dcrwService.findHistoryDcrwByGqdm(dcuser.getDcGqdm(),max));
                    }
                    int size = dcrws.size();
                    //此方法仅仅用于分页
                    dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                    latestMissionDTO.setTotal(size);
                    latestMissionDTO.setRows(dcrws);
                }*/
                List<dcDcrw> dcrws = dcrwService.findHistoryDcrwByGqdm(user.getDcGqdm(),max);
                int size = dcrws.size();
                //此方法仅仅用于分页
                dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                latestMissionDTO.setTotal(size);
                latestMissionDTO.setRows(dcrws);
            }
            //当用户为三级关区时（只能查看自己的任务）
            if (user.getDcGqdj() > 1){
                dcUser fUser = dcrwService.findFatherGq(user.getDcGqdm());
                List<dcDcrw> dcrws = dcrwService.findHistoryDcrwForThrid(fUser.getDcGqdm(),user,max);
                int size = dcrws.size();
                dcrws = dcrwService.getLatestMissionListPage(dcrws,page,rows,size);
                latestMissionDTO.setTotal(size);
                latestMissionDTO.setRows(dcrws);
            }
        }
        //历史调查任务返回的内容和最新任务差不多，所以用一个对象
        return latestMissionDTO;
    }

    @ResponseBody
    @RequestMapping(value = "withdraw",method = RequestMethod.POST)
    public Object withdraw(@RequestBody WithdrawDTO withdrawDTO,
                           HttpServletRequest request,
                           HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("撤回提交的任务");
        logService.InsertLog(log);

        int id = withdrawDTO.getRenwuid();
        boolean flag = dcrwService.modifyStatusToNoSubmit(id);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccess(flag);
        if (flag == true)
            resultDTO.setMessage("修改状态成功");
        else
            resultDTO.setMessage("修改状态失败");
        return resultDTO;
    }


    @RequestMapping(value = "/wsdcrw",method = RequestMethod.GET)
    public String toWsjy(@RequestParam(value = "rwid") Integer rwid,
                       HttpServletRequest request,
                       HttpServletResponse response){
        request.getSession().setAttribute("wsrwid",rwid);
        return "wsdcrw";
    }

    @RequestMapping(value = "/dwdcrw",method = RequestMethod.GET)
    public String toDwjy(@RequestParam(value = "rwid") Integer rwid,
                         HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().setAttribute("dwrwid",rwid);
        return "dwdcrw";
    }

    @RequestMapping(value = "/zwdcrw",method = RequestMethod.GET)
    public String toZwjy(@RequestParam(value = "rwid") Integer rwid,
                         HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().setAttribute("zwrwid",rwid);
        return "zwdcrw";
    }

    @RequestMapping(value = "/wslsjl",method = RequestMethod.GET)
    public String toWslsjl(@RequestParam(value = "rwid") Integer rwid,
                         HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().setAttribute("wslsrwid",rwid);
        return "wslsjl";
    }

    @RequestMapping(value = "/dwlsjl",method = RequestMethod.GET)
    public String toDwlsjl(@RequestParam(value = "rwid") Integer rwid,
                           HttpServletRequest request,
                           HttpServletResponse response){
        request.getSession().setAttribute("dwlsrwid",rwid);
        return "dwlsjl";
    }

    @RequestMapping(value = "/zwlsjl",method = RequestMethod.GET)
    public String toZwlsjl(@RequestParam(value = "rwid") Integer rwid,
                           HttpServletRequest request,
                           HttpServletResponse response){
        request.getSession().setAttribute("zwlsrwid",rwid);
        return "zwlsjl";
    }

    @ResponseBody
    @RequestMapping(value = "accept",method = RequestMethod.POST)
    public Object accept(@RequestBody WithdrawDTO withdrawDTO,
                           HttpServletRequest request,
                           HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("接受了调查任务");
        logService.InsertLog(log);

        int id = withdrawDTO.getRenwuid();
        boolean flag = dcrwService.modifyStatusToAccept(id);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setSuccess(flag);
        if (flag == true)
            resultDTO.setMessage("修改状态成功");
        else
            resultDTO.setMessage("修改状态失败");
        return resultDTO;
    }


}
