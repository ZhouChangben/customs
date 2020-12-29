package com.xsz.customs.dto;

import com.xsz.customs.model.dcDwjy;
import lombok.Data;

import java.util.List;

@Data
public class ShowDwjyDTO {
    private int total;
    private List<dcDwjy> rows;
}
