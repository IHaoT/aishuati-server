package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class Administrator {
    private int Administrator_id;
    private String Administrator_Account;
    private String Administrator_name;
    private String Administrator_pwd;
    private String Administrator_email;
    private String Administrator_telephone;
    private Date Administrator_createTime;
    private String introduce;
    private int state;
}
