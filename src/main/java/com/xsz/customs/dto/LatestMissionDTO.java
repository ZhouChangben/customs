package com.xsz.customs.dto;

import com.xsz.customs.model.dcDcrw;
import lombok.Data;

import java.util.List;

@Data
public class LatestMissionDTO {
    private int total;
    private List<dcDcrw> rows;
}
