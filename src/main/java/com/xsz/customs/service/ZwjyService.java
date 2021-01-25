package com.xsz.customs.service;

import com.xsz.customs.dto.StatisticWjSingleResultDTO;
import com.xsz.customs.dto.StatisticZjSingleResultDTO;
import com.xsz.customs.dto.ZwjyDTO;
import com.xsz.customs.dto.ZwjyInfoDTO;
import com.xsz.customs.mapper.*;
import com.xsz.customs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZwjyService {
    @Autowired
    private dcWsjyMapper wsjyMapper;

    @Autowired
    private dcDcrwMapper dcrwMapper;

    @Autowired
    private dcUserMapper userMapper;

    @Autowired
    private dcZwjyMapper zwjyMapper;

    @Autowired
    private dcZwjyExtMapper zwjyExtMapper;

    @Autowired
    private dcDcrwExtMapper dcrwExtMapper;

    public boolean insertNewTable(dcZwjy zwjy) {
        int flag = zwjyMapper.insert(zwjy);
        if (flag == 0)
            return false;
        else
            return true;
    }

    public dcZwjy findZwjyById(int id) {
        dcZwjy zwjy = new dcZwjy();
        zwjy = zwjyMapper.selectByPrimaryKey(id);
        return zwjy;
    }



    public List<dcZwjy> getZwjysByRenwuid(int renwuid) {
        dcZwjyExample zwjyExample = new dcZwjyExample();
        zwjyExample.createCriteria()
                .andDcRenwuidEqualTo(renwuid);
        List<dcZwjy> zwjys = zwjyMapper.selectByExample(zwjyExample);
        return zwjys;
    }

    public List<dcZwjy> getZwjysByRenwuidForSub(int renwuid,dcUser user) {
        dcZwjyExample zwjyExample = new dcZwjyExample();
        List<dcZwjy> zwjys;
        if (user.getDcGqdj() < 2){
            zwjyExample.createCriteria()
                    .andDcRenwuidEqualTo(renwuid);
            zwjys = zwjyMapper.selectByExample(zwjyExample);
        }
        else {
            zwjyExample.createCriteria()
                    .andDcRenwuidEqualTo(renwuid)
                    .andDcRenwugqdm2EqualTo(user.getDcGqdm());
            zwjys = zwjyMapper.selectByExample(zwjyExample);
        }
        return zwjys;
    }

    public List<dcZwjy> getZwjyListPage(List<dcZwjy> zwjys, Integer page, Integer rows, int size) {
        int first = (page-1)*rows;
        int last = (page-1)*rows + rows;
        if (last > size){
            last = size;
        }
        return zwjys.subList(first,last);
    }



    public boolean updateZwjy(dcZwjy zwjy) {
        dcZwjyExample zwjyExample = new dcZwjyExample();
        zwjyExample.createCriteria()
                .andIdEqualTo(zwjy.getId());
        int flag = zwjyMapper.updateByExampleSelective(zwjy,zwjyExample);
        System.out.println("service层");
        if (flag == 0)
            return false;
        else
            return true;
    }

    public boolean deleteZwjyById(int id) {
        int flag = zwjyMapper.deleteByPrimaryKey(id);
        if (flag == 0)
            return false;
        else
            return true;
    }

    public boolean historyOnceMoreZj(List<Integer> ids) {

        dcDcrw dcrw = new dcDcrw();
        dcZwjy zwjy = new dcZwjy();
        dcDcrwExample dcrwExample = new dcDcrwExample();
        for (int id : ids) {
            zwjy = zwjyMapper.selectByPrimaryKey(id);
            zwjy.setId(null);
            int renwuid = zwjy.getDcRenwuid();
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
                        zwjy.setDcRenwumc(renwumc);
                        zwjy.setDcRenwuid(renwuidNew);
                        zwjyMapper.insert(zwjy);
                    }
                }
            }
        }
        return true;
    }

    public ZwjyInfoDTO showInformationAlreadyKnow(ZwjyInfoDTO zwjyInfoDTO,String gqdm) {

        int rwid = zwjyInfoDTO.getRwid();
        dcDcrwExample dcrwexample = new dcDcrwExample();
        dcrwexample.createCriteria()
                .andIdEqualTo(rwid);
        List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwexample);
        dcDcrw dcrw = dcrws.get(0);
        /*String gqdm = dcrw.getDcRenwugqdm();*/
        zwjyInfoDTO.setGqdm(gqdm);
        zwjyInfoDTO.setGqName(dcrw.getDcRenwugqname());
        zwjyInfoDTO.setRwmc(dcrw.getDcRenwumc());
        zwjyInfoDTO.setYwly(dcrw.getDcDcbname());
        zwjyInfoDTO.setSaveLocation(dcrw.getDcRenwugqname());

        dcUserExample userExample = new dcUserExample();
        userExample.createCriteria()
                .andDcGqdmEqualTo(gqdm);
        List<dcUser> users = userMapper.selectByExample(userExample);
        dcUser user = users.get(0);

        zwjyInfoDTO.setContact(user.getDcZjlxr());
        zwjyInfoDTO.setPrincipal(user.getDcZjfzr());
        zwjyInfoDTO.setLxrPhone(user.getDcZjlxdh());
        zwjyInfoDTO.setFzrPhone(user.getDcZjfzdh());

        return zwjyInfoDTO;
    }

    public List<dcZwjy> searchZj(String content,dcUser user){
        List<dcZwjy> zwjys;
        if (user.getDcGqdj() > 1){
            zwjys = zwjyExtMapper.SearchZjContent(content,user.getDcGqdm());
        }
        else if (user.getDcGqdj() == 1){
            zwjys = zwjyExtMapper.SearchZjContentForSecond(content,user.getDcGqname());
        }
        else {
            zwjys = zwjyExtMapper.SearchZjContentForMax(content);
        }
        return zwjys;
    }

    //统计所有关区的某资源数量
    public List<StatisticZjSingleResultDTO> countType(List<zjLblx> zjLblxes){
        dcUserExample userExample = new dcUserExample();
        userExample.createCriteria()
                .andDcGqdjEqualTo(1);
        List<dcUser> users = userMapper.selectByExample(userExample);
        ArrayList<StatisticZjSingleResultDTO> singleResultDTOS = new ArrayList<>();
        for (dcUser user:users) {
            ArrayList<Integer> counts = new ArrayList<>();
            for (zjLblx lblx : zjLblxes){
                dcZwjyExample example = new dcZwjyExample();
                example.createCriteria()
                        .andZjLbEqualTo(lblx.getZjlbMc())
                        .andDcRenwugqdmEqualTo(user.getDcGqdm());
                int size = (int)zwjyMapper.countByExample(example);
                counts.add(size);
            }
            StatisticZjSingleResultDTO singleResultDTO = new StatisticZjSingleResultDTO();
            singleResultDTO.setSourceCount(zjLblxes.size());
            singleResultDTO.setGqName(user.getDcGqname());
            singleResultDTO.setCounts(counts);
            for (int i = 0;i < counts.size();i++){
                switch(i){
                    case 0:{
                        singleResultDTO.setKc(counts.get(0));
                        break;
                    }
                    case 1:{
                        singleResultDTO.setZc(counts.get(1));
                        break;
                    }
                    case 2:{
                        singleResultDTO.setXc(counts.get(2));
                        break;
                    }
                    case 3:{
                        singleResultDTO.setXj(counts.get(3));
                        break;
                    }
                    case 4:{
                        singleResultDTO.setZj(counts.get(4));
                        break;
                    }
                    case 5:{
                        singleResultDTO.setBd(counts.get(5));
                        break;
                    }
                    case 6:{
                        singleResultDTO.setRtdw(counts.get(6));
                        break;
                    }
                    case 7:{
                        singleResultDTO.setWzzy(counts.get(7));
                        break;
                    }
                    case 8:{
                        singleResultDTO.setQt(counts.get(8));
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
            singleResultDTOS.add(singleResultDTO);
        }
        return singleResultDTOS;
    }
    
    public List<StatisticZjSingleResultDTO> countTypeForOneGq(List<zjLblx> zjLblxes,String gqName){
        dcUserExample userExample = new dcUserExample();
        userExample.createCriteria()
                .andDcGqnameEqualTo(gqName);
        List<dcUser> users = userMapper.selectByExample(userExample);
        ArrayList<StatisticZjSingleResultDTO> singleResultDTOS = new ArrayList<>();
        if (users.size() != 0){
            dcUser user = users.get(0);
            ArrayList<Integer> counts = new ArrayList<>();
            for (zjLblx lblx : zjLblxes){
                dcZwjyExample example = new dcZwjyExample();
                example.createCriteria()
                        .andZjLbEqualTo(lblx.getZjlbMc())
                        .andDcRenwugqdmEqualTo(user.getDcGqdm());
                int size = (int)zwjyMapper.countByExample(example);
                /*List<dcWsjy> wsjys = wsjyMapper.selectByExample(example);*/
                counts.add(size);
            }
            StatisticZjSingleResultDTO singleResultDTO = new StatisticZjSingleResultDTO();
            singleResultDTO.setSourceCount(zjLblxes.size());
            singleResultDTO.setGqName(user.getDcGqname());
            singleResultDTO.setCounts(counts);
            for (int i = 0;i < counts.size();i++){
                switch(i){
                    case 0:{
                        singleResultDTO.setKc(counts.get(0));
                        break;
                    }
                    case 1:{
                        singleResultDTO.setZc(counts.get(1));
                        break;
                    }
                    case 2:{
                        singleResultDTO.setXc(counts.get(2));
                        break;
                    }
                    case 3:{
                        singleResultDTO.setXj(counts.get(3));
                        break;
                    }
                    case 4:{
                        singleResultDTO.setZj(counts.get(4));
                        break;
                    }
                    case 5:{
                        singleResultDTO.setBd(counts.get(5));
                        break;
                    }
                    case 6:{
                        singleResultDTO.setRtdw(counts.get(6));
                        break;
                    }
                    case 7:{
                        singleResultDTO.setWzzy(counts.get(7));
                        break;
                    }
                    case 8:{
                        singleResultDTO.setQt(counts.get(8));
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
            singleResultDTOS.add(singleResultDTO);
        }
        return singleResultDTOS;
    }

    public List<StatisticZjSingleResultDTO> getZjTjListPage(List<StatisticZjSingleResultDTO> statisticZjSingleResultDTOS, int page, int rows, int size){
        int first = (page-1)*rows;
        int last = (page-1)*rows + rows;
        if (last > size){
            last = size;
        }
        return statisticZjSingleResultDTOS.subList(first,last);
    }

    public ZwjyDTO transformZwjyToDTO(dcZwjy zwjy) {
        ZwjyDTO zwjyDTO = new ZwjyDTO();

        zwjyDTO.setId(zwjy.getId());
        zwjyDTO.setGqdm(zwjy.getDcRenwugqdm());
        zwjyDTO.setGqName(zwjy.getDcRenwugqname());
        zwjyDTO.setName(zwjy.getZjName());
        zwjyDTO.setEname(zwjy.getZjLdxm());
        zwjyDTO.setRwid(zwjy.getDcRenwuid());
        zwjyDTO.setRwmc(zwjy.getDcRenwumc());
        zwjyDTO.setYwly(zwjy.getZjYwly());
        zwjyDTO.setCategory(zwjy.getZjLb());
        zwjyDTO.setSaveType(zwjy.getZjBaocunxs());
        zwjyDTO.setSaveCondition(zwjy.getZjBaocuntj());
        zwjyDTO.setSaveLocation(zwjy.getZjBaocunwz());
        zwjyDTO.setSaveStatus(zwjy.getZjBaocunzt());
        zwjyDTO.setSaveTime(zwjy.getZjBaocunsj());
        zwjyDTO.setSource(zwjy.getZjLaiyuan());
        zwjyDTO.setSourceSite(zwjy.getZjLaiyuangj());
        zwjyDTO.setDanwei(zwjy.getZjYbdanwei());
        zwjyDTO.setShidai(zwjy.getZjShidai());
        zwjyDTO.setJydw(zwjy.getZjJianyidw());
        zwjyDTO.setJyywlb(zwjy.getZjJianyiywlb());
        zwjyDTO.setNumber(zwjy.getZjYbl());
        zwjyDTO.setHwlb(zwjy.getZjHuowulb());
        zwjyDTO.setJizhu(zwjy.getZjJizhu());
        zwjyDTO.setObtainSite(zwjy.getZjHuodedd());
        zwjyDTO.setCode(zwjy.getZjNeibubh());
        zwjyDTO.setContact(zwjy.getZjLianlr());
        zwjyDTO.setLxrPhone(zwjy.getZjLianldh());
        zwjyDTO.setPrincipal(zwjy.getZjFuzeren());
        zwjyDTO.setFzrPhone(zwjy.getZjFuzerendh());
        zwjyDTO.setRemark(zwjy.getZjBy1());
        return zwjyDTO;
    }

    public dcZwjy transformDTOToZwjy(ZwjyDTO zwjyDTO) {
        dcZwjy zwjy = new dcZwjy();

        zwjy.setId(zwjyDTO.getId());
        zwjy.setDcRenwuid(zwjyDTO.getRwid());
        zwjy.setDcRenwuxh(zwjyDTO.getRwxh());
        zwjy.setDcRenwumc(zwjyDTO.getRwmc());
        zwjy.setDcRenwugqdm(zwjyDTO.getGqdm());
        zwjy.setDcRenwugqname(zwjyDTO.getGqName());
        zwjy.setZjName(zwjyDTO.getName());
        zwjy.setZjLdxm(zwjyDTO.getEname());
        zwjy.setZjYwly(zwjyDTO.getYwly());
        zwjy.setZjLb(zwjyDTO.getCategory());
        zwjy.setZjBaocunsj(zwjyDTO.getSaveTime());
        zwjy.setZjBaocuntj(zwjyDTO.getSaveCondition());
        zwjy.setZjBaocunwz(zwjyDTO.getSaveLocation());
        zwjy.setZjBaocunxs(zwjyDTO.getSaveType());
        zwjy.setZjBaocunzt(zwjyDTO.getSaveStatus());
        zwjy.setZjLaiyuan(zwjyDTO.getSource());
        zwjy.setZjLaiyuangj(zwjyDTO.getSourceSite());
        zwjy.setZjYbdanwei(zwjyDTO.getDanwei());
        zwjy.setZjShidai(zwjyDTO.getShidai());
        zwjy.setZjJianyidw(zwjyDTO.getJydw());
        zwjy.setZjJianyiywlb(zwjyDTO.getJyywlb());
        zwjy.setZjYbl(zwjyDTO.getNumber());
        zwjy.setZjHuowulb(zwjyDTO.getHwlb());
        zwjy.setZjJizhu(zwjyDTO.getJizhu());
        zwjy.setZjHuodedd(zwjyDTO.getObtainSite());
        zwjy.setZjNeibubh(zwjyDTO.getCode());
        zwjy.setZjLianlr(zwjyDTO.getContact());
        zwjy.setZjLianldh(zwjyDTO.getLxrPhone());
        zwjy.setZjFuzeren(zwjyDTO.getPrincipal());
        zwjy.setZjFuzerendh(zwjyDTO.getFzrPhone());
        zwjy.setZjBy1(zwjyDTO.getRemark());
        return zwjy;
    }
}
