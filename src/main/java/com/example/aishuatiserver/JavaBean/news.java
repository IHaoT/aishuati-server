package com.example.aishuatiserver.JavaBean;


import lombok.Data;

import java.util.Date;

@Data
public class news {
    private int sander_Id;
    private int receiver_Id;
    private String content;
    private Date createTime;
    private int state;
}
