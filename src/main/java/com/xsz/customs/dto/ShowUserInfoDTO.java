package com.xsz.customs.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShowUserInfoDTO {
    private int total;
    private List<UserInfoDTO> rows;
}
