package com.xsz.customs.dto;

import lombok.Data;

@Data
public class WsjysDTO {//这个DTO的作用是当进入数据填写列表界面前获取选中的任务id，这样才能获取相应的列表
    private int renwuid;
}
