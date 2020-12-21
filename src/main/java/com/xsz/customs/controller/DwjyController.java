package com.xsz.customs.controller;

import com.xsz.customs.dto.*;
import com.xsz.customs.model.dcDwjy;
import com.xsz.customs.service.DwjyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DwjyController {
    @Autowired
    private DwjyService dwjyService;

    @Autowired
    private DwjyService zwjyService;

    @Autowired
    private DwjyService wsjyService;

    @ResponseBody
    @RequestMapping(value = "addDwjy",method = RequestMethod.POST)
    public Object addDwjy(@RequestBody DwjyDTO dwjyDTO,
                          HttpServletRequest request,
                          HttpServletResponse response){
        AddDcrwResultDTO addDcrwResultDTO = new AddDcrwResultDTO();
        dcDwjy dwjy = new dcDwjy();
        //写完DwjyDTO以后写


        boolean flag = dwjyService.insertNewTable(dwjy);
        addDcrwResultDTO.setSuccess(flag);
        if (flag == true){
            addDcrwResultDTO.setMessage("插入动物检疫表格成功");
        }
        else {
            addDcrwResultDTO.setMessage("插入动物检疫表格失败");
        }
        return addDcrwResultDTO;
    }

    @ResponseBody
    @RequestMapping(value = "showTable",method = RequestMethod.POST)
    public Object showTable(@RequestBody ShowTableDTO showTableDTO,
                            HttpServletRequest request,
                            HttpServletResponse response){

        int renwuid = showTableDTO.getRenwuid();
        if (showTableDTO.getDcbName().equals("动物检疫")){
            DwjyDTO dwjyDTO = new DwjyDTO();
            dcDwjy dwjy = dwjyService.getDwjyByRenwuid(renwuid);
            //把动物检疫表和DTO整合起来

            return dwjyDTO;
        }
        else if (showTableDTO.getDcbName().equals("植物检疫")){
            ZwjyDTO zwjyDTO = new ZwjyDTO();

            return zwjyDTO;
        }
        else if (showTableDTO.getDcbName().equals("卫生检疫")){
            WsjyDTO wsjyDTO = new WsjyDTO();

            return wsjyDTO;
        }
        return null;
    }

}
