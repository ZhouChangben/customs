package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.dcLog;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.model.dcWsjy;
import com.xsz.customs.model.wjLblx;
import com.xsz.customs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class WsjyController {

    @Autowired
    private DwjyService dwjyService;

    @Autowired
    private WsjyService wsjyService;

    @Autowired
    private ZwjyService zwjyService;

    @Autowired
    private DcrwService dcrwService;

    @Autowired
    private LogService logService;

    @Autowired
    private LblxService lblxService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "addWsjy",method = RequestMethod.POST)
    public Object addDwjy(@RequestBody WsjyDTO wsjyDTO,
                          HttpServletRequest request,
                          HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("添加一条卫生检疫表项");
        logService.InsertLog(log);

        int renwuid = (int)request.getSession().getAttribute("wsrwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        if (user.getDcGqdj() > 1){
            dcUser fUser = userService.findFatherGq(user.getDcGqdm());
            wsjyDTO.setGqdm(fUser.getDcGqdm());
        }
        ResultDTO resultDTO = new ResultDTO();
        dcWsjy wsjy;
        wsjy = wsjyService.transformDTOToWsjy(wsjyDTO);
        wsjy.setDcRenwuid(renwuid);
        wsjy.setDcRenwugqdm2(user.getDcGqdm());
        boolean flag = wsjyService.insertNewTable(wsjy);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("插入卫生检疫表格成功");
        }
        else {
            resultDTO.setMessage("插入卫生检疫表格失败");
        }
        return resultDTO;
    }

    //将数据库中的数据写在未提交的表中
    @ResponseBody
    @RequestMapping(value = "showWsjyTable",method = RequestMethod.POST)
    public Object showTable(@RequestBody ShowTableDTO showTableDTO,
                            HttpServletRequest request,
                            HttpServletResponse response){
        WsjyDTO wsjyDTO = new WsjyDTO();
        int id = showTableDTO.getId();
        dcWsjy wsjy = wsjyService.findWsjyById(id);
        wsjyDTO = wsjyService.transformWsjyToDTO(wsjy);
        return wsjyDTO;
    }

    //展示当前任务下的卫生检疫的所有表项,也许将来要把所有的调查表都整合在这个方法里
    @ResponseBody
    @RequestMapping(value = "wsdcrw",method = RequestMethod.POST)
    public Object showWsjys(Integer page,
                            Integer rows,
                            HttpServletRequest request,
                            HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("wsrwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        ShowWsjyDTO showWsjyDTO = new ShowWsjyDTO();
        List<dcWsjy> wsjys = wsjyService.getWsjysByRenwuidForSub(renwuid,user);
        int size = wsjys.size();
        wsjys = wsjyService.getWsjyListPage(wsjys,page,rows,size);
        showWsjyDTO.setTotal(size);
        showWsjyDTO.setRows(wsjys);
        return showWsjyDTO;
    }

    @ResponseBody
    @RequestMapping(value = "wslsjl",method = RequestMethod.POST)
    public Object showWsjyls(Integer page,
                             Integer rows,
                             HttpServletRequest request,
                             HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("wslsrwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        ShowWsjyDTO showWsjyDTO = new ShowWsjyDTO();
        List<dcWsjy> wsjys = wsjyService.getWsjysByRenwuidForSub(renwuid,user);
        int size = wsjys.size();
        wsjys = wsjyService.getWsjyListPage(wsjys,page,rows,size);
        showWsjyDTO.setTotal(size);
        showWsjyDTO.setRows(wsjys);
        return showWsjyDTO;
    }

    //点击添加数据时将联系人，联系电话、关区等返回给前台
    @ResponseBody
    @RequestMapping(value = "showinformation",method = RequestMethod.POST)
    public Object showInformationAlreadyKnow(HttpServletRequest request,
                                             HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("wsrwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        WsjyInfoDTO wsjyInfoDTO = new WsjyInfoDTO();
        wsjyInfoDTO.setRwid(renwuid);
        wsjyInfoDTO = wsjyService.showInformationAlreadyKnow(wsjyInfoDTO,user.getDcGqdm());
        return wsjyInfoDTO;
    }

    //修改已有的数据
    @ResponseBody
    @RequestMapping(value = "modifyWsjy",method = RequestMethod.POST)
    public Object moodifyWsjy(@RequestBody WsjyDTO wsjyDTO,
                              HttpServletRequest request,
                              HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("修改一条卫生检疫表项");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        dcWsjy wsjy = new dcWsjy();

        wsjy = wsjyService.transformDTOToWsjy(wsjyDTO);
        boolean flag = wsjyService.updateWsjy(wsjy);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("修改卫生检疫表格成功");
        }
        else {
            resultDTO.setMessage("修改卫生检疫表格失败");
        }
        return resultDTO;
    }

    //删除卫生检疫表项
    @ResponseBody
    @RequestMapping(value = "deleteWsjy",method = RequestMethod.POST)
    public Object deleteWsjy(@RequestBody ShowTableDTO showTableDTO,
                            HttpServletRequest request,
                            HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("删除一条卫生检疫表项");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        int id = showTableDTO.getId();
        boolean flag = wsjyService.deleteWsjyById(id);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除卫生检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除卫生检疫表格失败");
        }
        return resultDTO;
    }

    //提交卫生检疫表,DTO是借用的，在具体表项页提交
    @ResponseBody
    @RequestMapping(value = "submit",method = RequestMethod.POST)
    public Object submit(HttpServletRequest request,
                         HttpServletResponse response){
        ResultDTO resultDTO = new ResultDTO();
        int rwid = (int)request.getSession().getAttribute("wsrwid");
        boolean flag = dcrwService.modifyStatusToSubmit(rwid);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除卫生检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除卫生检疫表格失败");
        }
        return resultDTO;
    }

    //在外部提交卫生检疫任务
    @ResponseBody
    @RequestMapping(value = "submitWsjyOut",method = RequestMethod.POST)
    public Object submitOut(@RequestBody WsjyDTO wsjyDTO,
                            HttpServletRequest request,
                            HttpServletResponse response){
        ResultDTO resultDTO = new ResultDTO();
        int rwid = wsjyDTO.getRwid();
        boolean flag = dcrwService.modifyStatusToSubmit(rwid);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除卫生检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除卫生检疫表格失败");
        }
        return resultDTO;
    }

    //将历史任务中卫生检疫表的表项选中后插入到当前任务中
    @ResponseBody
    @RequestMapping(value = "historyOnceMoreWs",method = RequestMethod.POST)
    public Object historyOnceMoreWj(@RequestBody HistoryOnceMoreDTO historyOnceMoreDTO,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("添加一条历史卫生检疫表项");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        List<Integer> ids = historyOnceMoreDTO.getIds();
        boolean flag = wsjyService.historyOnceMoreWj(ids);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除卫生检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除卫生检疫表格失败");
        }
        return resultDTO;
    }
    /*@ResponseBody
    @RequestMapping(value = "historyOnceMore",method = RequestMethod.POST)
    public Object historyOnceMore(@RequestBody HistoryOnceMoreDTO historyOnceMoreDTO,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        *//*System.out.println("111");
        System.out.println(historyOnceMoreDTO);
        ResultDTO resultDTO = new ResultDTO();
        String ids = historyOnceMoreDTO.getIds();
        System.out.println(ids);
        String[] idstring = ids.split(",");
        List<Integer> intId = new ArrayList<>();
        for (String s : idstring) {
            intId.add(Integer.parseInt(s));
        }
        System.out.println(intId);
        boolean flag = wsjyService.historyOnceMoreWj(intId);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除卫生检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除卫生检疫表格失败");
        }
        return resultDTO;*//*
        return true;
    }*/
    @ResponseBody
    @RequestMapping(value = "wsjySearch",method = RequestMethod.POST)
    public Object wsjySearch(@RequestBody SeasrchContentDTO seasrchContentDTO,
                             HttpServletRequest request,
                             HttpServletResponse response){
        dcUser user = (dcUser) request.getSession().getAttribute("user");
        String content = seasrchContentDTO.getContent();
        content = '%' + content + '%';
        List<dcWsjy> wsjys = wsjyService.searchWj(content,user);
        ShowWsjyDTO showWsjyDTO = new ShowWsjyDTO();
        showWsjyDTO.setRows(wsjys);
        showWsjyDTO.setTotal(wsjys.size());
        return showWsjyDTO;
    }

    //卫生检疫统计功能
    /*@ResponseBody
    @RequestMapping(value = "wsjyStatistics",method = RequestMethod.POST)
    public Object wsjyStatistics(@RequestBody StatisticTypeDTO statisticTypeDTO,
                                 Integer page,
                                 Integer rows,
                                 HttpServletRequest request,
                             HttpServletResponse response){
        List<wjLblx> allWjLbs = lblxService.getAllWjlx();
        StatisticWjResultDTO statisticWjResultDTO = new StatisticWjResultDTO();
        List<StatisticWjSingleResultDTO> singleResultDTOS;

        singleResultDTOS= wsjyService.countType(allWjLbs);
        //选择统计所有关区的资源数量
        *//*if (statisticTypeDTO.isAll()){
            singleResultDTOS= wsjyService.countType(allWjLbs);
        }
        else {
            String gqName = statisticTypeDTO.getGqName();
            singleResultDTOS = wsjyService.countTypeForOneGq(allWjLbs,gqName);
        }*//*
        int size = singleResultDTOS.size();
        System.out.println(size);
        System.out.println(page);
        System.out.println(rows);
        singleResultDTOS = wsjyService.getWjTjListPage(singleResultDTOS,page,rows,size);
        statisticWjResultDTO.setTotal(size);
        statisticWjResultDTO.setRows(singleResultDTOS);
        return statisticWjResultDTO;
    }*/

    //卫生检疫统计功能
    @ResponseBody
    @RequestMapping(value = "wsjyStatistics",method = RequestMethod.GET)
    public Object wsjyStatistics(@RequestParam boolean isAll,
                                     @RequestParam String gqName,
                                     Integer page,
                                     Integer rows,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        List<wjLblx> allWjLbs = lblxService.getAllWjlx();
        StatisticWjResultDTO statisticWjResultDTO = new StatisticWjResultDTO();
        List<StatisticWjSingleResultDTO> singleResultDTOS;
        //选择统计所有关区的资源数量
        if (isAll){
            singleResultDTOS= wsjyService.countType(allWjLbs);
        }
        else {
            singleResultDTOS = wsjyService.countTypeForOneGq(allWjLbs,gqName);
        }
        int size = singleResultDTOS.size();
        singleResultDTOS = wsjyService.getWjTjListPage(singleResultDTOS,page,rows,size);
        statisticWjResultDTO.setTotal(size);
        statisticWjResultDTO.setRows(singleResultDTOS);
        return statisticWjResultDTO;
    }
}
