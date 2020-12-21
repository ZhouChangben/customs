package com.xsz.customs.service;

import com.xsz.customs.mapper.dcWsjyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xsz.customs.model.dcWsjy;
import java.util.List;

@Service
public class WsjyService {
    @Autowired
    private dcWsjyMapper wsjyMapper;

    public boolean insertNewTable(dcWsjy dcwsjy){

        return true;

    }
}
