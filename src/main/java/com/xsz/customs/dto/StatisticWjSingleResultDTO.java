package com.xsz.customs.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticWjSingleResultDTO {
    //这个count是资源类型的数量
    int SourceCount;
    String gqName;
    List<Integer> counts;
    int bmsw;
    int yxyb;
    int dz;
    int jz;
    int xbz;
    int hsdb;
}
