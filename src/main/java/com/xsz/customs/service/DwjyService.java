package com.xsz.customs.service;

import com.xsz.customs.mapper.dcDwjyMapper;
import com.xsz.customs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DwjyService {
    @Autowired
    private dcDwjyMapper dwjyMapper;

    //这里的动物检疫就是存储着希望被修改的表数据
    public boolean insertNewTable(dcDwjy dcdwjy){
        dcDwjyExample dwjyExample = new dcDwjyExample();
        dwjyExample.createCriteria()
                .andDcRenwuidEqualTo(dcdwjy.getDcRenwuid());
        int flag = dwjyMapper.updateByExampleSelective(dcdwjy,dwjyExample);
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
}
