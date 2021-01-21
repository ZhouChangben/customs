package com.xsz.customs.service;

import com.xsz.customs.dto.DwjyDTO;
import com.xsz.customs.dto.DwjyInfoDTO;
import com.xsz.customs.mapper.dcDcrwExtMapper;
import com.xsz.customs.mapper.dcDcrwMapper;
import com.xsz.customs.mapper.dcDwjyMapper;
import com.xsz.customs.mapper.dcUserMapper;
import com.xsz.customs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DwjyService {
    @Autowired
    private dcDwjyMapper dwjyMapper;

    @Autowired
    private dcDcrwMapper dcrwMapper;

    @Autowired
    private dcUserMapper userMapper;

    @Autowired
    private dcDcrwExtMapper dcrwExtMapper;

    public boolean updateDwjy(dcDwjy dwjy) {
        dcDwjyExample dwjyExample = new dcDwjyExample();
        dwjyExample.createCriteria()
                .andIdEqualTo(dwjy.getId());
        int flag = dwjyMapper.updateByExampleSelective(dwjy,dwjyExample);
        if (flag == 0)
            return false;
        else
            return true;
    }


    public dcDwjy findDwjyById(int id) {
        dcDwjy dwjy = new dcDwjy();
        dwjy = dwjyMapper.selectByPrimaryKey(id);
        return dwjy;
    }

    //这里的动物检疫就是存储着希望被修改的表数据
    public boolean insertNewTable(dcDwjy dcdwjy){
        int flag = dwjyMapper.insert(dcdwjy);
        if (flag == 0)
            return false;
        else
            return true;
    }

    public dcDwjy getDwjyByRenwuid(int renwuid){
        dcDwjyExample dcDwjyExample = new dcDwjyExample();
        dcDwjyExample.createCriteria()
                .andDcRenwuidEqualTo(renwuid);
        List<dcDwjy> dwjys = dwjyMapper.selectByExample(dcDwjyExample);
        if (dwjys.size() != 0){
            return dwjys.get(0);
        }
        else
            return null;
    }

    public List<dcDwjy> getDwjysByRenwuidForSub(int renwuid,dcUser user) {
        dcDwjyExample dwjyExample = new dcDwjyExample();
        List<dcDwjy> dwjys;
        //如果为高级关区，就展示所有表项
        if (user.getDcGqdj()<2){
            dwjyExample.createCriteria()
                    .andDcRenwuidEqualTo(renwuid);
            dwjys = dwjyMapper.selectByExample(dwjyExample);
        }
        //当前用户为三级填报用户
        else {
            dwjyExample.createCriteria()
                    .andDcRenwuidEqualTo(renwuid)
                    .andDcRenwugqdm2EqualTo(user.getDcGqdm());
            dwjys = dwjyMapper.selectByExample(dwjyExample);
        }
        return dwjys;
    }

    public List<dcDwjy> getDwjysByRenwuid(int renwuid) {
        dcDwjyExample dwjyExample = new dcDwjyExample();
        dwjyExample.createCriteria()
                .andDcRenwuidEqualTo(renwuid);
        List<dcDwjy> dwjys = dwjyMapper.selectByExample(dwjyExample);
        return dwjys;
    }

    public List<dcDwjy> getDwjyListPage(List<dcDwjy> dwjys, Integer page, Integer rows, int size) {
        int first = (page-1)*rows;
        int last = (page-1)*rows + rows;
        if (last > size){
            last = size;
        }
        return dwjys.subList(first,last);
    }

    public DwjyInfoDTO showInformationAlreadyKnow(DwjyInfoDTO dwjyInfoDTO) {
        int rwid = dwjyInfoDTO.getRwid();
        dcDcrwExample dcrwexample = new dcDcrwExample();
        dcrwexample.createCriteria()
                .andIdEqualTo(rwid);
        List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwexample);
        dcDcrw dcrw = dcrws.get(0);
        String gqdm = dcrw.getDcRenwugqdm();
        dwjyInfoDTO.setGqdm(gqdm);
        dwjyInfoDTO.setGqName(dcrw.getDcRenwugqname());
        dwjyInfoDTO.setRwmc(dcrw.getDcRenwumc());
        dwjyInfoDTO.setYwly(dcrw.getDcDcbname());
        dwjyInfoDTO.setSaveLocation(dcrw.getDcRenwugqname());

        dcUserExample userExample = new dcUserExample();
        userExample.createCriteria()
                .andDcGqdmEqualTo(gqdm);
        List<dcUser> users = userMapper.selectByExample(userExample);
        dcUser user = users.get(0);

        dwjyInfoDTO.setContact(user.getDcLxr());
        dwjyInfoDTO.setPrincipal(user.getDcLxr());
        dwjyInfoDTO.setLxrPhone(user.getDcLxdh());
        dwjyInfoDTO.setFzrPhone(user.getDcLxdh());

        return dwjyInfoDTO;
    }


    public boolean deleteDwjyById(int id) {
        int flag = dwjyMapper.deleteByPrimaryKey(id);
        if (flag == 0)
            return false;
        else
            return true;
    }

    public boolean historyOnceMoreDj(List<Integer> ids) {
        dcDcrw dcrw = new dcDcrw();
        dcDwjy dwjy = new dcDwjy();
        dcDcrwExample dcrwExample = new dcDcrwExample();
        for (int id : ids) {
            dwjy = dwjyMapper.selectByPrimaryKey(id);
            dwjy.setId(null);
            int renwuid = dwjy.getDcRenwuid();
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
                        dwjy.setDcRenwumc(renwumc);
                        dwjy.setDcRenwuid(renwuidNew);
                        dwjyMapper.insert(dwjy);
                    }
                }
                /*dcDcrw dcrwNewest = new dcDcrw();
                dcrwNewest = dcrws.get(0);
                int renwuidNew = dcrwNewest.getId();
                String renwumc = dcrwNewest.getDcRenwumc();
                dwjy.setDcRenwumc(renwumc);
                dwjy.setDcRenwuid(renwuidNew);
                dwjyMapper.insert(dwjy);*/
            }
        }
        return true;
    }

    public DwjyDTO transformDwjyToDTO(dcDwjy dwjy) {
        DwjyDTO dwjyDTO = new DwjyDTO();

        dwjyDTO.setId(dwjy.getId());
        dwjyDTO.setGqdm(dwjy.getDcRenwugqdm());
        dwjyDTO.setGqName(dwjy.getDcRenwugqname());
        dwjyDTO.setRwid(dwjy.getDcRenwuid());
        dwjyDTO.setRwmc(dwjy.getDcRenwumc());
        dwjyDTO.setRwxh(dwjy.getDcRenwuxh());
        dwjyDTO.setYwly(dwjy.getDjYwly());
        dwjyDTO.setName(dwjy.getDjName());
        dwjyDTO.setEname(dwjy.getDjYwm());
        dwjyDTO.setCategory(dwjy.getDjLb());
        dwjyDTO.setNumber(dwjy.getDjYbl());
        dwjyDTO.setObtainApproach(dwjy.getDjHuoqutj());
        dwjyDTO.setObtainLocation(dwjy.getDjHuodedd());
        dwjyDTO.setSaveLocation(dwjy.getDjBaocunwz());
        dwjyDTO.setSaveTime(dwjy.getDjBaocunsj());
        dwjyDTO.setSaveCondition(dwjy.getDjBaocuntj());
        dwjyDTO.setCode(dwjy.getDjNeibubh());
        dwjyDTO.setContact(dwjy.getDjLianlr());
        dwjyDTO.setLxrPhone(dwjy.getDjLianldh());
        dwjyDTO.setPrincipal(dwjy.getDjFuzeren());
        dwjyDTO.setFzrPhone(dwjy.getDjFuzerendh());
        dwjyDTO.setRemark(dwjy.getDjBy1());
        dwjyDTO.setDanwei(dwjy.getDjYbdanwei());
        dwjyDTO.setChuanci(dwjy.getDjChuanci());
        dwjyDTO.setDzxq(dwjy.getDjDuzhuxing());
        dwjyDTO.setBywswCategory(dwjy.getDjBingyuan());
        dwjyDTO.setCell(dwjy.getDjXibaoxing());
        dwjyDTO.setCondition(dwjy.getDjZhuangtai());
        dwjyDTO.setSituation(dwjy.getDjJibenqk());
        dwjyDTO.setCdCell(dwjy.getDjDuzhucd());
        dwjyDTO.setSuzhu(dwjy.getDjLysuzhu());
        dwjyDTO.setSourceSite(dwjy.getDjLaiyuan());
        return dwjyDTO;
    }

    public dcDwjy transformDTOToDwjy(DwjyDTO dwjyDTO) {
        dcDwjy dwjy = new dcDwjy();
        dwjy.setId(dwjyDTO.getId());
        dwjy.setDcRenwugqdm(dwjyDTO.getGqdm());
        dwjy.setDcRenwugqname(dwjyDTO.getGqName());
        dwjy.setDcRenwuid(dwjyDTO.getRwid());
        dwjy.setDcRenwumc(dwjyDTO.getRwmc());
        dwjy.setDcRenwuxh(dwjyDTO.getRwxh());
        dwjy.setDjYwly(dwjyDTO.getYwly());
        dwjy.setDjName(dwjyDTO.getName());
        dwjy.setDjYwm(dwjyDTO.getEname());
        dwjy.setDjLb(dwjyDTO.getCategory());
        dwjy.setDjYbl(dwjyDTO.getNumber());
        dwjy.setDjHuoqutj(dwjyDTO.getObtainApproach());
        dwjy.setDjHuodedd(dwjyDTO.getObtainLocation());
        dwjy.setDjBaocunsj(dwjyDTO.getSaveTime());
        dwjy.setDjBaocunwz(dwjyDTO.getSaveLocation());
        dwjy.setDjBaocuntj(dwjyDTO.getSaveCondition());
        dwjy.setDjNeibubh(dwjyDTO.getCode());
        dwjy.setDjLianlr(dwjyDTO.getContact());
        dwjy.setDjLianldh(dwjyDTO.getLxrPhone());
        dwjy.setDjFuzeren(dwjyDTO.getPrincipal());
        dwjy.setDjFuzerendh(dwjyDTO.getFzrPhone());
        dwjy.setDjBy1(dwjyDTO.getRemark());
        dwjy.setDjYbdanwei(dwjyDTO.getDanwei());
        dwjy.setDjChuanci(dwjyDTO.getChuanci());
        dwjy.setDjDuzhuxing(dwjyDTO.getDzxq());
        dwjy.setDjBingyuan(dwjyDTO.getBywswCategory());
        dwjy.setDjXibaoxing(dwjyDTO.getCell());
        dwjy.setDjZhuangtai(dwjyDTO.getCondition());
        dwjy.setDjJibenqk(dwjyDTO.getSituation());
        dwjy.setDjDuzhucd(dwjyDTO.getCdCell());
        dwjy.setDjLysuzhu(dwjyDTO.getSuzhu());
        dwjy.setDjLaiyuan(dwjyDTO.getSourceSite());
        return dwjy;
    }
}
