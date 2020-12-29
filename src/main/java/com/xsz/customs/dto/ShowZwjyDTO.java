package com.xsz.customs.dto;

import com.xsz.customs.model.dcWsjy;
import com.xsz.customs.model.dcZwjy;
import lombok.Data;

import java.util.List;

@Data
public class ShowZwjyDTO {
    private int total;
    private List<dcZwjy> rows;
}
