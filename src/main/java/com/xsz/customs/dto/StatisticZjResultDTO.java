package com.xsz.customs.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticZjResultDTO {
    //数据条数
    int total;
    List<StatisticZjSingleResultDTO> rows;
}
