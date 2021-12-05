package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class AdminInfo {
    private int Administrator_id;
    private String Administrator_account;
    private String Administrator_name;
    private String Administrator_email;
    private String Administrator_telephone;
    private Date Administrator_createtime;
    private int state;
}
