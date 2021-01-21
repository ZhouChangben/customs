package com.xsz.customs.controller;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.xsz.customs.dto.ResultDTO;
import com.xsz.customs.model.dcDwjy;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.model.dcWsjy;
import com.xsz.customs.model.dcZwjy;
import com.xsz.customs.service.DwjyService;
import com.xsz.customs.service.WsjyService;
import com.xsz.customs.service.ZwjyService;
import jdk.internal.util.xml.impl.ReaderUTF8;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private DwjyService dwjyService;

    @Autowired
    private WsjyService wsjyService;

    @Autowired
    private ZwjyService zwjyService;



    /**
     * Excel表格导出接口
     * http://localhost:8080/excelDownloadXj
     * @param response response对象
     * @throws IOException 抛IO异常
     */
    @RequestMapping("/excelDownloadDj")
    public void djExcelDownload(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        int renwuid = (int)request.getSession().getAttribute("rwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        //表头数据
        String[] header = {"序号", "关区", "业务领域", "类别", "名称", "英文名(简称）","毒（菌）株型/血清型","病原微生物分类","细胞型","内部保存编号","传次","状态\\形式","毒株传代细胞","宿主","来源国家/地区","获得渠道","数量","保存条件","保存时间","基本情况","获得地点","保存位置\n" +
                "（省市区，隶属关/部门）","联系人","联络电话","负责人","联络电话","备注"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("动物检疫表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }

        //获取所有的employee
        List<dcDwjy> dwjys;
        if (user.getDcGqdj() < 2){
            dwjys = dwjyService.getDwjysByRenwuid(renwuid);
        }
        else {
            dwjys = dwjyService.getDwjysByRenwuidForSub(renwuid,user);
        }
        for(int i=0;i<dwjys.size();i++){
            //创建一行
            HSSFRow row1 = sheet.createRow(i+1);
            //第一列序号
            row1.createCell(0).setCellValue(new HSSFRichTextString(String.valueOf(i+1)));
            //第二列关区
            row1.createCell(1).setCellValue(new HSSFRichTextString(dwjys.get(i).getDcRenwugqname()));
            //第三列业务领域
            row1.createCell(2).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjYwly()));
            //第三列类别
            row1.createCell(3).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjLb()));
            //第4列名称
            row1.createCell(4).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjName()));
            //第5列英文名
            row1.createCell(5).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjYwm()));
            //第6列毒株型
            row1.createCell(6).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjDuzhuxing()));
            //第7列病原微生物分类
            row1.createCell(7).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjBingyuan()));
            //第8列细胞型
            row1.createCell(8).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjXibaoxing()));
            //第9列内部保存编号
            row1.createCell(9).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjNeibubh()));
            //第10列传次
            row1.createCell(10).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjChuanci()));
            //第11列状态/形式
            row1.createCell(11).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjZhuangtai()));
            //第12列毒株传代细胞
            row1.createCell(12).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjDuzhucd()));
            //第13列宿主
            row1.createCell(13).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjLysuzhu()));
            //第14列来源国家
            row1.createCell(14).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjLaiyuan()));
            //第15列获得渠道
            row1.createCell(15).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjHuoqutj()));
            //第16列数量
            row1.createCell(16).setCellValue(new HSSFRichTextString(String.valueOf(dwjys.get(i).getDjYbl())+dwjys.get(i).getDjYbdanwei()));
            //第17列保存条件
            row1.createCell(17).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjBaocuntj()));
            //第18列保存时间
            row1.createCell(18).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjBaocunsj()));
            //第19列基本情况
            row1.createCell(19).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjJibenqk()));
            //第20列获得地点
            row1.createCell(20).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjHuodedd()));
            //第21列保存位置（省市区，隶属关/部门）
            row1.createCell(21).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjBaocunwz()));
            //第22列联络人
            row1.createCell(22).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjLianlr()));
            //第23列联络电话
            row1.createCell(23).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjLianldh()));
            //第24列负责人
            row1.createCell(24).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjFuzeren()));
            //第25列负责人联络电话
            row1.createCell(25).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjFuzerendh()));
            //第26列备注
            row1.createCell(26).setCellValue(new HSSFRichTextString(dwjys.get(i).getDjBy1()));
        }


        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为“关区名称+动物检疫.xls”
        String fileName = new String((user.getDcGqname()+"动物检疫").getBytes(),"ISO8859-1");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/excelDownloadWj")
    public void wjExcelDownload(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        int renwuid = (int)request.getSession().getAttribute("rwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        //表头数据
        String[] header = {"序号", "关区", "业务领域", "类别", "名称", "拉丁学名（细胞株英文名）","株系（仅适用于细胞株）","传次（仅适用于细胞株）","内部保存编号","保存形式","来源","来源宿主或环境","样本量","保存状态","获得地点","获取途径","保存条件","保存时间","保存位置","联系人","联络电话","负责人","联络电话","备注"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("卫生检疫表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.BLUE.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }

        //获取所有的employee
        List<dcWsjy> wsjys;
        if (user.getDcGqdj() < 2){
           wsjys = wsjyService.getWsjysByRenwuid(renwuid);
        }
        else {
            wsjys = wsjyService.getWsjysByRenwuidForSub(renwuid,user);
        }
        for(int i=0;i<wsjys.size();i++){
            //创建一行
            HSSFRow row1 = sheet.createRow(i+1);
            //第一列序号
            row1.createCell(0).setCellValue(new HSSFRichTextString(String.valueOf(i+1)));
            //第二列关区
            row1.createCell(1).setCellValue(new HSSFRichTextString(wsjys.get(i).getDcRenwugqname()));
            //第三列业务领域
            row1.createCell(2).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjYwly()));
            //第4列类别
            row1.createCell(3).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjLb()));
            //第5列名称
            row1.createCell(4).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjName()));
            //第6列拉丁学名
            row1.createCell(5).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjLdxm()));
            //第7列株系
            row1.createCell(6).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjZhuxi()));
            //第8列传次
            row1.createCell(7).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjZhuanci()));
            //第9列内部保存编号
            row1.createCell(8).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjNeibubh()));
            //第10列保存形式
            row1.createCell(9).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjBaocunxs()));
            //第10列来源
            row1.createCell(10).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjLaiyuan()));
            //第11列来源宿主或环境
            row1.createCell(11).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjLysuzhu()));
            //第12列样本量
            row1.createCell(12).setCellValue(new HSSFRichTextString(String.valueOf(wsjys.get(i).getWjYbl())+wsjys.get(i).getWjYbdanwei()));
            //第13列保存状态
            row1.createCell(13).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjBaocunzt()));
            //第14列获得地点
            row1.createCell(14).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjHuodedd()));
            //第15列获取途径
            row1.createCell(15).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjHuoqutj()));
            //第16列保存条件
            row1.createCell(16).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjBaocuntj()));
            //第17列保存时间
            row1.createCell(17).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjBaocunsj()));
            //第18列保存位置
            row1.createCell(18).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjBaocunwz()));
            //第22列联络人
            row1.createCell(19).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjLianlr()));
            //第23列联络电话
            row1.createCell(20).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjLianldh()));
            //第24列负责人
            row1.createCell(21).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjFuzeren()));
            //第25列负责人联络电话
            row1.createCell(22).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjFuzerendh()));
            //第26列备注
            row1.createCell(23).setCellValue(new HSSFRichTextString(wsjys.get(i).getWjBy1()));
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为“关区名称+卫生检疫.xls”
        String fileName = new String((user.getDcGqname()+"卫生检疫").getBytes(),"ISO8859-1");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/excelDownloadZj")
    public void zjExcelDownload(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        int renwuid = (int)request.getSession().getAttribute("rwid");
        dcUser user = (dcUser)request.getSession().getAttribute("user");
        ResultDTO resultDTO = new ResultDTO();
        //表头数据
        String[] header = {"序号", "关区", "业务领域", "类别", "中文名称", "拉丁学名","世代/种下阶元","内部保存编号","检疫地位","检疫业务类别","货物类别","寄主","保存形式","来源","来源国家/地区","样本数量","保存状态","获得地点","保存部门(省市区，隶属关/部门)","保存条件","保存时间","鉴定人","联络电话","负责人","联络电话","备注"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("卫生检疫表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREEN.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }
        //"序号", "关区", "业务领域", "类别", "中文名称", "拉丁学名","世代/种下阶元","内部保存编号","检疫地位","检疫业务类别","货物类别","寄主","保存形式","来源","来源国家/地区","样本数量","保存状态","获得地点","保存部门(省市区，隶属关/部门)","保存条件","保存时间","鉴定人","联络电话","负责人","联络电话","备注"
        List<dcZwjy> zwjys = zwjyService.getZwjysByRenwuid(renwuid);
        for(int i=0;i<zwjys.size();i++){
            //创建一行
            HSSFRow row1 = sheet.createRow(i+1);
            //第一列序号
            row1.createCell(0).setCellValue(new HSSFRichTextString(String.valueOf(i+1)));
            //第二列关区
            row1.createCell(1).setCellValue(new HSSFRichTextString(zwjys.get(i).getDcRenwugqname()));
            //第三列业务领域
            row1.createCell(2).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjYwly()));
            //第4列类别
            row1.createCell(3).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjLb()));
            //第5列中文名称
            row1.createCell(4).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjName()));
            //第6列拉丁学名
            row1.createCell(5).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjLdxm()));
            //第7列世代/种下阶元
            row1.createCell(6).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjShidai()));
            //第8列内部保存编号
            row1.createCell(7).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjNeibubh()));
            //第9列检疫地位
            row1.createCell(8).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjJianyidw()));
            //第10列检疫业务类别
            row1.createCell(9).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjJianyiywlb()));
            //第10列货物类别
            row1.createCell(10).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjHuowulb()));
            //第11列寄主
            row1.createCell(11).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjJizhu()));
            //第12列保存形式
            row1.createCell(12).setCellValue(new HSSFRichTextString(zwjys.get(0).getZjBaocunxs()));
            //第13列来源
            row1.createCell(13).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjLaiyuan()));
            //第14列来源国家/地区
            row1.createCell(14).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjLaiyuangj()));
            //第15列样本数量
            row1.createCell(15).setCellValue(new HSSFRichTextString(String.valueOf(zwjys.get(i).getZjYbl())+zwjys.get(i).getZjYbdanwei()));
            //"保存状态","获得地点","保存部门(省市区，隶属关/部门)","保存条件","保存时间","鉴定人","联络电话","负责人","联络电话","备注"
            //第16列保存状态
            row1.createCell(16).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjBaocunzt()));
            //第17列获得地点
            row1.createCell(17).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjHuodedd()));
            //第18列保存部门(省市区，隶属关/部门)
            row1.createCell(18).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjBaocunwz()));
            //第19列保存条件
            row1.createCell(19).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjBaocuntj()));
            //第20列保存时间
            row1.createCell(20).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjBaocunsj()));
            //第21列鉴定人
            row1.createCell(21).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjLianlr()));
            //第22列联络电话
            row1.createCell(22).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjLianldh()));
            //第23列负责人
            row1.createCell(23).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjFuzeren()));
            //第24列负责人联络电话
            row1.createCell(24).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjFuzerendh()));
            //第25列备注
            row1.createCell(25).setCellValue(new HSSFRichTextString(zwjys.get(i).getZjBy1()));
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为“关区名称+植物检疫.xls”
        String fileName = new String((user.getDcGqname()+"植物检疫").getBytes(),"ISO8859-1");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls");

        //刷新缓冲
        response.flushBuffer();
        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }
}
