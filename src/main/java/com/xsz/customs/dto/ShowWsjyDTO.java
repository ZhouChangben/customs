package com.xsz.customs.dto;

import com.xsz.customs.model.dcWsjy;
import lombok.Data;

import java.util.List;

@Data
public class ShowWsjyDTO {
    private int total;
    private List<dcWsjy> rows;
}
