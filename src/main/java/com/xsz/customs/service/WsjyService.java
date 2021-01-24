package com.xsz.customs.service;

import com.xsz.customs.dto.StatisticWjResultDTO;
import com.xsz.customs.dto.StatisticWjSingleResultDTO;
import com.xsz.customs.dto.WsjyDTO;
import com.xsz.customs.dto.WsjyInfoDTO;
import com.xsz.customs.mapper.*;
import com.xsz.customs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WsjyService {
    @Autowired
    private dcWsjyMapper wsjyMapper;

    @Autowired
    private dcDcrwMapper dcrwMapper;

    @Autowired
    private dcUserMapper userMapper;

    @Autowired
    private dcDcrwExtMapper dcrwExtMapper;

    @Autowired
    private dcWsjyExtMapper wsjyExtMapper;

    public boolean updateTable(dcWsjy dcwsjy){

        dcWsjyExample wsjyExample = new dcWsjyExample();
        wsjyExample.createCriteria()
                .andDcRenwuidEqualTo(dcwsjy.getId());
        int flag = wsjyMapper.updateByExampleSelective(dcwsjy,wsjyExample);
        if (flag == 0)
            return false;
        else
            return true;
    }

    public boolean insertNewTable(dcWsjy wsjy){
        int flag = wsjyMapper.insert(wsjy);
        if (flag == 0)
            return false;
        else
            return true;
    }

    //通过id获取具体的一个卫生检疫
    public dcWsjy getWsjyById(int id){
        dcWsjy wsjy = new dcWsjy();
        wsjy = wsjyMapper.selectByPrimaryKey(id);
        return wsjy;
    }

    //通过任务id获取当前任务填写的所有表项
    public List<dcWsjy> getWsjysByRenwuid(int renwuid){
        dcWsjyExample dcWsjyExample = new dcWsjyExample();
        dcWsjyExample.createCriteria()
                .andDcRenwuidEqualTo(renwuid);
        List<dcWsjy> wsjys = wsjyMapper.selectByExample(dcWsjyExample);
        return wsjys;
    }

    public List<dcWsjy> getWsjysByRenwuidForSub(int renwuid,dcUser user){
        dcWsjyExample dcWsjyExample = new dcWsjyExample();
        List<dcWsjy> wsjys;
        if (user.getDcGqdj() < 2){
            dcWsjyExample.createCriteria()
                    .andDcRenwuidEqualTo(renwuid);
            wsjys = wsjyMapper.selectByExample(dcWsjyExample);
        }
        else {
            dcWsjyExample.createCriteria()
                    .andDcRenwuidEqualTo(renwuid)
                    .andDcRenwugqdm2EqualTo(user.getDcGqdm());
            wsjys = wsjyMapper.selectByExample(dcWsjyExample);
        }
        return wsjys;
    }

    //这个方法是用来给卫生检疫表项的列表分页的
    public List<dcWsjy> getWsjyListPage(List<dcWsjy> wsjys, int page, int rows, int size){
        int first = (page-1)*rows;
        int last = (page-1)*rows + rows;
        if (last > size){
            last = size;
        }
        return wsjys.subList(first,last);
    }

    //返回一些在填写卫生检疫表时需要自动显示的数据
    public WsjyInfoDTO showInformationAlreadyKnow(WsjyInfoDTO wsjyInfoDTO,String gqdm){
        int rwid = wsjyInfoDTO.getRwid();
        dcDcrwExample dcrwexample = new dcDcrwExample();
        dcrwexample.createCriteria()
                .andIdEqualTo(rwid);
        List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwexample);
        dcDcrw dcrw = dcrws.get(0);
        /*String gqdm = dcrw.getDcRenwugqdm();*/
        wsjyInfoDTO.setGqdm(gqdm);
        wsjyInfoDTO.setGqName(dcrw.getDcRenwugqname());
        wsjyInfoDTO.setRwmc(dcrw.getDcRenwumc());
        wsjyInfoDTO.setYwly(dcrw.getDcDcbname());
        wsjyInfoDTO.setSaveLocation(dcrw.getDcRenwugqname());

        dcUserExample userExample = new dcUserExample();
        userExample.createCriteria()
                .andDcGqdmEqualTo(gqdm);
        List<dcUser> users = userMapper.selectByExample(userExample);
        dcUser user = users.get(0);

        wsjyInfoDTO.setContact(user.getDcWjlxr());
        wsjyInfoDTO.setPrincipal(user.getDcWjfzr());
        wsjyInfoDTO.setLxrPhone(user.getDcWjlxdh());
        wsjyInfoDTO.setFzrPhone(user.getDcWjfzdh());

        return wsjyInfoDTO;
    }

    //将wsjy转化为DTO
    public WsjyDTO transformWsjyToDTO(dcWsjy wsjy){
        WsjyDTO wsjyDTO = new WsjyDTO();

        wsjyDTO.setId(wsjy.getId());
        wsjyDTO.setRwid(wsjy.getDcRenwuid());
        wsjyDTO.setGqName(wsjy.getDcRenwugqname());
        wsjyDTO.setGqdm(wsjy.getDcRenwugqdm());
        wsjyDTO.setRwxh(wsjy.getDcRenwuxh());
        wsjyDTO.setRwmc(wsjy.getDcRenwumc());
        wsjyDTO.setYwly(wsjy.getWjYwly());
        wsjyDTO.setCategory(wsjy.getWjLb());
        wsjyDTO.setSaveType(wsjy.getWjBaocunxs());
        wsjyDTO.setName(wsjy.getWjName());
        wsjyDTO.setEname(wsjy.getWjLdxm());
        wsjyDTO.setZhuxi(wsjy.getWjZhuxi());
        wsjyDTO.setChuanci(wsjy.getWjZhuanci());
        wsjyDTO.setSource(wsjy.getWjLaiyuan());
        wsjyDTO.setSzhj(wsjy.getWjLysuzhu());
        wsjyDTO.setNumber(wsjy.getWjYbl());
        wsjyDTO.setDanwei(wsjy.getWjYbdanwei());
        wsjyDTO.setSaveStatus(wsjy.getWjBaocunzt());
        wsjyDTO.setObtainSite(wsjy.getWjHuodedd());
        wsjyDTO.setObtainApproach(wsjy.getWjHuoqutj());
        wsjyDTO.setSaveCondition(wsjy.getWjBaocuntj());
        wsjyDTO.setSaveTime(wsjy.getWjBaocunsj());
        wsjyDTO.setCode(wsjy.getWjNeibubh());
        wsjyDTO.setSaveLocation(wsjy.getWjBaocunwz());
        wsjyDTO.setContact(wsjy.getWjLianlr());
        wsjyDTO.setLxrPhone(wsjy.getWjLianldh());
        wsjyDTO.setPrincipal(wsjy.getWjFuzeren());
        wsjyDTO.setFzrPhone(wsjy.getWjFuzerendh());
        wsjyDTO.setRemark(wsjy.getWjBy1());

        return wsjyDTO;
    }

    //将DTO转化为wsjy
    public dcWsjy transformDTOToWsjy(WsjyDTO wsjyDTO){
        dcWsjy wsjy = new dcWsjy();

        wsjy.setId(wsjyDTO.getId());
        wsjy.setDcRenwuid(wsjyDTO.getRwid());
        wsjy.setDcRenwugqname(wsjyDTO.getGqName());
        wsjy.setDcRenwugqdm(wsjyDTO.getGqdm());
        wsjy.setDcRenwuxh(wsjyDTO.getRwxh());
        wsjy.setDcRenwumc(wsjyDTO.getRwmc());
        wsjy.setWjYwly(wsjyDTO.getYwly());
        wsjy.setWjLb(wsjyDTO.getCategory());
        wsjy.setWjBaocunxs(wsjyDTO.getSaveType());
        wsjy.setWjName(wsjyDTO.getName());
        wsjy.setWjLdxm(wsjyDTO.getEname());
        wsjy.setWjZhuxi(wsjyDTO.getZhuxi());
        wsjy.setWjZhuanci(wsjyDTO.getChuanci());
        wsjy.setWjLaiyuan(wsjyDTO.getSource());
        wsjy.setWjLysuzhu(wsjyDTO.getSzhj());
        wsjy.setWjYbl(wsjyDTO.getNumber());
        wsjy.setWjYbdanwei(wsjyDTO.getDanwei());
        wsjy.setWjBaocunzt(wsjyDTO.getSaveStatus());
        wsjy.setWjHuodedd(wsjyDTO.getObtainSite());
        wsjy.setWjHuoqutj(wsjyDTO.getObtainApproach());
        wsjy.setWjBaocuntj(wsjyDTO.getSaveCondition());
        wsjy.setWjBaocunsj(wsjyDTO.getSaveTime());
        wsjy.setWjNeibubh(wsjyDTO.getCode());
        wsjy.setWjBaocunwz(wsjyDTO.getSaveLocation());
        wsjy.setWjLianlr(wsjyDTO.getContact());
        wsjy.setWjLianldh(wsjyDTO.getLxrPhone());
        wsjy.setWjFuzeren(wsjyDTO.getPrincipal());
        wsjy.setWjFuzerendh(wsjyDTO.getFzrPhone());
        //当做备注用
        wsjy.setWjBy1(wsjyDTO.getRemark());

        return wsjy;
    }

    public dcWsjy findWsjyById(int id) {
        dcWsjy wsjy = wsjyMapper.selectByPrimaryKey(id);
        return wsjy;
    }

    public boolean updateWsjy(dcWsjy wsjy){
        dcWsjyExample example = new dcWsjyExample();
        int id = wsjy.getId();
        example.createCriteria()
                .andIdEqualTo(id);
        int flag = wsjyMapper.updateByExampleSelective(wsjy,example);
        if (flag == 0)
            return false;
        else
            return true;
    }

    public boolean deleteWsjyById(int id){
        int flag = wsjyMapper.deleteByPrimaryKey(id);
        if (flag == 0)
            return false;
        else
            return true;
    }

    //将历史任务中卫生检疫表的表项选中后插入到当前任务中
    public boolean historyOnceMoreWj(List<Integer> ids){
        dcDcrw dcrw = new dcDcrw();
        dcWsjy wsjy = new dcWsjy();
        dcDcrwExample dcrwExample = new dcDcrwExample();
        for (int id : ids) {
            wsjy = wsjyMapper.selectByPrimaryKey(id);
            wsjy.setId(null);
            int renwuid = wsjy.getDcRenwuid();
            dcrw = dcrwMapper.selectByPrimaryKey(renwuid);
            int xh = dcrwExtMapper.SelectTheLatestMission();
            String dcrwGqName = dcrw.getDcRenwugqname();
            String dcbName = dcrw.getDcDcbname();

            dcrwExample.createCriteria()
                    .andDcRenwuxhEqualTo(xh)
                    .andDcRenwugqnameEqualTo(dcrwGqName)
                    .andDcDcbnameEqualTo(dcbName);

            List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwExample);
            if (dcrws != null){
                for (dcDcrw dcrwNewest : dcrws) {
                    if (dcrwNewest.getDcDcbzt().equals("未提交")) {
                        int renwuidNew = dcrwNewest.getId();
                        String renwumc = dcrwNewest.getDcRenwumc();
                        wsjy.setDcRenwumc(renwumc);
                        wsjy.setDcRenwuid(renwuidNew);
                        wsjyMapper.insert(wsjy);
                    }
                }
            }
        }
        return true;
    }

    public List<dcWsjy> searchWj(String content,dcUser user){
        List<dcWsjy> wsjys;
        if (user.getDcGqdj() > 1){
            wsjys = wsjyExtMapper.SearchWjContent(content,user.getDcGqdm());
        }
        else if (user.getDcGqdj() == 1){
            wsjys = wsjyExtMapper.SearchWjContentForSecond(content,user.getDcGqname());
        }
        else {
            wsjys = wsjyExtMapper.SearchWjContentForMax(content);
        }
        return wsjys;
    }

    //统计所有关区的某资源数量
    public StatisticWjResultDTO countType(List<wjLblx> wjLblxes){
        dcUserExample userExample = new dcUserExample();
        userExample.createCriteria()
                .andDcGqdjEqualTo(1);
        List<dcUser> users = userMapper.selectByExample(userExample);
        ArrayList<StatisticWjSingleResultDTO> singleResultDTOS = new ArrayList<>();
        for (dcUser user:users) {
            ArrayList<Integer> counts = new ArrayList<>();
            for (wjLblx lblx : wjLblxes){
                dcWsjyExample example = new dcWsjyExample();
                example.createCriteria()
                        .andWjLbEqualTo(lblx.getWjlbMc())
                        .andDcRenwugqdmEqualTo(user.getDcGqdm());
                int size = (int)wsjyMapper.countByExample(example);
                /*List<dcWsjy> wsjies = wsjyMapper.selectByExample(example);
                int size = wsjies.size();*/
                //System.out.println(user.getDcGqname()+lblx.getWjlbMc()+wsjies.size());
                counts.add(size);
            }
            StatisticWjSingleResultDTO singleResultDTO = new StatisticWjSingleResultDTO();
            singleResultDTO.setSourceCount(wjLblxes.size());
            singleResultDTO.setGqName(user.getDcGqname());
            singleResultDTO.setCounts(counts);
            for (int i = 0;i < counts.size();i++){
                switch(i){
                    case 0:{
                        singleResultDTO.setBmsw(counts.get(0));
                        break;
                    }
                    case 1:{
                        singleResultDTO.setYxyb(counts.get(1));
                        break;
                    }
                    case 2:{
                        singleResultDTO.setDz(counts.get(2));
                        break;
                    }
                    case 3:{
                        singleResultDTO.setJz(counts.get(3));
                        break;
                    }
                    case 4:{
                        singleResultDTO.setXbz(counts.get(4));
                        break;
                    }
                    case 5:{
                        singleResultDTO.setHsdb(counts.get(5));
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
            System.out.println(singleResultDTO);
            singleResultDTOS.add(singleResultDTO);
        }
        StatisticWjResultDTO resultDTO = new StatisticWjResultDTO();
        resultDTO.setRows(singleResultDTOS);
        resultDTO.setTotal(singleResultDTOS.size());
        return resultDTO;
    }

    public int countTypeForOneGq(List<wjLblx> wjLblxes,String gqName){

        return 1;
    }
}
