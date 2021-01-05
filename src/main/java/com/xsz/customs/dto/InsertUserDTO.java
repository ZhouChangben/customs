package com.xsz.customs.dto;

import lombok.Data;

@Data
public class InsertUserDTO {
    private String gqdm;
    private String gqname;
    private String gqpwd;
    private String gqlxr;
    private String gqlxdh;
    private boolean wsjy;
    private boolean dwjy;
    private boolean zwjy;
}
