package com.xsz.customs.mapper;

import com.xsz.customs.model.dcDwjy;

import java.util.List;

public interface dcDwjyExtMapper {
    public List<dcDwjy> SearchDjContent(String content, String gqdm);
    public List<dcDwjy> SearchDjContentForSecond(String content, String gqName);
    public List<dcDwjy> SearchDjContentForMax(String content);
}
