package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.dcDwjy;
import com.xsz.customs.model.dcWsjy;
import com.xsz.customs.service.DcrwService;
import com.xsz.customs.service.DwjyService;
import com.xsz.customs.service.WsjyService;
import com.xsz.customs.service.ZwjyService;
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

    @ResponseBody
    @RequestMapping(value = "addWsjy",method = RequestMethod.POST)
    public Object addDwjy(@RequestBody WsjyDTO wsjyDTO,
                          HttpServletRequest request,
                          HttpServletResponse response){
        int renwuid = (int)request.getSession().getAttribute("rwid");
        AddDcrwResultDTO addDcrwResultDTO = new AddDcrwResultDTO();
        dcWsjy wsjy = new dcWsjy();
        wsjy = wsjyService.transformDTOToWsjy(wsjyDTO);
        wsjy.setDcRenwuid(renwuid);
        boolean flag = wsjyService.insertNewTable(wsjy);
        addDcrwResultDTO.setSuccess(flag);
        if (flag == true){
            addDcrwResultDTO.setMessage("插入动物检疫表格成功");
        }
        else {
            addDcrwResultDTO.setMessage("插入动物检疫表格失败");
        }
        return addDcrwResultDTO;
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
        int renwuid = (int)request.getSession().getAttribute("rwid");
        ShowWsjyDTO showWsjyDTO = new ShowWsjyDTO();
        List<dcWsjy> wsjys = wsjyService.getWsjysByRenwuid(renwuid);
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
        int renwuid = (int)request.getSession().getAttribute("rwid");
        WsjyInfoDTO wsjyInfoDTO = new WsjyInfoDTO();
        wsjyInfoDTO.setRwid(renwuid);
        wsjyInfoDTO = wsjyService.showInformationAlreadyKnow(wsjyInfoDTO);
        return wsjyInfoDTO;
    }

    //修改已有的数据
    @ResponseBody
    @RequestMapping(value = "modifyWsjy",method = RequestMethod.POST)
    public Object moodifyWsjy(@RequestBody WsjyDTO wsjyDTO,
                              HttpServletRequest request,
                              HttpServletResponse response){
        AddDcrwResultDTO addDcrwResultDTO = new AddDcrwResultDTO();
        dcWsjy wsjy = new dcWsjy();

        wsjy = wsjyService.transformDTOToWsjy(wsjyDTO);
        boolean flag = wsjyService.updateWsjy(wsjy);
        addDcrwResultDTO.setSuccess(flag);
        if (flag == true){
            addDcrwResultDTO.setMessage("修改卫生检疫表格成功");
        }
        else {
            addDcrwResultDTO.setMessage("修改卫生检疫表格失败");
        }
        return addDcrwResultDTO;
    }

    //删除卫生检疫表项
    @ResponseBody
    @RequestMapping(value = "deleteWsjy",method = RequestMethod.POST)
    public Object deleteWsjy(@RequestBody ShowTableDTO showTableDTO,
                            HttpServletRequest request,
                            HttpServletResponse response){
        AddDcrwResultDTO addDcrwResultDTO = new AddDcrwResultDTO();
        int id = showTableDTO.getId();
        System.out.println(id);
        boolean flag = wsjyService.deleteWsjyById(id);
        addDcrwResultDTO.setSuccess(flag);
        if (flag == true){
            addDcrwResultDTO.setMessage("删除卫生检疫表格成功");
        }
        else {
            addDcrwResultDTO.setMessage("删除卫生检疫表格失败");
        }
        return addDcrwResultDTO;
    }

    //提交卫生检疫表,DTO是借用的
    @ResponseBody
    @RequestMapping(value = "submit",method = RequestMethod.POST)
    public Object submit(HttpServletRequest request,
                         HttpServletResponse response){
        AddDcrwResultDTO addDcrwResultDTO = new AddDcrwResultDTO();
        int rwid = (int)request.getSession().getAttribute("rwid");
        System.out.println(rwid);
        boolean flag = dcrwService.modifyStatusToSubmit(rwid);
        addDcrwResultDTO.setSuccess(flag);
        if (flag == true){
            addDcrwResultDTO.setMessage("删除卫生检疫表格成功");
        }
        else {
            addDcrwResultDTO.setMessage("删除卫生检疫表格失败");
        }
        return addDcrwResultDTO;

    }

}
