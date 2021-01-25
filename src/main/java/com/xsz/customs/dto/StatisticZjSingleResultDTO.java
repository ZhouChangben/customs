package com.xsz.customs.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticZjSingleResultDTO {
    //这个count是资源类型的数量
    int SourceCount;
    String gqName;
    List<Integer> counts;
    int kc;
    int zc;
    int xc;
    int xj;
    int zj;
    int bd;
    int rtdw;
    int wzzy;
    int qt;
}
