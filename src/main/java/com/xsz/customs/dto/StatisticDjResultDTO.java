package com.xsz.customs.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticDjResultDTO {
    int total;
    List<StatisticDjSingleResultDTO> rows;
}
