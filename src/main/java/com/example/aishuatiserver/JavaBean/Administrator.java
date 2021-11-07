package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class Administrator {
    private int Administrator_id;
    private String Administrator_name;
    private String Administrator_pwd;
    private String Administrator_email;
    private int Administrator_sex;
    private String Administrator_telephone;
    private Date Administrator_createTime;
    private int state;
}
