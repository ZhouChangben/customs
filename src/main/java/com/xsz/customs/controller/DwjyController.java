package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.*;
import com.xsz.customs.model.dcDwjy;
import com.xsz.customs.service.*;
import com.xsz.customs.service.DwjyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DwjyController {
    @Autowired
    private DwjyService dwjyService;

    @Autowired
    private ZwjyService zwjyService;

    @Autowired
    private DcrwService dcrwService;

    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    @Autowired
    private LblxService lblxService;

    @ResponseBody
    @RequestMapping(value = "addDwjy",method = RequestMethod.POST)
    public Object addDwjy(@RequestBody DwjyDTO dwjyDTO,
                          HttpServletRequest request,
                          HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("添加一条动物检疫表项");
        logService.InsertLog(log);

        int renwuid = (int)request.getSession().getAttribute("dwrwid");
        dcUser user = (dcUser) request.getSession().getAttribute("user");
        if (user.getDcGqdj() > 1){
            dcUser fUser = userService.findFatherGq(user.getDcGqdm());
            dwjyDTO.setGqdm(fUser.getDcGqdm());
        }
        ResultDTO resultDTO = new ResultDTO();
        dcDwjy dwjy;
        dwjy = dwjyService.transformDTOToDwjy(dwjyDTO);

        dwjy.setDcRenwuid(renwuid);
        dwjy.setDcRenwugqdm2(user.getDcGqdm());
        boolean flag = dwjyService.insertNewTable(dwjy);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("插入动物检疫表格成功");
        }
        else {
            resultDTO.setMessage("插入动物检疫表格失败");
        }
        return resultDTO;
    }

    //将数据库中的数据写在未提交的表中
    @ResponseBody
    @RequestMapping(value = "showDwjyTable",method = RequestMethod.POST)
    public Object showTable(@RequestBody ShowTableDTO showTableDTO,
                            HttpServletRequest request,
                            HttpServletResponse response){
        DwjyDTO dwjyDTO = new DwjyDTO();
        int id = showTableDTO.getId();
        dcDwjy dwjy = dwjyService.findDwjyById(id);
        dwjyDTO = dwjyService.transformDwjyToDTO(dwjy);
        return dwjyDTO;
    }

    //展示当前任务下的动物检疫的所有表项,也许将来要把所有的调查表都整合在这个方法里
    @ResponseBody
    @RequestMapping(value = "dwdcrw",method = RequestMethod.POST)
    public Object showDwjys(Integer page,
                            Integer rows,
                            HttpServletRequest request,
                            HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("dwrwid");
        dcUser user = (dcUser) request.getSession().getAttribute("user");
        ShowDwjyDTO showDwjyDTO = new ShowDwjyDTO();
        List<dcDwjy> dwjys = dwjyService.getDwjysByRenwuidForSub(renwuid,user);
        int size = dwjys.size();
        dwjys = dwjyService.getDwjyListPage(dwjys,page,rows,size);
        showDwjyDTO.setTotal(size);
        showDwjyDTO.setRows(dwjys);
        return showDwjyDTO;
    }

    @ResponseBody
    @RequestMapping(value = "dwlsjl",method = RequestMethod.POST)
    public Object showDwjyls(Integer page,
                            Integer rows,
                            HttpServletRequest request,
                            HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("dwlsrwid");
        dcUser user = (dcUser) request.getSession().getAttribute("user");
        ShowDwjyDTO showDwjyDTO = new ShowDwjyDTO();
        List<dcDwjy> dwjys = dwjyService.getDwjysByRenwuidForSub(renwuid,user);
        int size = dwjys.size();
        dwjys = dwjyService.getDwjyListPage(dwjys,page,rows,size);
        showDwjyDTO.setTotal(size);
        showDwjyDTO.setRows(dwjys);
        return showDwjyDTO;
    }

    //点击添加数据时将联系人，联系电话、关区等返回给前台
    @ResponseBody
    @RequestMapping(value = "showDwDcrwinformation",method = RequestMethod.POST)
    public Object showInformationAlreadyKnow(HttpServletRequest request,
                                             HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("dwrwid");
        dcUser user = (dcUser) request.getSession().getAttribute("user");
        DwjyInfoDTO dwjyInfoDTO = new DwjyInfoDTO();
        dwjyInfoDTO.setRwid(renwuid);
        dwjyInfoDTO = dwjyService.showInformationAlreadyKnow(dwjyInfoDTO,user.getDcGqdm());
        return dwjyInfoDTO;
    }

    //修改已有的数据
    @ResponseBody
    @RequestMapping(value = "modifyDwjy",method = RequestMethod.POST)
    public Object moodifyDwjy(@RequestBody DwjyDTO dwjyDTO,
                              HttpServletRequest request,
                              HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("修改一条动物检疫表项");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        dcDwjy dwjy = new dcDwjy();
        dwjy = dwjyService.transformDTOToDwjy(dwjyDTO);
        boolean flag = dwjyService.updateDwjy(dwjy);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("修改动物检疫表格成功");
        }
        else {
            resultDTO.setMessage("修改动物检疫表格失败");
        }
        return resultDTO;
    }

    //删除动物检疫表项
    @ResponseBody
    @RequestMapping(value = "deleteDwjy",method = RequestMethod.POST)
    public Object deleteDwjy(@RequestBody ShowTableDTO showTableDTO,
                             HttpServletRequest request,
                             HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("删除一条动物检疫表项");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        int id = showTableDTO.getId();
        boolean flag = dwjyService.deleteDwjyById(id);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除动物检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除动物检疫表格失败");
        }
        return resultDTO;
    }

    //提交动物检疫表,DTO是借用的,在具体表项页提交
    @ResponseBody
    @RequestMapping(value = "submitDwjy",method = RequestMethod.POST)
    public Object submit(HttpServletRequest request,
                         HttpServletResponse response){
        ResultDTO resultDTO = new ResultDTO();
        int rwid = (int)request.getSession().getAttribute("dwrwid");
        boolean flag = dcrwService.modifyStatusToSubmit(rwid);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除动物检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除动物检疫表格失败");
        }
        return resultDTO;
    }

    /*//在外部提交动物检疫任务
    @ResponseBody
    @RequestMapping(value = "submitDwjyOut",method = RequestMethod.POST)
    public Object submitOut(@RequestBody DwjyDTO dwjyDTO,
                            HttpServletRequest request,
                         HttpServletResponse response){
        ResultDTO resultDTO = new ResultDTO();
        int rwid = dwjyDTO.getRwid();
        boolean flag = dcrwService.modifyStatusToSubmit(rwid);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除动物检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除动物检疫表格失败");
        }
        return resultDTO;
    }*/

    //将历史任务中动物检疫表的表项选中后插入到当前任务中
    @ResponseBody
    @RequestMapping(value = "HistoryOnceMoreDj",method = RequestMethod.POST)
    public Object historyOnceMoreWj(@RequestBody HistoryOnceMoreDTO historyOnceMoreDTO,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("添加一条历史动物检疫表项");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        List<Integer> ids = historyOnceMoreDTO.getIds();
        boolean flag = dwjyService.historyOnceMoreDj(ids);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除动物检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除动物检疫表格失败");
        }
        return resultDTO;
    }

    @ResponseBody
    @RequestMapping(value = "dwjySearch",method = RequestMethod.GET)
    public Object wsjySearch(@RequestParam String content,
                             Integer page,
                             Integer rows,
                             HttpServletRequest request,
                             HttpServletResponse response){
        dcUser user = (dcUser) request.getSession().getAttribute("user");
        content = '%' + content + '%';
        List<dcDwjy> dwjys = dwjyService.searchDj(content,user);
        int size = dwjys.size();
        dwjys = dwjyService.getDwjyListPage(dwjys,page,rows,size);
        ShowDwjyDTO showDwjyDTO = new ShowDwjyDTO();
        showDwjyDTO.setRows(dwjys);
        showDwjyDTO.setTotal(size);
        return showDwjyDTO;
    }

    //动物检疫统计功能
    @ResponseBody
    @RequestMapping(value = "dwjyStatistics",method = RequestMethod.GET)
    public Object dwjyStatistics(@RequestParam boolean isAll,
                                     @RequestParam String gqName,
                                     Integer page,
                                     Integer rows,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        List<djLblx> allDjLbs = lblxService.getAllDjlx();
        StatisticDjResultDTO statisticDjResultDTO = new StatisticDjResultDTO();
        List<StatisticDjSingleResultDTO> singleResultDTOS;
        int size;
        //选择统计所有关区的资源数量
        if (isAll){
            singleResultDTOS= dwjyService.countType(allDjLbs);
            size = singleResultDTOS.size();
            singleResultDTOS = dwjyService.getDjTjListPage(singleResultDTOS,page,rows,size);
            statisticDjResultDTO.setTotal(size);
            statisticDjResultDTO.setRows(singleResultDTOS);
        }
        else {
            singleResultDTOS = dwjyService.countTypeForOneGq(allDjLbs,gqName);
            size = singleResultDTOS.size();
            statisticDjResultDTO.setTotal(size);
            statisticDjResultDTO.setRows(singleResultDTOS);
        }
        return statisticDjResultDTO;
    }
}
