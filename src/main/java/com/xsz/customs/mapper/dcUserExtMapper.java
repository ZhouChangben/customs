package com.xsz.customs.mapper;

import com.xsz.customs.model.dcUser;
import com.xsz.customs.model.dcUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface dcUserExtMapper {
    List<dcUser> SelectByFrontDm(String record);
}