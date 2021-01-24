package com.xsz.customs.dto;

import com.xsz.customs.model.dcWsjy;
import lombok.Data;

import java.util.List;

@Data
public class StatisticWjResultDTO {
    //数据条数
    int total;
    List<StatisticWjSingleResultDTO> rows;
}
