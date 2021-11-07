package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class User_event_log {
    private int logId;
    private int stuId;
    private String stuName;
    private String content;
    private Date createTime;
}
