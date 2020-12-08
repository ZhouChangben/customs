package com.xsz.customs.dto;

import com.xsz.customs.model.dcUser;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SubUsersDTO {
    private int total;
    private List<dcUser> rows;


}
