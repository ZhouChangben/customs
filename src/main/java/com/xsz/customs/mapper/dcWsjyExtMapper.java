package com.xsz.customs.mapper;

import com.xsz.customs.model.dcWsjy;

import java.util.List;

public interface dcWsjyExtMapper {
    public List<dcWsjy> SearchWjContent(String content,String gqdm);
    public List<dcWsjy> SearchWjContentForSecond(String content,String gqName);
    public List<dcWsjy> SearchWjContentForMax(String content);
}
