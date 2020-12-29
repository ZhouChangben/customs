package com.xsz.customs.service;

import com.xsz.customs.mapper.dcLogMapper;
import com.xsz.customs.model.dcLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private dcLogMapper logMapper;

    public void InsertLog(dcLog log){
        logMapper.insert(log);
    }
}
