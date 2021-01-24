package com.xsz.customs.mapper;

import com.xsz.customs.model.dcZwjy;

import java.util.List;

public interface dcZwjyExtMapper {
    public List<dcZwjy> SearchZjContent(String content, String gqdm);
    public List<dcZwjy> SearchZjContentForSecond(String content, String gqName);
    public List<dcZwjy> SearchZjContentForMax(String content);
}
