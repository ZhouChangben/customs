package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.dcDwjy;
import com.xsz.customs.model.dcDwjy;
import com.xsz.customs.service.*;
import com.xsz.customs.service.DwjyService;
import com.xsz.customs.service.DwjyService;
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
public class DwjyController {
    @Autowired
    private DwjyService dwjyService;

    @Autowired
    private ZwjyService zwjyService;

    @Autowired
    private DcrwService dcrwService;



    @ResponseBody
    @RequestMapping(value = "addDwjy",method = RequestMethod.POST)
    public Object addDwjy(@RequestBody DwjyDTO dwjyDTO,
                          HttpServletRequest request,
                          HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("rwid");
        ResultDTO resultDTO = new ResultDTO();
        dcDwjy dwjy = new dcDwjy();
        dwjy = dwjyService.transformDTOToDwjy(dwjyDTO);

        dwjy.setDcRenwuid(renwuid);
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
        int renwuid = (int)request.getSession().getAttribute("rwid");
        ShowDwjyDTO showDwjyDTO = new ShowDwjyDTO();
        List<dcDwjy> dwjys = dwjyService.getDwjysByRenwuid(renwuid);
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
        int renwuid = (int)request.getSession().getAttribute("rwid");
        ShowDwjyDTO showDwjyDTO = new ShowDwjyDTO();
        List<dcDwjy> dwjys = dwjyService.getDwjysByRenwuid(renwuid);
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
        int renwuid = (int)request.getSession().getAttribute("rwid");
        DwjyInfoDTO dwjyInfoDTO = new DwjyInfoDTO();
        dwjyInfoDTO.setRwid(renwuid);
        dwjyInfoDTO = dwjyService.showInformationAlreadyKnow(dwjyInfoDTO);
        return dwjyInfoDTO;
    }

    //修改已有的数据
    @ResponseBody
    @RequestMapping(value = "modifyDwjy",method = RequestMethod.POST)
    public Object moodifyDwjy(@RequestBody DwjyDTO dwjyDTO,
                              HttpServletRequest request,
                              HttpServletResponse response){
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

    //提交动物检疫表,DTO是借用的
    @ResponseBody
    @RequestMapping(value = "submitDwjy",method = RequestMethod.POST)
    public Object submit(HttpServletRequest request,
                         HttpServletResponse response){
        ResultDTO resultDTO = new ResultDTO();
        int rwid = (int)request.getSession().getAttribute("rwid");
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

    //将历史任务中动物检疫表的表项选中后插入到当前任务中
    @ResponseBody
    @RequestMapping(value = "HistoryOnceMoreDj",method = RequestMethod.POST)
    public Object historyOnceMoreWj(@RequestBody HistoryOnceMoreDTO historyOnceMoreDTO,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
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

}
