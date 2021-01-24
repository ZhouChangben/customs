package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.*;
import com.xsz.customs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ZwjyController {
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

    @ResponseBody
    @RequestMapping(value = "addZwjy",method = RequestMethod.POST)
    public Object addZwjy(@RequestBody ZwjyDTO zwjyDTO,
                          HttpServletRequest request,
                          HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("添加一条植物检疫表项");
        logService.InsertLog(log);

        int renwuid = (int)request.getSession().getAttribute("zwrwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        ResultDTO resultDTO = new ResultDTO();
        dcZwjy zwjy = new dcZwjy();
        zwjy = zwjyService.transformDTOToZwjy(zwjyDTO);
        zwjy.setDcRenwuid(renwuid);
        zwjy.setDcRenwugqdm2(user.getDcGqdm());
        boolean flag = zwjyService.insertNewTable(zwjy);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("插入植物检疫表格成功");
        }
        else {
            resultDTO.setMessage("插入植物检疫表格失败");
        }
        return resultDTO;
    }

    //将数据库中的数据写在未提交的表中
    @ResponseBody
    @RequestMapping(value = "showZwjyTable",method = RequestMethod.POST)
    public Object showTable(@RequestBody ShowTableDTO showTableDTO,
                            HttpServletRequest request,
                            HttpServletResponse response){
        ZwjyDTO zwjyDTO = new ZwjyDTO();
        int id = showTableDTO.getId();
        dcZwjy zwjy = zwjyService.findZwjyById(id);
        zwjyDTO = zwjyService.transformZwjyToDTO(zwjy);
        return zwjyDTO;
    }

    //展示当前任务下的卫生检疫的所有表项,也许将来要把所有的调查表都整合在这个方法里
    @ResponseBody
    @RequestMapping(value = "zwdcrw",method = RequestMethod.POST)
    public Object showWsjys(Integer page,
                            Integer rows,
                            HttpServletRequest request,
                            HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("zwrwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        ShowZwjyDTO showZwjyDTO = new ShowZwjyDTO();
        List<dcZwjy> zwjys = zwjyService.getZwjysByRenwuidForSub(renwuid,user);
        int size = zwjys.size();
        zwjys = zwjyService.getZwjyListPage(zwjys,page,rows,size);
        showZwjyDTO.setTotal(size);
        showZwjyDTO.setRows(zwjys);
        return showZwjyDTO;
    }

    @ResponseBody
    @RequestMapping(value = "zwlsjl",method = RequestMethod.POST)
    public Object showZwjyls(Integer page,
                             Integer rows,
                             HttpServletRequest request,
                             HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("zwlsrwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        ShowZwjyDTO showZwjyDTO = new ShowZwjyDTO();
        List<dcZwjy> zwjys = zwjyService.getZwjysByRenwuidForSub(renwuid,user);
        int size = zwjys.size();
        zwjys = zwjyService.getZwjyListPage(zwjys,page,rows,size);
        showZwjyDTO.setTotal(size);
        showZwjyDTO.setRows(zwjys);
        return showZwjyDTO;
    }

    //点击添加数据时将联系人，联系电话、关区等返回给前台
    @ResponseBody
    @RequestMapping(value = "showZwDcrwinformation",method = RequestMethod.POST)
    public Object showInformationAlreadyKnow(HttpServletRequest request,
                                             HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("zwrwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        ZwjyInfoDTO zwjyInfoDTO = new ZwjyInfoDTO();
        zwjyInfoDTO.setRwid(renwuid);
        zwjyInfoDTO = zwjyService.showInformationAlreadyKnow(zwjyInfoDTO,user.getDcGqdm());
        return zwjyInfoDTO;
    }

    //修改已有的数据
    @ResponseBody
    @RequestMapping(value = "modifyZwjy",method = RequestMethod.POST)
    public Object moodifyWsjy(@RequestBody ZwjyDTO zwjyDTO,
                              HttpServletRequest request,
                              HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("修改一条植物检疫表项");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        dcZwjy zwjy = new dcZwjy();
        zwjy = zwjyService.transformDTOToZwjy(zwjyDTO);
        boolean flag = zwjyService.updateZwjy(zwjy);

        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("修改植物检疫表格成功");
        }
        else {
            resultDTO.setMessage("修改植物检疫表格失败");
        }
        return resultDTO;
    }

    //删除植物检疫表项
    @ResponseBody
    @RequestMapping(value = "deleteZwjy",method = RequestMethod.POST)
    public Object deleteWsjy(@RequestBody ShowTableDTO showTableDTO,
                             HttpServletRequest request,
                             HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("删除一条植物检疫表项");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        int id = showTableDTO.getId();
        boolean flag = zwjyService.deleteZwjyById(id);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除植物检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除植物检疫表格失败");
        }
        return resultDTO;
    }

    //提交植物检疫表
    @ResponseBody
    @RequestMapping(value = "submitZwjy",method = RequestMethod.POST)
    public Object submit(HttpServletRequest request,
                         HttpServletResponse response){
        ResultDTO resultDTO = new ResultDTO();
        int renwuid = (int)request.getSession().getAttribute("zwrwid");
        boolean flag = dcrwService.modifyStatusToSubmit(renwuid);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除植物检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除植物检疫表格失败");
        }
        return resultDTO;
    }

    //在外部提交植物检疫任务
    @ResponseBody
    @RequestMapping(value = "submitZwjyOut",method = RequestMethod.POST)
    public Object submitOut(@RequestBody ZwjyDTO zwjyDTO,
                            HttpServletRequest request,
                            HttpServletResponse response){
        ResultDTO resultDTO = new ResultDTO();
        int rwid = zwjyDTO.getRwid();
        boolean flag = dcrwService.modifyStatusToSubmit(rwid);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除植物检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除植物检疫表格失败");
        }
        return resultDTO;
    }

    //将历史任务中植物检疫表的表项选中后插入到当前任务中
    @ResponseBody
    @RequestMapping(value = "HistoryOnceMoreZj",method = RequestMethod.POST)
    public Object historyOnceMoreWj(@RequestBody HistoryOnceMoreDTO historyOnceMoreDTO,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        //日志记录
        dcLog log = new dcLog();
        log.setIp(request.getRemoteAddr());
        log.setTime(System.currentTimeMillis());
        log.setMovement("插入一条历史植物检疫表项");
        logService.InsertLog(log);

        ResultDTO resultDTO = new ResultDTO();
        List<Integer> ids = historyOnceMoreDTO.getIds();
        boolean flag = zwjyService.historyOnceMoreZj(ids);
        resultDTO.setSuccess(flag);
        if (flag == true){
            resultDTO.setMessage("删除卫生检疫表格成功");
        }
        else {
            resultDTO.setMessage("删除卫生检疫表格失败");
        }
        return resultDTO;
    }

    @ResponseBody
    @RequestMapping(value = "zwjySearch",method = RequestMethod.POST)
    public Object zwjySearch(@RequestBody SeasrchContentDTO seasrchContentDTO,
                             HttpServletRequest request,
                             HttpServletResponse response){
        dcUser user = (dcUser) request.getSession().getAttribute("user");
        String content = seasrchContentDTO.getContent();
        content = '%' + content + '%';
        List<dcZwjy> zwjys = zwjyService.searchZj(content,user);
        ShowZwjyDTO showZwjyDTO = new ShowZwjyDTO();
        showZwjyDTO.setRows(zwjys);
        showZwjyDTO.setTotal(zwjys.size());
        return showZwjyDTO;
    }

}
