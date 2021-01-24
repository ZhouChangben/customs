package com.xsz.customs.service;

import com.xsz.customs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xsz.customs.mapper.*;

import java.util.List;

@Service
public class LblxService {
    @Autowired
    private djLblxMapper djLbMapper;

    @Autowired
    private wjLblxMapper wjLbMapper;

    @Autowired
    private zjLblxMapper zjLbMapper;

    public List<djLblx> getAllDjlx(){
        djLblxExample djlxExample = new djLblxExample();
        djlxExample.createCriteria()
                .andDjlbDmIsNotNull();
        List<djLblx> djLbs = djLbMapper.selectByExample(djlxExample);
        return djLbs;
    }

    public List<wjLblx> getAllWjlx(){
        wjLblxExample wjlxExample = new wjLblxExample();
        wjlxExample.createCriteria()
                .andWjlbDmIsNotNull();
        List<wjLblx> wjLbs = wjLbMapper.selectByExample(wjlxExample);
        return wjLbs;
    }

    public List<zjLblx> getAllZjlx(){
        zjLblxExample zjlxExample = new zjLblxExample();
        zjlxExample.createCriteria()
                .andZjlbDmIsNotNull();
        List<zjLblx> zjLbs = zjLbMapper.selectByExample(zjlxExample);
        return zjLbs;
    }



}
