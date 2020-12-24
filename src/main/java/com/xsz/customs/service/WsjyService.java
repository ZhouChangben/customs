package com.xsz.customs.service;

import com.xsz.customs.dto.WsjyDTO;
import com.xsz.customs.dto.WsjyInfoDTO;
import com.xsz.customs.mapper.dcDcrwMapper;
import com.xsz.customs.mapper.dcUserMapper;
import com.xsz.customs.mapper.dcWsjyMapper;
import com.xsz.customs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WsjyService {
    @Autowired
    private dcWsjyMapper wsjyMapper;

    @Autowired
    private dcDcrwMapper dcrwMapper;

    @Autowired
    private dcUserMapper userMapper;


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
        dcWsjyExample dcwsjyExample = new dcWsjyExample();
        dcwsjyExample.createCriteria()
                .andIdEqualTo(id);
        List<dcWsjy> wsjys = wsjyMapper.selectByExample(dcwsjyExample);
        if (wsjys.size() != 0){
            return wsjys.get(0);
        }
        else
            return null;
    }

    //通过任务id获取当前任务填写的所有表项
    public List<dcWsjy> getWsjysByRenwuid(int renwuid){
        dcWsjyExample dcWsjyExample = new dcWsjyExample();
        dcWsjyExample.createCriteria()
                .andDcRenwuidEqualTo(renwuid);
        List<dcWsjy> wsjys = wsjyMapper.selectByExample(dcWsjyExample);
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
    public WsjyInfoDTO showInformationAlreadyKnow(WsjyInfoDTO wsjyInfoDTO){
        int rwid = wsjyInfoDTO.getRwid();
        dcDcrwExample dcrwexample = new dcDcrwExample();
        dcrwexample.createCriteria()
                .andIdEqualTo(rwid);
        List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwexample);
        dcDcrw dcrw = dcrws.get(0);
        String gqdm = dcrw.getDcRenwugqdm();
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

        wsjyInfoDTO.setContact(user.getDcLxr());
        wsjyInfoDTO.setPrincipal(user.getDcLxr());
        wsjyInfoDTO.setLxrPhone(user.getDcLxdh());
        wsjyInfoDTO.setFzrPhone(user.getDcLxdh());

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
    public boolean historyOnceMore(List<Integer> ids,int renwuid){
        dcDcrw dcrw = new dcDcrw();
        dcWsjy wsjy = new dcWsjy();
        dcrw = dcrwMapper.selectByPrimaryKey(renwuid);
        for (int id : ids) {
            wsjy = wsjyMapper.selectByPrimaryKey(id);
            wsjy.setDcRenwumc(dcrw.getDcRenwumc());
            wsjyMapper.insert(wsjy);
        }
        return true;
    }
}
