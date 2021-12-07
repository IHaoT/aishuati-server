package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class AdminInfo {
    private int adminId;
    private String adminAccount;
    private String adminName;
    private String adminEmail;
    private String adminTelephoto;
    private Date adminCreateTime;
    private int state;
}
